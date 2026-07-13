/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.order.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.common.aop.NoRepeatSubmit;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.enums.*;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.modules.mp.domain.YxWechatTemplate;
import co.yixiang.modules.mp.service.WeixinPayService;
import co.yixiang.modules.mp.service.YxWechatTemplateService;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.order.dto.OrderExtendDto;
import co.yixiang.modules.order.param.*;
import co.yixiang.modules.order.service.YxStoreOrderCartInfoService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.YxStoreOrderStatusService;
import co.yixiang.modules.order.service.dto.YxStoreOrderDto;
import co.yixiang.modules.order.vo.ComputeVo;
import co.yixiang.modules.order.vo.ConfirmOrderVo;
import co.yixiang.modules.order.vo.OrderCartInfoVo;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.services.CreatShareProductService;
import co.yixiang.modules.services.OrderSupplyService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.tools.express.ExpressService;
import co.yixiang.modules.tools.express.config.ExpressAutoConfiguration;
import co.yixiang.modules.tools.express.dao.ExpressInfo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vdurmont.emoji.EmojiParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-27
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "订单模块", tags = "商城:订单模块")
public class StoreOrderController {

    private final YxStoreOrderService storeOrderService;
    private final OrderSupplyService orderSupplyService;
    private final CreatShareProductService creatShareProductService;
    private final YxWechatTemplateService yxWechatTemplateService;
    private final YxStoreOrderStatusService orderStatusService;

    private final YxStoreOrderCartInfoService storeOrderCartInfoService;
    private final WeixinPayService weixinPayService;

    @Value("${file.path}")
    private String path;


    /**
     * 订单确认
     */
    @AppLog(value = "订单确认", type = 1)
    @AuthCheck
    @PostMapping("/order/confirm")
    @ApiOperation(value = "订单确认", notes = "订单确认")
    public ApiResult<ConfirmOrderVo> confirm(@Validated @RequestBody ConfirmOrderParam param) {
        YxUser yxUser = LocalUser.getUser();
        return ApiResult.ok(storeOrderService.confirmOrder(yxUser, param.getCartId()));

    }

