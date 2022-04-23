/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.dto.YxStoreBargainQueryCriteria;
import co.yixiang.modules.aop.NoRepeatSubmit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng
 * @date 2019-12-22
 */
@Api(tags = "商城:砍价管理")
@RestController
@RequestMapping("api")
public class StoreBargainController {

    private final YxStoreBargainService yxStoreBargainService;

    public StoreBargainController(YxStoreBargainService yxStoreBargainService) {
        this.yxStoreBargainService = yxStoreBargainService;
    }

    @Log("查询砍价")
    @ApiOperation(value = "查询砍价")
    @GetMapping(value = "/yxStoreBargain")
    @PreAuthorize("hasAnyRole('admin','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_SELECT')")
    public ResponseEntity getYxStoreBargains(YxStoreBargainQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxStoreBargainService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @Log("修改砍价")
    @ApiOperation(value = "修改砍价")
    @PutMapping(value = "/yxStoreBargain")
    @PreAuthorize("hasAnyRole('admin','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreBargain resources) {
        if (ObjectUtil.isNull(resources.getId())) {
            return new ResponseEntity(yxStoreBargainService.save(resources), HttpStatus.CREATED);
        } else {
            yxStoreBargainService.saveOrUpdate(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @NoRepeatSubmit
    @Log("删除砍价")
    @ApiOperation(value = "删除砍价")
    @DeleteMapping(value = "/yxStoreBargain/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {
        yxStoreBargainService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
