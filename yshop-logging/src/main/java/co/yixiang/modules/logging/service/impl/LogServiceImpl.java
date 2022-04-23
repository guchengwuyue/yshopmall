/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.logging.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.LogTypeEnum;
import co.yixiang.enums.YesNoEnum;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.logging.service.LogService;
import co.yixiang.modules.logging.service.dto.LogErrorDTO;
import co.yixiang.modules.logging.service.dto.LogQueryCriteria;
import co.yixiang.modules.logging.service.dto.LogSmallDTO;
import co.yixiang.modules.logging.service.mapper.LogMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.SecurityUtils;
import co.yixiang.utils.StringUtils;
import co.yixiang.utils.ValidationUtil;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author hupeng
 * @date 2018-11-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl extends BaseServiceImpl<LogMapper, co.yixiang.modules.logging.domain.Log> implements LogService {


    private final LogMapper logMapper;

    private final IGenerator generator;

    public LogServiceImpl(LogMapper logMapper, IGenerator generator) {
        this.logMapper = logMapper;
        this.generator = generator;
    }

    @Override
    public Object findAllByPageable(LogQueryCriteria criteria, Pageable pageable) {
        criteria.setLogType(LogTypeEnum.INFO.getDesc());
        criteria.setType(YesNoEnum.YES_NO_1.getValue());
        getPage(pageable);
        PageInfo<co.yixiang.modules.logging.domain.Log> page = new PageInfo<>(logMapper.findAllByPageable(criteria.getBlurry()));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        if (Objects.equals(criteria.getType(),YesNoEnum.YES_NO_0.getValue())){
            criteria.setLogType(LogTypeEnum.INFO.getDesc());
            criteria.setType(YesNoEnum.YES_NO_0.getValue());
        } else {
            criteria.setLogType(LogTypeEnum.ERROR.getDesc());
        }
        getPage(pageable);
        PageInfo<co.yixiang.modules.logging.domain.Log> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        String status = LogTypeEnum.ERROR.getDesc();
        if (status.equals(criteria.getLogType())) {
            map.put("content", generator.convert(page.getList(), LogErrorDTO.class));
            map.put("totalElements", page.getTotal());
        }
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<co.yixiang.modules.logging.domain.Log> queryAll(LogQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(co.yixiang.modules.logging.domain.Log.class, criteria));
    }

    @Override
    public Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable) {
        criteria.setLogType(LogTypeEnum.INFO.getDesc());
        criteria.setBlurry(SecurityUtils.getUsername());
        getPage(pageable);
        PageInfo<co.yixiang.modules.logging.domain.Log> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), LogSmallDTO.class));
        map.put("totalElements", page.getTotal());
        return map;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String ip, ProceedingJoinPoint joinPoint,
                     co.yixiang.modules.logging.domain.Log log, Long uid) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log aopLog = method.getAnnotation(Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        //类型 0-后台 1-前台
        log.setType(aopLog.type());
        if (uid != null) {
            log.setUid(uid);
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
                username = new JSONObject(argValues[0]).get("username").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        this.save(log);
    }

    @Override
    public Object findByErrDetail(Long id) {
        co.yixiang.modules.logging.domain.Log log = this.getById(id);
        ValidationUtil.isNull(log.getId(), "Log", "id", id);
        byte[] details = log.getExceptionDetail();
        return Dict.create().set("exception", new String(ObjectUtil.isNotNull(details) ? details : "".getBytes()));
    }

    @Override
    public void download(List<co.yixiang.modules.logging.domain.Log> logs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (co.yixiang.modules.logging.domain.Log log : logs) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", log.getUsername());
            map.put("IP", log.getRequestIp());
            map.put("IP来源", log.getAddress());
            map.put("描述", log.getDescription());
            map.put("浏览器", log.getBrowser());
            map.put("请求耗时/毫秒", log.getTime());
            map.put("异常详情", new String(ObjectUtil.isNotNull(log.getExceptionDetail()) ? log.getExceptionDetail() : "".getBytes()));
            map.put("创建日期", log.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        logMapper.deleteByLogType(LogTypeEnum.ERROR.getDesc());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        logMapper.deleteByLogType(LogTypeEnum.INFO.getDesc());
    }
}
