/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.coupon.rest;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.aop.NoRepeatSubmit;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.vo.StoreCouponUserVo;
import co.yixiang.modules.activity.vo.YxStoreCouponIssueQueryVo;
import co.yixiang.modules.activity.vo.YxStoreCouponUserQueryVo;
import co.yixiang.modules.coupon.param.YxStoreCouponQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券 todo
 * </p>
 *
 * @author hupeng
 * @since 2019-10-02
 */
@Slf4j
@RestController
@Api(value = "优惠券", tags = "营销:优惠券")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CouponController {

    private final YxStoreCouponIssueService couponIssueService;
    private final YxStoreCouponUserService storeCouponUserService;

    /**
     * 可领取优惠券列表
     */
    @AuthCheck
    @GetMapping("/coupons")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productId", value = "产品ID", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "优惠券类型 0通用券 1商品券 2内部券", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "可领取优惠券列表",notes = "可领取优惠券列表")
    public ApiResult<List<YxStoreCouponIssueQueryVo>> getList(@RequestParam(value = "page",defaultValue = "1") int page,
                                                               @RequestParam(value = "limit",defaultValue = "10") int limit,
                                                               @RequestParam(value = "productId",required = false) Long productId,
                                                               @RequestParam(value = "type",required = false) Integer type){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(couponIssueService.getCouponList(page, limit,uid,productId,type));
    }

    /**
     * 领取优惠券
     */
    @AppLog(value = "领取优惠券", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/coupon/receive")
    @ApiOperation(value = "领取优惠券",notes = "领取优惠券")
    public ApiResult<Boolean> receive(@Validated @RequestBody YxStoreCouponQueryParam param){
        Long uid = LocalUser.getUser().getUid();
        if(!NumberUtil.isNumber(param.getCouponId())){
           throw new YshopException("参数非法");
        }
        Integer couponId = Integer.valueOf(param.getCouponId());
        couponIssueService.issueUserCoupon(couponId,uid);
        return ApiResult.ok();
    }

    /**
     * 用户已领取优惠券
     */
    @AppLog(value = "查看已领取优惠券", type = 1)
    @AuthCheck
    @GetMapping("/coupons/user/{type}")
    @ApiOperation(value = "用户已领取优惠券",notes = "用户已领取优惠券")
    public ApiResult<List<YxStoreCouponUserQueryVo>> getUserList(){
        Long uid = LocalUser.getUser().getUid();
        List<YxStoreCouponUserQueryVo> list = storeCouponUserService.getUserCoupon(uid);
        return ApiResult.ok(list);
    }

    /**
     * 用户已领取优惠券pc
     */
    @AppLog(value = "用户已领取优惠券pc", type = 1)
    @AuthCheck
    @GetMapping("/coupons/user/pc/{type}")
    @ApiOperation(value = "用户已领取优惠券pc",notes = "用户已领取优惠券pc")
    public ApiResult<Object> getUserPCList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit, @PathVariable(value = "type") Integer type){
        Long uid = LocalUser.getUser().getUid();
        Map<String, Object> map = storeCouponUserService.getUserPCCoupon(uid,page,limit,type);
        Long total = (Long) map.get("total");
        Long totalPage = (Long) map.get("totalPage");
        return ApiResult.resultPage(total.intValue(), totalPage.intValue(), map.get("list"));
    }

    /**
     * 优惠券 订单获取
     */
    @AuthCheck
    @GetMapping("/coupons/order/{cartIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartIds", value = "购物车ID,多个用,分割", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "优惠券订单获取",notes = "优惠券订单获取")
    public ApiResult<List<StoreCouponUserVo>> orderCoupon(@PathVariable String cartIds){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(storeCouponUserService.beUsableCouponList(uid,cartIds));
    }


}

