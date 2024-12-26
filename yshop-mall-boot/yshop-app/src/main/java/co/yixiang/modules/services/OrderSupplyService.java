/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.services;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.enums.AppFromEnum;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.OrderLogEnum;
import co.yixiang.enums.PayTypeEnum;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.order.dto.OrderExtendDto;
import co.yixiang.modules.order.param.ComputeOrderParam;
import co.yixiang.modules.order.service.YxStoreOrderCartInfoService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.dto.ProductAttrDto;
import co.yixiang.modules.order.service.dto.ProductDto;
import co.yixiang.modules.order.vo.OrderCartInfoVo;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.mp.service.WeixinPayService;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 订单提供者服务
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/22
 **/
@Slf4j
@Service
@AllArgsConstructor
public class OrderSupplyService {

    private final YxStoreOrderService storeOrderService;
    private final YxStorePinkService storePinkService;
    private final YxStoreOrderCartInfoService orderCartInfoService;
    private final WeixinPayService weixinPayService;


    /**
     * 返回订单产品信息
     * @param unique 订单唯一值
     * @return OrderCartInfoVo
     */
    public OrderCartInfoVo getProductOrder(String unique){
        YxStoreOrderCartInfo orderCartInfo = orderCartInfoService.findByUni(unique);

        YxStoreCartQueryVo cartInfo = JSONObject.parseObject(orderCartInfo.getCartInfo(),
                YxStoreCartQueryVo.class);

        ProductDto productDTO = new ProductDto();
        productDTO.setImage(cartInfo.getProductInfo().getImage());
        productDTO.setPrice(cartInfo.getProductInfo().getPrice().doubleValue());
        productDTO.setStoreName(cartInfo.getProductInfo().getStoreName());
        if(ObjectUtil.isNotEmpty(cartInfo.getProductInfo().getAttrInfo())){
            ProductAttrDto productAttrDTO = new ProductAttrDto();
            productAttrDTO.setImage(cartInfo.getProductInfo().getAttrInfo().getImage());
            productAttrDTO.setPrice(cartInfo.getProductInfo().getAttrInfo().getPrice().doubleValue());
            productAttrDTO.setProductId(cartInfo.getProductInfo().getAttrInfo().getProductId());
            productAttrDTO.setSku(cartInfo.getProductInfo().getAttrInfo().getSku());
            productDTO.setAttrInfo(productAttrDTO);
        }


        return OrderCartInfoVo.builder()
                .cartNum(cartInfo.getCartNum())
                .combinationId(cartInfo.getCombinationId())
                .orderId(storeOrderService.getById(orderCartInfo.getOid()).getOrderId())
                .seckillId(cartInfo.getSeckillId())
                .productInfo(productDTO)
                .build();
    }

    /**
     * 订单检测
     * @param uid uid
     * @param key 缓存值
     * @param param ComputeOrderParam
     * @return map
     */
    public Map<String,Object> check(Long uid,String key, ComputeOrderParam param){
        Map<String,Object> map = Maps.newHashMap();
        if(StrUtil.isBlank(key)) {
            throw new YshopException("参数错误");
        }
        YxStoreOrderQueryVo storeOrder = storeOrderService.getOrderInfo(key,uid);
        if(ObjectUtil.isNotNull(storeOrder)){

            OrderExtendDto orderExtendDTO = OrderExtendDto.builder()
                    .key(key)
                    .orderId(storeOrder.getOrderId())
                    .build();
            map.put("status", OrderLogEnum.EXTEND_ORDER.getValue());
            map.put("result", orderExtendDTO);
            map.put("msg",OrderLogEnum.EXTEND_ORDER.getDesc());
        }

        // 拼团
        if(StrUtil.isNotBlank(param.getPinkId()) && NumberUtil.isNumber(param.getPinkId())){
            Long pinkId = Long.valueOf(param.getPinkId());
            if(pinkId > 0){
                YxStoreOrder yxStoreOrder = storeOrderService.getOrderPink(pinkId,uid);
                if(yxStoreOrder != null){
                    map.put("status",OrderLogEnum.PINK_ORDER_FAIL_1.getValue());
                    OrderExtendDto orderExtendDTO = new OrderExtendDto();
                    orderExtendDTO.setOrderId(yxStoreOrder.getOrderId());
                    map.put("result",orderExtendDTO);
                    map.put("msg",OrderLogEnum.PINK_ORDER_FAIL_1.getDesc());
                }
                if(yxStoreOrder != null && storePinkService.getIsPinkUid(pinkId,uid)){
                    map.put("status",OrderLogEnum.PINK_ORDER_FAIL_2.getValue());
                    OrderExtendDto orderExtendDTO = new OrderExtendDto();
                    orderExtendDTO.setOrderId(yxStoreOrder.getOrderId());
                    map.put("result",orderExtendDTO);
                    map.put("msg",OrderLogEnum.PINK_ORDER_FAIL_2.getDesc());
                }
            }

        }


        return map;
    }

