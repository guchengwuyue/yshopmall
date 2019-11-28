package co.yixiang.modules.quartz.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.quartz.domain.QuartzJob;
import co.yixiang.modules.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.quartz.service.dto.JobQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    @Autowired
    private QuartzJobService quartzJobService;

    @Log("查询定时任务")
    @GetMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/jobLogs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobLogs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobService.queryAllLog(criteria,pageable), HttpStatus.OK);
    }

    @Log("新增定时任务")
    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody QuartzJob resources){
        if(ObjectUtil.isNotNull(resources)) throw new BadRequestException("演示环境禁止操作");
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(quartzJobService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改定时任务")
    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity update(@Validated(QuartzJob.Update.class) @RequestBody QuartzJob resources){
        if(ObjectUtil.isNotNull(resources)) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("更改定时任务状态")
    @PutMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity updateIsPause(@PathVariable Long id){
        if(id > 0) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.updateIsPause(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("执行定时任务")
    @PutMapping(value = "/jobs/exec/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity execution(@PathVariable Long id){
        if(id > 0) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.execution(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除定时任务")
    @DeleteMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        if(id > 0) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.delete(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
