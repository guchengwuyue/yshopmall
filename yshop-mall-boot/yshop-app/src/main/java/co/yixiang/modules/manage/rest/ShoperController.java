/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.manage.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.modules.manage.param.OrderPriceParam;
import co.yixiang.modules.manage.param.ShoperQueryParam;
import co.yixiang.modules.order.param.OrderDeliveryParam;
import co.yixiang.modules.order.param.OrderRefundParam;
import co.yixiang.modules.order.service.YxExpressService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.vo.OrderDataVo;
import co.yixiang.modules.order.vo.ShoperOrderTimeDataVo;
import co.yixiang.modules.order.vo.UserOrderCountVo;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
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

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShoperController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/25
 **/
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "商家管理", tags = "商城:商家管理")
public class ShoperController {

    private final YxStoreOrderService storeOrderService;
    private final YxExpressService expressService;

    /**
     * 订单数据统计
     */
    @AuthCheck
    @GetMapping("/admin/order/statistics")
    @ApiOperation(value = "订单数据统计",notes = "订单数据统计")
    public ApiResult<Map<String,Object>> statistics(){
        UserOrderCountVo userOrderCountVo  = storeOrderService.orderData(null);
        ShoperOrderTimeDataVo orderTimeDataVo = storeOrderService.getShoperOrderTimeData();

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("orderCount",userOrderCountVo);
        map.put("orderTimeCount",orderTimeDataVo);
        return ApiResult.ok(map);
    }

    /**
     * 订单每月统计数据
     */
    @AuthCheck
    @GetMapping("/admin/order/data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "订单每月统计数据",notes = "订单每月统计数据")
    public ApiResult<List<OrderDataVo>> data(@RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "limit",defaultValue = "10") int limit){
        return ApiResult.ok(storeOrderService.getOrderDataPriceCount(page,limit));
    }


    /**
     * 订单列表
     */
    @AppLog(value = "查看订单列表", type = 1)
    @AuthCheck
    @GetMapping("/admin/order/list")
    @ApiOperation(value = "订单列表",notes = "订单列表")
    public ApiResult<Object> orderList(ShoperQueryParam queryParam) {
        Map<String, Object> map = storeOrderService.orderList(null, queryParam.getStatus(),
                queryParam.getPage(), queryParam.getLimit());
        return ApiResult.ok(map.get("list"));
    }

    /**
     * 订单详情
     */
    @AppLog(value = "查看订单详情", type = 1)
    @AuthCheck
    @GetMapping("/admin/order/detail/{key}")
    @ApiOperation(value = "订单详情",notes = "订单详情")
    public ApiResult<YxStoreOrderQueryVo> orderDetail(@PathVariable String key){
        if(StrUtil.isEmpty(key)) {
            throw new YshopException("参数错误");
        }
        YxStoreOrderQueryVo storeOrder = storeOrderService.getOrderInfo(key,null);
        if(ObjectUtil.isNull(storeOrder)){
            throw new YshopException("订单不存在");
        }
        return ApiResult.ok(storeOrderService.handleOrder(storeOrder));
    }

    /**
     * 订单改价
     */
    @AppLog(value = "订单改价", type = 1)
    @AuthCheck
    @PostMapping("/admin/order/price")
    @ApiOperation(value = "订单改价",notes = "订单改价")
    public ApiResult<Boolean> orderPrice(@Validated @RequestBody OrderPriceParam param){
        storeOrderService.editOrderPrice(param.getOrderId(),param.getPrice());
        return ApiResult.ok();
    }

    /**
     * 快递公司
     */
    @GetMapping("/logistics")
    @ApiOperation(value = "快递公司",notes = "快递公司")
    public ApiResult<Object> express(){
        return ApiResult.ok(expressService.list());
    }


    /**
     * 订单发货
     */
    @AppLog(value = "订单发货", type = 1)
    @AuthCheck
    @PostMapping("/admin/order/delivery/keep")
    @ApiOperation(value = "订单发货",notes = "订单发货")
    public ApiResult<Boolean> orderDelivery(@Validated @RequestBody OrderDeliveryParam param){
        storeOrderService.orderDelivery(param.getOrderId(),param.getDeliveryId(),
                param.getDeliveryName(),param.getDeliveryType());
        return ApiResult.ok();
    }

    /**
     * 订单退款
     */
    @AppLog(value = "订单退款", type = 1)
    @AuthCheck
    @PostMapping("/admin/order/refund")
    @ApiOperation(value = "订单退款",notes = "订单退款")
    public ApiResult<Boolean> orderRefund(@Validated @RequestBody OrderRefundParam param){
        storeOrderService.orderRefund(param.getOrderId(),new BigDecimal(param.getPrice()),param.getType(), null);
        return ApiResult.ok();
    }


    /**
     * 订单交易额/订单数量时间chart统计
     */
    @Deprecated
    @GetMapping("/admin/order/time")
    @ApiOperation(value = "chart统计",notes = "chart统计")
    public ApiResult<Object> chartCount(ShoperQueryParam queryParam){
//        return ApiResult.ok(storeOrderService.chartCount(queryParam.getCate().intValue(),
//                queryParam.getType().intValue()));
        return null;
    }






}