    /**
     * 支付
     * @param map     map
     * @param orderId 订单号
     * @param uid     uid
     * @param payType 支付方式
     * @param from 来源
     * @param orderDTO orderDTO
     * @return map
     */
    public Map<String, Object> goPay(Map<String, Object> map, String orderId, Long uid, String payType,
                                     String from, OrderExtendDto orderDTO){
        switch (PayTypeEnum.toType(payType)){
            case WEIXIN:
                Map<String,String> jsConfig = new HashMap<>();
                if(AppFromEnum.WEIXIN_H5.getValue().equals(from)){
                    WxPayMwebOrderResult wxPayMwebOrderResult = (WxPayMwebOrderResult)weixinPayService
                            .unifyPay(orderId,from, BillDetailEnum.TYPE_3.getValue(),"H5商品购买");

                    log.info("wxPayMwebOrderResult:{}",wxPayMwebOrderResult);
                    jsConfig.put("mweb_url",wxPayMwebOrderResult.getMwebUrl());
                    orderDTO.setJsConfig(jsConfig);
                    map.put("result",orderDTO);
                    map.put("status","WECHAT_H5_PAY");
                    map.put("payMsg","订单创建成功");
                    return map;
                } else if(AppFromEnum.ROUNTINE.getValue().equals(from)){
                    map.put("status","WECHAT_PAY");
                    WxPayMpOrderResult wxPayMpOrderResult = (WxPayMpOrderResult)weixinPayService
                            .unifyPay(orderId,from, BillDetailEnum.TYPE_3.getValue(),"小程序商品购买");
                    jsConfig.put("appId",wxPayMpOrderResult.getAppId());
                    jsConfig.put("timeStamp",wxPayMpOrderResult.getTimeStamp());
                    jsConfig.put("paySign",wxPayMpOrderResult.getPaySign());
                    jsConfig.put("nonceStr",wxPayMpOrderResult.getNonceStr());
                    jsConfig.put("package",wxPayMpOrderResult.getPackageValue());
                    jsConfig.put("signType",wxPayMpOrderResult.getSignType());
                    orderDTO.setJsConfig(jsConfig);
                    map.put("payMsg","订单创建成功");
                    map.put("result",orderDTO);
                    return map;
                }else if(AppFromEnum.APP.getValue().equals(from)){//app支付
                    map.put("status","WECHAT_APP_PAY");
                    WxPayAppOrderResult wxPayAppOrderResult = (WxPayAppOrderResult)weixinPayService
                            .unifyPay(orderId,from, BillDetailEnum.TYPE_3.getValue(),"APP商品购买");
                    jsConfig.put("appid",wxPayAppOrderResult.getAppId());
                    jsConfig.put("partnerid",wxPayAppOrderResult.getPartnerId());
                    jsConfig.put("prepayid",wxPayAppOrderResult.getPrepayId());
                    jsConfig.put("package",wxPayAppOrderResult.getPackageValue());
                    jsConfig.put("noncestr",wxPayAppOrderResult.getNonceStr());
                    jsConfig.put("timestamp",wxPayAppOrderResult.getTimeStamp());
                    jsConfig.put("sign",wxPayAppOrderResult.getSign());
                    orderDTO.setJsConfig(jsConfig);
                    map.put("result",orderDTO);
                    map.put("payMsg","订单创建成功");
                    return map;
                }else if(AppFromEnum.PC.getValue().equals(from)){ //扫码支付
                    map.put("status","WECHAT_PC_PAY");
                    WxPayNativeOrderResult wxPayNativeOrderResult = (WxPayNativeOrderResult)weixinPayService
                            .unifyPay(orderId,from, BillDetailEnum.TYPE_3.getValue(),"pc商品购买");
                    jsConfig.put("codeUrl",wxPayNativeOrderResult.getCodeUrl());
                    orderDTO.setJsConfig(jsConfig);
                    map.put("result",orderDTO);
                    map.put("payMsg","订单创建成功");
                    return map;
                }
                else{//公众号
                    map.put("status","WECHAT_PAY");
                    WxPayMpOrderResult wxPayMpOrderResult = (WxPayMpOrderResult)weixinPayService
                            .unifyPay(orderId,from, BillDetailEnum.TYPE_3.getValue(),"公众号商品购买");

                    log.info("WxPayMpOrderResult:{}",wxPayMpOrderResult);

                    jsConfig.put("appId",wxPayMpOrderResult.getAppId());
                    jsConfig.put("timestamp",wxPayMpOrderResult.getTimeStamp());
                    jsConfig.put("nonceStr",wxPayMpOrderResult.getNonceStr());
                    jsConfig.put("package",wxPayMpOrderResult.getPackageValue());
                    jsConfig.put("signType",wxPayMpOrderResult.getSignType());
                    jsConfig.put("paySign",wxPayMpOrderResult.getPaySign());
                    orderDTO.setJsConfig(jsConfig);
                    map.put("result",orderDTO);
                    map.put("payMsg","订单创建成功");

                    return map;
                }
            case YUE:
                storeOrderService.yuePay(orderId,uid);
                map.put("payMsg","余额支付成功");
                return map;
            case INTEGRAL:
                storeOrderService.integralPay(orderId,uid);
                map.put("payMsg","积分兑换成功");
                return map;
            default:
        }

        map.put("payMsg","订单生成失败");
        return map;
    }




}
