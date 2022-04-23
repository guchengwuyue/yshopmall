/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
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
 * @date 2019-11-14
 */
@Api(tags = "商城:提现管理")
@RestController
@RequestMapping("api")
public class UserExtractController {

    private final YxUserExtractService yxUserExtractService;

    public UserExtractController(YxUserExtractService yxUserExtractService) {
        this.yxUserExtractService = yxUserExtractService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxUserExtract")
    @PreAuthorize("hasAnyRole('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_SELECT')")
    public ResponseEntity getYxUserExtracts(YxUserExtractQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxUserExtractService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @Log("操作提现")
    @ApiOperation(value = "操作提现")
    @PutMapping(value = "/yxUserExtract")
    @PreAuthorize("hasAnyRole('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUserExtract resources) {
        yxUserExtractService.doExtract(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