    /**
     * 计算订单金额
     */
    @AuthCheck
    @PostMapping("/order/computed/{key}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "唯一的key", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "计算订单金额", notes = "计算订单金额")
    public ApiResult<Map<String, Object>> computedOrder(@Validated @RequestBody ComputeOrderParam param,
                                                        @PathVariable String key) {
        YxUser yxUser = LocalUser.getUser();
        Map<String, Object> map = orderSupplyService.check(yxUser.getUid(), key, param);
        if (OrderLogEnum.EXTEND_ORDER.getValue().equals(map.get("status"))
                || OrderLogEnum.PINK_ORDER_FAIL_1.getValue().equals(map.get("status"))
                || OrderLogEnum.PINK_ORDER_FAIL_2.getValue().equals(map.get("status"))) {
            return ApiResult.ok(map, map.get("msg").toString());
        }

        ComputeVo computeVo = storeOrderService.computedOrder(yxUser, key,
                param.getCouponId(),
                param.getUseIntegral(),
                param.getShippingType(),
                param.getAddressId());

        map.put("result", computeVo);
        map.put("status", OrderLogEnum.NONE_ORDER.getValue());
        return ApiResult.ok(map);
    }

    /**
     * 订单创建
     */
    @AppLog(value = "订单创建", type = 1)
    @AuthCheck
    @NoRepeatSubmit
    @PostMapping("/order/create/{key}")
    @ApiOperation(value = "订单创建", notes = "订单创建")
    public ApiResult<Map<String, Object>> create(@Valid @RequestBody OrderParam param,
                                                 @PathVariable String key) {
        YxUser yxUser = LocalUser.getUser();
        ComputeOrderParam computeOrderParam = new ComputeOrderParam();
        BeanUtil.copyProperties(param, computeOrderParam);
        Map<String, Object> map = orderSupplyService.check(yxUser.getUid(), key, computeOrderParam);
        if (OrderLogEnum.EXTEND_ORDER.getValue().equals(map.get("status"))
                || OrderLogEnum.PINK_ORDER_FAIL_2.getValue().equals(map.get("status"))
                || OrderLogEnum.PINK_ORDER_FAIL_1.getValue().equals(map.get("status"))) {
            return ApiResult.ok(map, map.get("msg").toString());
        }


        //创建订单
        YxStoreOrder order = storeOrderService.createOrder(yxUser, key, param);

        if (ObjectUtil.isNull(order)) {
            throw new YshopException("订单生成失败");
        }

        String orderId = order.getOrderId();

        OrderExtendDto orderDTO = new OrderExtendDto();
        orderDTO.setKey(key);
        orderDTO.setOrderId(orderId);
        map.put("status", OrderLogEnum.CREATE_ORDER_SUCCESS.getValue());
        map.put("result", orderDTO);
        map.put("createTime", order.getCreateTime());

        //开始处理支付
        //处理金额为0的情况
        if(order.getPayPrice().compareTo(BigDecimal.ZERO) <= 0&&!param.getPayType().equals(PayTypeEnum.INTEGRAL.getValue())){
            storeOrderService.yuePay(orderId,yxUser.getUid());
            map.put("payMsg","支付成功");
            return ApiResult.ok(map,"支付成功");
        }

        orderSupplyService.goPay(map,orderId,yxUser.getUid(),
                param.getPayType(),param.getFrom(),orderDTO);
        return ApiResult.ok(map, map.get("payMsg").toString());
    }


    /**
     * 订单支付
     */
    @AppLog(value = "订单支付", type = 1)
    @AuthCheck
    @PostMapping("/order/pay")
    @ApiOperation(value = "订单支付", notes = "订单支付")
    public ApiResult<Map<String, Object>> pay(@Valid @RequestBody PayParam param) {
        Map<String, Object> map = new LinkedHashMap<>();
        Long uid = LocalUser.getUser().getUid();
        YxStoreOrderQueryVo storeOrder = storeOrderService
                .getOrderInfo(param.getUni(), uid);
        if (ObjectUtil.isNull(storeOrder)) {
            throw new YshopException("订单不存在");
        }

        if (OrderInfoEnum.REFUND_STATUS_1.getValue().equals(storeOrder.getPaid())) {
            throw new YshopException("该订单已支付");
        }

        String orderId = storeOrder.getOrderId();

        OrderExtendDto orderDTO = new OrderExtendDto();
        orderDTO.setOrderId(orderId);
        map.put("status", "SUCCESS");
        map.put("result", orderDTO);


        if (storeOrder.getPayPrice().compareTo(BigDecimal.ZERO) <= 0 && !param.getPaytype().equals(PayTypeEnum.INTEGRAL.getValue())) {
            storeOrderService.yuePay(orderId, uid);
            return ApiResult.ok(map, "支付成功");
        }

        //处理是否已经修改过订单价格，如果修改用新的单号去拉起支付
        if (StrUtil.isNotBlank(storeOrder.getExtendOrderId())) {
            orderId = storeOrder.getExtendOrderId();
        }


        orderSupplyService.goPay(map, orderId, uid, param.getPaytype(), param.getFrom(), orderDTO);

        return ApiResult.ok(map);
    }


    /**
     * 订单列表
     */
    @AppLog(value = "查看订单列表", type = 1)
    @AuthCheck
    @GetMapping("/order/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "商品状态,-1全部 默认为0未支付 1待发货 2待收货 3待评价 4已完成 5退款中 6已退款 7退款", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "订单列表", notes = "订单列表")
    public ApiResult<Object> orderList(@RequestParam(value = "type", defaultValue = "0") int type,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Long uid = LocalUser.getUser().getUid();
        Map<String, Object> map = storeOrderService.orderList(uid, type, page, limit);
        Long total = (Long) map.get("total");
        Long totalPage = (Long) map.get("totalPage");
        return ApiResult.resultPage(total.intValue(), totalPage.intValue(), map.get("list"));
    }


    /**
     * 订单详情
     */
    @AppLog(value = "查看订单详情", type = 1)
    @AuthCheck
    @GetMapping("/order/detail/{key}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "唯一的key", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "订单详情", notes = "订单详情")
    public ApiResult<YxStoreOrderQueryVo> detail(@PathVariable String key) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(key)) {
            throw new YshopException("参数错误");
        }
        YxStoreOrderQueryVo storeOrder = storeOrderService.getOrderInfo(key, uid);
        if (ObjectUtil.isNull(storeOrder)) {
            throw new YshopException("订单不存在");
        }
        storeOrder = creatShareProductService.handleQrcode(storeOrder, path);

        return ApiResult.ok(storeOrderService.handleOrder(storeOrder));
    }


    /**
     * 订单收货
     */
    @AppLog(value = "订单收货", type = 1)
    @AuthCheck
    @PostMapping("/order/take")
    @ApiOperation(value = "订单收货", notes = "订单收货")
    public ApiResult<Boolean> orderTake(@Validated @RequestBody DoOrderParam param) {
        Long uid = LocalUser.getUser().getUid();
        storeOrderService.takeOrder(param.getUni(), uid);
        return ApiResult.ok();
    }

    /**
     * 订单产品信息
     */
    @PostMapping("/order/product")
    @ApiOperation(value = "订单产品信息", notes = "订单产品信息")
    public ApiResult<OrderCartInfoVo> product(@Validated @RequestBody ProductOrderParam param) {
        return ApiResult.ok(orderSupplyService.getProductOrder(param.getUnique()));
    }

    /**
     * 订单评价
     */
    @AppLog(value = "订单评价", type = 1)
    @AuthCheck
    @NoRepeatSubmit
    @PostMapping("/order/comment")
    @ApiOperation(value = "订单评价", notes = "订单评价")
    public ApiResult<Boolean> comment(@Valid @RequestBody ProductReplyParam param) {
        YxUser user = LocalUser.getUser();
        YxStoreOrderCartInfo orderCartInfo = storeOrderCartInfoService
                .getOne(Wrappers.<YxStoreOrderCartInfo>lambdaQuery()
                        .eq(YxStoreOrderCartInfo::getUnique,param.getUnique()));
        storeOrderService.orderComment(orderCartInfo, user, param.getUnique(),
                param.getComment(),
                param.getPics(), param.getProductScore(), param.getServiceScore());

        //增加状态
        orderStatusService.create(orderCartInfo.getOid(),
                OrderLogEnum.EVAL_ORDER.getValue(),
                OrderLogEnum.EVAL_ORDER.getDesc());
        return ApiResult.ok();
    }

    /**
     * 订单评价
     */
    @AppLog(value = "订单评价", type = 1)
    @AuthCheck
    @NoRepeatSubmit
    @PostMapping("/order/comments")
    @ApiOperation(value = "订单评价", notes = "订单评价")
    public ApiResult<Boolean> comments(@Valid @RequestBody List<ProductReplyParam> param) {
        YxUser user = LocalUser.getUser();
        if (param.size() > 0) {
            YxStoreOrderCartInfo orderCartInfo = storeOrderCartInfoService
                    .getOne(Wrappers.<YxStoreOrderCartInfo>lambdaQuery()
                            .eq(YxStoreOrderCartInfo::getUnique,param.get(0).getUnique()));

            for (ProductReplyParam productReplyParam : param) {
                storeOrderService.orderComment(orderCartInfo , user, productReplyParam.getUnique(),
                        productReplyParam.getComment(),
                        productReplyParam.getPics(), productReplyParam.getProductScore(), productReplyParam.getServiceScore());
            }

            //增加状态
            orderStatusService.create(orderCartInfo.getOid(),
                    OrderLogEnum.EVAL_ORDER.getValue(),
                    OrderLogEnum.EVAL_ORDER.getDesc());
        }
        return ApiResult.ok();
    }


    /**
     * 订单删除
     */
    @AppLog(value = "订单删除", type = 1)
    @AuthCheck
    @PostMapping("/order/del")
    @ApiOperation(value = "订单删除", notes = "订单删除")
    public ApiResult<Boolean> orderDel(@Validated @RequestBody DoOrderParam param) {
        Long uid = LocalUser.getUser().getUid();
        storeOrderService.removeOrder(param.getUni(), uid);
        return ApiResult.ok();
    }

    /**
     * 订单退款理由
     */
    @GetMapping("/order/refund/reason")
    @ApiOperation(value = "订单退款理由", notes = "订单退款理由")
    public ApiResult<Object> refundReason() {
        ArrayList<String> list = new ArrayList<>();
        list.add("收货地址填错了");
        list.add("与描述不符");
        list.add("信息填错了，重新拍");
        list.add("收到商品损坏了");
        list.add("未按预定时间发货");
        list.add("其它原因");

        return ApiResult.ok(list);
    }

    /**
     * 订单退款审核
     */
    @AppLog(value = "订单退款审核", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/order/refund/verify")
    @ApiOperation(value = "订单退款审核", notes = "订单退款审核")
    public ApiResult<Boolean> refundVerify(@RequestBody RefundParam param) {
        Long uid = LocalUser.getUser().getUid();
        storeOrderService.orderApplyRefund(param.getRefundReasonWapExplain(),
                param.getRefundReasonWapImg(),
                EmojiParser.removeAllEmojis(param.getText()),
                param.getUni(), uid);
        return ApiResult.ok();
    }

    /**
     * 订单取消   未支付的订单回退积分,回退优惠券,回退库存
     */
    @AppLog(value = "订单取消", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/order/cancel")
    @ApiOperation(value = "订单取消", notes = "订单取消")
    public ApiResult<Boolean> cancelOrder(@Validated @RequestBody HandleOrderParam param) {
        Long uid = LocalUser.getUser().getUid();
        YxStoreOrderQueryVo orderInfo = storeOrderService.getOrderInfo(param.getId(), uid);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new YshopException("订单不存在");
        }
        if (orderInfo.getStatus() != 0) {
            throw new YshopException("订单不能取消");
        }
        if (orderInfo.getPaid() == 1) {
            BigDecimal bigDecimal = new BigDecimal("100");
            int payPrice = bigDecimal.multiply(orderInfo.getPayPrice()).intValue();
            weixinPayService.refundOrder(param.getId(),payPrice);
        }
        storeOrderService.cancelOrder(param.getId(), uid);
        return ApiResult.ok();
    }


    /**
     * 获取物流信息
     */
    @AuthCheck
    @PostMapping("/order/express")
    @ApiOperation(value = "获取物流信息", notes = "获取物流信息")
    public ApiResult<ExpressInfo> express(@RequestBody ExpressParam expressInfoDo) {

        //顺丰轨迹查询处理
        String lastFourNumber = "";
        if (expressInfoDo.getShipperCode().equals(ShipperCodeEnum.SF.getValue())) {
            YxStoreOrderQueryVo yxStoreOrderDto;
            yxStoreOrderDto = storeOrderService.getOrderInfo(expressInfoDo.getOrderCode(),null);
            lastFourNumber = yxStoreOrderDto.getUserPhone();
            if (lastFourNumber.length() == 11) {
                lastFourNumber = StrUtil.sub(lastFourNumber, lastFourNumber.length(), -4);
            }
        }

        ExpressService expressService = ExpressAutoConfiguration.expressService();
        ExpressInfo expressInfo = expressService.getExpressInfo(expressInfoDo.getOrderCode(),
                expressInfoDo.getShipperCode(), expressInfoDo.getLogisticCode(), lastFourNumber);
        if (!expressInfo.isSuccess()) {
            throw new YshopException(expressInfo.getReason());
        }
        return ApiResult.ok(expressInfo);
    }

    /**
     * 订单核销
     */
    @AppLog(value = "订单核销", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/order/order_verific")
    @ApiOperation(value = "订单核销", notes = "订单核销")
    public ApiResult<Object> orderVerify(@RequestBody OrderVerifyParam param) {
        Long uid = LocalUser.getUser().getUid();
        YxStoreOrderQueryVo orderQueryVo = storeOrderService.verifyOrder(param.getVerifyCode(),
                param.getIsConfirm(), uid);
        if (orderQueryVo != null) {
            return ApiResult.ok(orderQueryVo);
        }

        return ApiResult.ok("核销成功");
    }


    @AuthCheck
    @GetMapping("/order/getSubscribeTemplate")
    @ApiOperation(value = "获取订阅消息模板ID", notes = "获取订阅消息模板ID")
    public ApiResult<List<String>> getSubscribeTemplate() {
        List<YxWechatTemplate> yxWechatTemplate = yxWechatTemplateService.lambdaQuery()
                .eq(YxWechatTemplate::getType, "subscribe")
                .eq(YxWechatTemplate::getStatus, ShopCommonEnum.IS_STATUS_1.getValue()).list();
        List<String> temId = yxWechatTemplate.stream().map(tem -> tem.getTempid()).collect(Collectors.toList());
        return ApiResult.ok(temId);
    }

}

