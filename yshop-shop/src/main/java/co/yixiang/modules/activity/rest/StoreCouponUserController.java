/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hupeng
 * @date 2019-11-10
 */
@Api(tags = "商城:优惠券发放记录管理")
@RestController
@RequestMapping("api")
public class StoreCouponUserController {

    private final YxStoreCouponUserService yxStoreCouponUserService;

    public StoreCouponUserController(YxStoreCouponUserService yxStoreCouponUserService) {
        this.yxStoreCouponUserService = yxStoreCouponUserService;
    }

    @Log("查询Y")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_SELECT')")
    public ResponseEntity getYxStoreCouponUsers(YxStoreCouponUserQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxStoreCouponUserService.queryAll(criteria, pageable), HttpStatus.OK);
    }


}
