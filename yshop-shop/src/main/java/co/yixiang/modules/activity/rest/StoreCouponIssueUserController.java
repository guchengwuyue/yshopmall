/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
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
 * @date 2019-11-09
 */
@Api(tags = "商城:优惠券前台用户领取记录管理")
@RestController
@RequestMapping("api")
public class StoreCouponIssueUserController {

    private final YxStoreCouponIssueUserService yxStoreCouponIssueUserService;

    public StoreCouponIssueUserController(YxStoreCouponIssueUserService yxStoreCouponIssueUserService) {
        this.yxStoreCouponIssueUserService = yxStoreCouponIssueUserService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_SELECT')")
    public ResponseEntity getYxStoreCouponIssueUsers(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxStoreCouponIssueUserService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponIssueUser resources) {
        return new ResponseEntity(yxStoreCouponIssueUserService.save(resources), HttpStatus.CREATED);
    }

    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponIssueUser resources) {
        yxStoreCouponIssueUserService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCouponIssueUser/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {

        yxStoreCouponIssueUserService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
