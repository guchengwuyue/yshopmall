/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.enums.CouponEnum;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponQueryCriteria;
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
@Api(tags = "商城:优惠券管理")
@RestController
@RequestMapping("api")
public class StoreCouponController {

    private final YxStoreCouponService yxStoreCouponService;

    public StoreCouponController(YxStoreCouponService yxStoreCouponService) {
        this.yxStoreCouponService = yxStoreCouponService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCoupon")
    @PreAuthorize("@el.check('admin','YXSTORECOUPON_ALL','YXSTORECOUPON_SELECT')")
    public ResponseEntity getYxStoreCoupons(YxStoreCouponQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxStoreCouponService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxStoreCoupon")
    @PreAuthorize("@el.check('admin','YXSTORECOUPON_ALL','YXSTORECOUPON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCoupon resources){
        if(CouponEnum.TYPE_1.getValue().equals(resources.getType())
                && StrUtil.isEmpty(resources.getProductId())){
            throw new YshopException("请选择商品");
        }
        if(resources.getCouponPrice().compareTo(resources.getUseMinPrice()) >= 0) {
            throw new YshopException("优惠券金额不能高于最低消费金额");
        }
        return new ResponseEntity<>(yxStoreCouponService.save(resources),HttpStatus.CREATED);
    }

    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxStoreCoupon")
    @PreAuthorize("@el.check('admin','YXSTORECOUPON_ALL','YXSTORECOUPON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCoupon resources){
        if(CouponEnum.TYPE_1.getValue().equals(resources.getType())
                && StrUtil.isEmpty(resources.getProductId())){
            throw new YshopException("请选择商品");
        }
        if(resources.getCouponPrice().compareTo(resources.getUseMinPrice()) >= 0) {
            throw new YshopException("优惠券金额不能高于最低消费金额");
        }
        yxStoreCouponService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCoupon/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECOUPON_ALL','YXSTORECOUPON_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreCouponService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
