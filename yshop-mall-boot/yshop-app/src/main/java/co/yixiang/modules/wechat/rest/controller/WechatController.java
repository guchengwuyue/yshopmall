/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.wechat.rest.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.api.ApiResult;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.enums.AfterSalesStatusEnum;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.PayMethodEnum;
import co.yixiang.enums.PayTypeEnum;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.sales.domain.StoreAfterSales;
import co.yixiang.modules.sales.service.StoreAfterSalesService;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.user.domain.YxUserRecharge;
import co.yixiang.modules.user.service.YxUserRechargeService;
import co.yixiang.modules.mp.config.WxMpConfiguration;
import co.yixiang.modules.mp.config.WxPayConfiguration;
import co.yixiang.modules.mp.config.WxMaConfiguration;
import co.yixiang.utils.BigNum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName WechatController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/5
 **/
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "微信模块", tags = "微信:微信模块")
public class WechatController {

    private final YxStoreOrderService orderService;
    private final YxSystemConfigService systemConfigService;
    private final YxUserRechargeService userRechargeService;
    private final StoreAfterSalesService storeAfterSalesService;


    /**
     * 微信分享配置
     */
    @GetMapping("/share")
    @ApiOperation(value = "微信分享配置",notes = "微信分享配置")
    public ApiResult<Map<String,Object>> share() {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("img",systemConfigService.getData(SystemConfigConstants.WECHAT_SHARE_IMG));
        map.put("title",systemConfigService.getData(SystemConfigConstants.WECHAT_SHARE_TITLE));
        map.put("synopsis",systemConfigService.getData(SystemConfigConstants.WECHAT_SHARE_SYNOPSIS));
        Map<String,Object> mapt = new LinkedHashMap<>();
        mapt.put("data",map);
        return ApiResult.ok(mapt);
    }

    /**
     * jssdk配置
     */
    @GetMapping("/wechat/config")
    @ApiOperation(value = "jssdk配置",notes = "jssdk配置")
    public ApiResult<Map<String,Object>> jsConfig(HttpServletRequest request) throws WxErrorException {
        WxMpService wxService = WxMpConfiguration.getWxMpService();
        String url = request.getParameter("url");
        log.info("url:"+url);
        WxJsapiSignature jsapiSignature = wxService.createJsapiSignature(url);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("appId",jsapiSignature.getAppId());
        map.put("jsApiList",new String[]{"updateAppMessageShareData","openLocation","scanQRCode",
                "chooseWXPay","updateAppMessageShareData","updateTimelineShareData",
                "openAddress","editAddress","getLocation"});
        map.put("nonceStr",jsapiSignature.getNonceStr());
        map.put("signature",jsapiSignature.getSignature());
        map.put("timestamp",jsapiSignature.getTimestamp());
        map.put("url",jsapiSignature.getUrl());
        return ApiResult.ok(map);
    }


    /**
     * 微信小程序接口能力配置
     */
    @GetMapping("/wxapp/config")
    @ApiOperation(value = "微信小程序接口能力配置",notes = "微信小程序接口能力配置",produces = "text/plain;charset=utf-8")
    public String wxAppConfig(@RequestParam(value = "signature") String signature,
                                                     @RequestParam(value = "timestamp") String timestamp,
                                                     @RequestParam(value = "nonce") String nonce,
                               @RequestParam(name = "echostr", required = false) String echostr) throws WxErrorException {
        WxMaService wxService = WxMaConfiguration.getWxMaService();

        if( wxService.checkSignature(timestamp,nonce,signature)){
            return echostr;
        }
        return "false";
    }

