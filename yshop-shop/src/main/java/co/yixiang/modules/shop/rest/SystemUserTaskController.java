/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxSystemUserTask;
import co.yixiang.modules.shop.service.YxSystemUserTaskService;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng
 * @date 2019-12-04
 */
@Api(tags = "商城:用户任务管理")
@RestController
@RequestMapping("api")
public class SystemUserTaskController {

    private final YxSystemUserTaskService yxSystemUserTaskService;

    public SystemUserTaskController(YxSystemUserTaskService yxSystemUserTaskService) {
        this.yxSystemUserTaskService = yxSystemUserTaskService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_SELECT')")
    public ResponseEntity getYxSystemUserTasks(YxSystemUserTaskQueryCriteria criteria,
                                               Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, "level_id");
        Pageable pageableT = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        return new ResponseEntity(yxSystemUserTaskService.queryAll(criteria, pageableT),
                HttpStatus.OK);
    }

    @NoRepeatSubmit
    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxSystemUserTask resources) {
        return new ResponseEntity(yxSystemUserTaskService.save(resources), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxSystemUserTask resources) {

        yxSystemUserTaskService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxSystemUserTask/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {

        yxSystemUserTaskService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
