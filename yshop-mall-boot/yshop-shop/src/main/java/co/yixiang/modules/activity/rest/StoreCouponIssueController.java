/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.aop.ForbidSubmit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author hupeng
* @date 2019-11-09
*/
@Api(tags = "商城:优惠券发布管理")
@RestController
@RequestMapping("api")
public class StoreCouponIssueController {

    private final YxStoreCouponIssueService yxStoreCouponIssueService;

    public StoreCouponIssueController(YxStoreCouponIssueService yxStoreCouponIssueService) {
        this.yxStoreCouponIssueService = yxStoreCouponIssueService;
    }

    @Log("查询已发布")
    @ApiOperation(value = "查询已发布")
    @GetMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_SELECT')")
    public ResponseEntity getYxStoreCouponIssues(YxStoreCouponIssueQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxStoreCouponIssueService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("发布")
    @ApiOperation(value = "发布")
    @PostMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponIssue resources){
        if(resources.getTotalCount() > 0) {
            resources.setRemainCount(resources.getTotalCount());
        }
        return new ResponseEntity<>(yxStoreCouponIssueService.save(resources),HttpStatus.CREATED);
    }

    @Log("修改状态")
    @ApiOperation(value = "修改状态")
    @PutMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponIssue resources){
        yxStoreCouponIssueService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCouponIssue/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreCouponIssueService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
