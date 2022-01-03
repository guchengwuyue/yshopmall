/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.quartz.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.quartz.domain.QuartzJob;
import co.yixiang.modules.quartz.service.QuartzJobService;
import co.yixiang.modules.quartz.service.dto.QuartzJobDto;
import co.yixiang.modules.quartz.service.dto.QuartzJobQueryCriteria;
import co.yixiang.modules.quartz.service.mapper.QuartzJobMapper;
import co.yixiang.modules.quartz.utils.QuartzManage;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "quartzJob")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzJobServiceImpl extends BaseServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {

    private final IGenerator generator;
    private final QuartzManage quartzManage;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(QuartzJobQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<QuartzJob> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), QuartzJobDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<QuartzJob> queryAll(QuartzJobQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(QuartzJob.class, criteria));
    }


    @Override
    public void download(List<QuartzJobDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QuartzJobDto quartzJob : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("Spring Bean名称", quartzJob.getBeanName());
            map.put("cron 表达式", quartzJob.getCronExpression());
            map.put("状态：1暂停、0启用", quartzJob.getIsPause());
            map.put("任务名称", quartzJob.getJobName());
            map.put("方法名称", quartzJob.getMethodName());
            map.put("参数", quartzJob.getParams());
            map.put("备注", quartzJob.getRemark());
            map.put("创建日期", quartzJob.getCreateTime());
            map.put("Spring Bean名称", quartzJob.getBeanName());
            map.put("cron 表达式", quartzJob.getCronExpression());
            map.put("状态：1暂停、0启用", quartzJob.getIsPause());
            map.put("任务名称", quartzJob.getJobName());
            map.put("方法名称", quartzJob.getMethodName());
            map.put("参数", quartzJob.getParams());
            map.put("备注", quartzJob.getRemark());
            map.put("创建日期", quartzJob.getCreateTime());
            map.put("Spring Bean名称", quartzJob.getBeanName());
            map.put("cron 表达式", quartzJob.getCronExpression());
            map.put("状态：1暂停、0启用", quartzJob.getIsPause());
            map.put("任务名称", quartzJob.getJobName());
            map.put("方法名称", quartzJob.getMethodName());
            map.put("参数", quartzJob.getParams());
            map.put("备注", quartzJob.getRemark());
            map.put("创建日期", quartzJob.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 更改定时任务状态
     *
     * @param quartzJob /
     */
    @Override
    public void updateIsPause(QuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
        } else {
            quartzManage.pauseJob(quartzJob);
        }
        quartzJob.setIsPause(!quartzJob.getIsPause());
        this.saveOrUpdate(quartzJob);
    }

    @Override
    public boolean save(QuartzJob quartzJob) {
        quartzManage.addJob(quartzJob);
        return retBool(baseMapper.insert(quartzJob));
    }

    @Override
    public boolean updateById(QuartzJob quartzJob) {
        quartzManage.updateJobCron(quartzJob);
        return retBool(baseMapper.updateById(quartzJob));
    }

    /**
     * 立即执行定时任务
     *
     * @param quartzJob /
     */
    @Override
    public void execution(QuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        quartzManage.runJobNow(quartzJob);
    }

    /**
     * 查询启用的任务
     *
     * @return List
     */
    @Override
    public List<QuartzJob> findByIsPauseIsFalse() {
        QuartzJobQueryCriteria criteria = new QuartzJobQueryCriteria();
        criteria.setIsPause(false);
        return baseMapper.selectList(QueryHelpPlus.getPredicate(QuartzJob.class, criteria));
    }

    @Override
    public void removeByIds(List<Integer> idList) {
        idList.forEach(id -> {
            QuartzJob quartzJob = baseMapper.selectById(id);
            quartzManage.deleteJob(quartzJob);
        });
        baseMapper.deleteBatchIds(idList);
    }
}
