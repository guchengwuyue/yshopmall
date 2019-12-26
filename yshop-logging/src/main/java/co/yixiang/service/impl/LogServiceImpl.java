package co.yixiang.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import co.yixiang.domain.Log;
import co.yixiang.service.LogService;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.StringUtils;
import co.yixiang.repository.LogRepository;
import co.yixiang.service.dto.LogQueryCriteria;
import co.yixiang.service.mapper.LogErrorMapper;
import co.yixiang.service.mapper.LogSmallMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogErrorMapper logErrorMapper;

    @Autowired
    private LogSmallMapper logSmallMapper;

    private final String LOGINPATH = "login";

    @Override
    public Object findAllByPageable(String nickname, Pageable pageable) {
        Page<Map> page = logRepository.findAllByPageable(nickname,pageable);
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",page.getContent());
        map.put("totalElements",page.getTotalElements());
        return map;
    }

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable){
        Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)),pageable);
        if ("ERROR".equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(logErrorMapper::toDto));
        }
        return page;
    }

    @Override
    public Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)),pageable);
        return PageUtil.toPage(page.map(logSmallMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String ip, ProceedingJoinPoint joinPoint,
                     Log log,Long uid){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        co.yixiang.aop.log.Log aopLog = method.getAnnotation(co.yixiang.aop.log.Log.class);

        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        //类型 0-后台 1-前台
        log.setType(aopLog.type());
        if(uid != null) {
            log.setUid(uid);
        }


        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 获取IP地址
        log.setRequestIp(ip);

        if(LOGINPATH.equals(signature.getName())){
            try {
                JSONObject jsonObject = new JSONObject(argValues[0]);
                username = jsonObject.get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params + " }");
        logRepository.save(log);
    }

    @Override
    public Object findByErrDetail(Long id) {
        return Dict.create().set("exception",logRepository.findExceptionById(id));
    }
}
