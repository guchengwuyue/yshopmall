/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.quartz.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.quartz.domain.QuartzJob;
import co.yixiang.modules.quartz.service.QuartzJobService;
import co.yixiang.modules.quartz.service.QuartzLogService;
import co.yixiang.modules.quartz.service.dto.QuartzJobDto;
import co.yixiang.modules.quartz.service.dto.QuartzJobQueryCriteria;
import co.yixiang.modules.quartz.service.dto.QuartzLogDto;
import co.yixiang.modules.quartz.service.dto.QuartzLogQueryCriteria;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hupeng
 * @date 2019-01-07
 */
@Slf4j
@RestController
@Api(tags = "系统:定时任务管理")
@RequestMapping("/api/jobs")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    private final QuartzJobService quartzJobService;
    private final IGenerator generator;
    private final QuartzLogService quartzLogService;


    public QuartzJobController(QuartzJobService quartzJobService, IGenerator generator, QuartzLogService quartzLogService) {
        this.quartzJobService = quartzJobService;
        this.generator = generator;
        this.quartzLogService = quartzLogService;
    }

    @Log("查询定时任务")
    @ApiOperation("查询定时任务")
    @GetMapping
    @PreAuthorize("@el.check('admin','timing:list')")
    public ResponseEntity<Object> getJobs(QuartzJobQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(quartzJobService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("导出任务数据")
    @ApiOperation("导出任务数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','timing:list')")
    public void download(HttpServletResponse response, QuartzJobQueryCriteria criteria) throws IOException {
        quartzJobService.download(generator.convert(quartzJobService.queryAll(criteria), QuartzJobDto.class), response);
    }

    @Log("导出日志数据")
    @ApiOperation("导出日志数据")
    @GetMapping(value = "/logs/download")
    @PreAuthorize("@el.check('admin','timing:list')")
    public void downloadLog(HttpServletResponse response, QuartzLogQueryCriteria criteria) throws IOException {
        quartzLogService.download(generator.convert(quartzLogService.queryAll(criteria), QuartzLogDto.class), response);
    }

    @ApiOperation("查询任务执行日志")
    @GetMapping(value = "/logs")
    @PreAuthorize("@el.check('admin','timing:list')")
    public ResponseEntity<Object> getJobLogs(QuartzLogQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(quartzLogService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ForbidSubmit
    @Log("新增定时任务")
    @ApiOperation("新增定时任务")
    @PostMapping
    @PreAuthorize("@el.check('admin','timing:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody QuartzJob resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        return new ResponseEntity<>(quartzJobService.save(resources), HttpStatus.CREATED);
    }

    @ForbidSubmit
    @Log("修改定时任务")
    @ApiOperation("修改定时任务")
    @PutMapping
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody QuartzJob resources) {
        quartzJobService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("更改定时任务状态")
    @ApiOperation("更改定时任务状态")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> updateIsPause(@PathVariable Long id) {
        quartzJobService.updateIsPause(quartzJobService.getOne(new LambdaQueryWrapper<QuartzJob>()
                .eq(QuartzJob::getId, id)));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("执行定时任务")
    @ApiOperation("执行定时任务")
    @PutMapping(value = "/exec/{id}")
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> execution(@PathVariable Long id) {
        quartzJobService.execution(quartzJobService.getOne(new LambdaQueryWrapper<QuartzJob>().eq(QuartzJob::getId, id)));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除定时任务")
    @ApiOperation("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@el.check('admin','timing:del')")
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        quartzJobService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