    /**
     * 微信支付/充值回调
     */
    @AnonymousAccess
    @PostMapping("/wechat/notify")
    @ApiOperation(value = "微信支付充值回调",notes = "微信支付充值回调")
    public String renotify(@RequestBody String xmlData) {
        try {
            WxPayService wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.WECHAT);
            if(wxPayService == null) {
                wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.WXAPP);
            }
            if(wxPayService == null) {
                wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.APP);
            }
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            String orderId = notifyResult.getOutTradeNo();
            String attach = notifyResult.getAttach();
            if(BillDetailEnum.TYPE_3.getValue().equals(attach)){
                YxStoreOrderQueryVo orderInfo = orderService.getOrderInfo(orderId,null);
                if(orderInfo == null) {
                    return WxPayNotifyResponse.success("处理成功!");
                }
                if(OrderInfoEnum.PAY_STATUS_1.getValue().equals(orderInfo.getPaid())){
                    return WxPayNotifyResponse.success("处理成功!");
                }
                orderService.paySuccess(orderInfo.getOrderId(),PayTypeEnum.WEIXIN.getValue());
            }else if(BillDetailEnum.TYPE_1.getValue().equals(attach)){
                //处理充值
                YxUserRecharge userRecharge = userRechargeService.getInfoByOrderId(orderId);
                if(userRecharge == null) {
                    return WxPayNotifyResponse.success("处理成功!");
                }
                if(OrderInfoEnum.PAY_STATUS_1.getValue().equals(userRecharge.getPaid())){
                    return WxPayNotifyResponse.success("处理成功!");
                }

                userRechargeService.updateRecharge(userRecharge);
            }

            return WxPayNotifyResponse.success("处理成功!");
        } catch (WxPayException e) {
            log.error(e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }

    }

    /**
     * 微信退款回调
     */
    @ApiOperation(value = "退款回调通知处理",notes = "退款回调通知处理")
    @PostMapping("/notify/refund")
    public String parseRefundNotifyResult(@RequestBody String xmlData) {
        try {
            WxPayService wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.WECHAT);
            if(wxPayService == null) {
                wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.WXAPP);
            }
            if(wxPayService == null) {
                wxPayService = WxPayConfiguration.getPayService(PayMethodEnum.APP);
            }
            WxPayRefundNotifyResult result = wxPayService.parseRefundNotifyResult(xmlData);
            String orderId = result.getReqInfo().getOutTradeNo();
            BigDecimal refundFee = BigNum.div(result.getReqInfo().getRefundFee(), 100);
            YxStoreOrderQueryVo orderInfo = orderService.getOrderInfo(orderId,null);
            if(OrderInfoEnum.REFUND_STATUS_2.getValue().equals(orderInfo.getRefundStatus())){
                return WxPayNotifyResponse.success("处理成功!");
            }
            YxStoreOrder storeOrder = new YxStoreOrder();
            //修改状态
            storeOrder.setId(orderInfo.getId());
            storeOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_2.getValue());
            storeOrder.setRefundPrice(refundFee);
            orderService.updateById(storeOrder);
            orderService.returnStock(orderId);
            //售后状态修改
            LambdaQueryWrapper<StoreAfterSales> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StoreAfterSales::getOrderCode, orderId);
            StoreAfterSales storeAfterSales = storeAfterSalesService.getOne(wrapper);
            if (Objects.nonNull(storeAfterSales)) {
                storeAfterSales.setState(AfterSalesStatusEnum.STATUS_3.getValue());
                storeAfterSalesService.updateById(storeAfterSales);
            }
            return WxPayNotifyResponse.success("处理成功!");
        } catch (WxPayException | IllegalAccessException e) {
            log.error(e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
    /**
     * 微信验证消息
     */
    @GetMapping( value = "/wechat/serve",produces = "text/plain;charset=utf-8")
    @ApiOperation(value = "微信验证消息",notes = "微信验证消息")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr){

        final WxMpService wxService = WxMpConfiguration.getWxMpService();
        if (wxService == null) {
            throw new IllegalArgumentException("未找到对应配置的服务，请核实！");
        }

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "fail";
    }

    /**
     *微信获取消息
     */
    @PostMapping("/wechat/serve")
    @ApiOperation(value = "微信获取消息",notes = "微信获取消息")
    public void post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        WxMpService wxService = WxMpConfiguration.getWxMpService();

        if (!wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if(outMessage == null) {
                return;
            }
            out = outMessage.toXml();;
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if(outMessage == null) {
                return;
            }

            out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(out);
        writer.close();
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return WxMpConfiguration.getWxMpMessageRouter().route(message);
        } catch (Exception e) {
            log.error("路由消息时出现异常！", e);
        }

        return null;
    }




}
