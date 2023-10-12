/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.mp.listener;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.PayTypeEnum;
import co.yixiang.event.TemplateBean;
import co.yixiang.event.TemplateEvent;
import co.yixiang.event.TemplateListenEnum;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.customer.domain.YxStoreCustomer;
import co.yixiang.modules.customer.service.YxStoreCustomerService;
import co.yixiang.modules.mp.service.WeiXinSubscribeService;
import co.yixiang.modules.mp.service.WeixinPayService;
import co.yixiang.modules.mp.service.WeixinTemplateService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.WechatUserDto;
import com.github.binarywang.wxpay.exception.WxPayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author hupeng
 * 异步监听模板通知事件
 */
@Slf4j
@Component
public class TemplateListener implements SmartApplicationListener {
    @Autowired
    private YxUserService userService;
    @Autowired
    private WeixinTemplateService weixinTemplateService;
    @Autowired
    private WeixinPayService weixinPayService;
    @Autowired
    private WeiXinSubscribeService weiXinSubscribeService;
    @Autowired
    private YxUserExtractService yxUserExtractService;
    @Autowired
    private WeixinPayService payService;
    @Autowired
    private YxUserBillService billService;
    @Autowired
    private YxStoreCustomerService yxStoreCustomerService;
    //@Autowired
    //private MqProducer mqProducer;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == TemplateEvent.class;
    }

    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        TemplateEvent templateEvent = (TemplateEvent) applicationEvent;
        //获取注册用户对象信息
        TemplateBean templateBean = templateEvent.getTemplateBean();
        log.info("模板事件类型：{}", templateBean.getTemplateType());
        switch (TemplateListenEnum.toType(templateBean.getTemplateType())) {
            case TYPE_1:
                weixinTemplateService.paySuccessNotice(templateBean.getOrderId()
                        , templateBean.getPrice(), templateBean.getUid());
                weiXinSubscribeService.paySuccessNotice(templateBean.getOrderId()
                        , templateBean.getPrice(), templateBean.getUid());
                /**************给客服发送消息**************/
                try {
                    List<YxStoreCustomer> yxStoreCustomers = yxStoreCustomerService.lambdaQuery().eq(YxStoreCustomer::getIsEnable, ShopConstants.YSHOP_ONE_NUM).list();
                    yxStoreCustomers.forEach(msg -> {
                        if (StrUtil.isNotBlank(msg.getOpenId())) {
                         weixinTemplateService.paySuccessNoticeToKefu(templateBean.getOrderId()
                                 , templateBean.getPrice(), msg.getOpenId());
                        }
                    });
                } catch (Exception e) {
                    log.error("消息发送失败:{}", e);
                }
                break;
            case TYPE_2:
                //处理退款与消息
                if (PayTypeEnum.WEIXIN.getValue().equals(templateBean.getPayType())) {
                    BigDecimal bigDecimal = new BigDecimal("100");
                    int payPrice = bigDecimal.multiply(new BigDecimal(templateBean.getPrice())).intValue();
                    weixinPayService.refundOrder(templateBean.getOrderId(), payPrice);
                }

                weixinTemplateService.refundSuccessNotice("您的订单退款申请被通过，钱款将很快还至您的支付账户。",templateBean.getOrderId(), templateBean.getPrice(),
                        templateBean.getUid(), templateBean.getTime());
                break;
            case TYPE_3:
                weixinTemplateService.deliverySuccessNotice(templateBean.getOrderId(), templateBean.getDeliveryName(),
                        templateBean.getDeliveryId(), templateBean.getUid());
                break;
            case TYPE_4:
                weixinTemplateService.rechargeSuccessNotice(templateBean.getTime(), templateBean.getPrice(),
                        templateBean.getUid());
                break;
            case TYPE_7:
                //使用MQ延时消息
                //mqProducer.sendMsg("yshop-topic", templateBean.getOrderId());
                log.info("投递延时订单id： [{}]：", templateBean.getOrderId());
                break;
            case TYPE_8:
                YxUserExtract resources = yxUserExtractService.getById(templateBean.getExtractId());
                Boolean success = false;
                YxUser user = userService.getById(resources.getUid());
                if (user != null) {
                    WechatUserDto wechatUser = user.getWxProfile();
                    if (ObjectUtil.isNotNull(wechatUser) && ObjectUtil.isNotNull(wechatUser.getRoutineOpenid())) {
                        try {
                            String nonce_str = UUID.randomUUID().toString().replace("-", "");
                            payService.entPay(wechatUser.getRoutineOpenid(), nonce_str,
                                    resources.getRealName(),
                                    resources.getExtractPrice().multiply(new BigDecimal(100)).intValue());
                            success = true;
                        } catch (WxPayException e) {
                            log.error("退款失败,原因:{}", e.getMessage());
                        }
                    }
                }
                if (!success) {
                    //防止无限添加佣金
                    if (ObjectUtil.isNull(resources.getFailTime())) {
                        String mark = "提现失败,退回佣金" + resources.getExtractPrice() + "元";
                        double balance = NumberUtil.add(user.getBrokeragePrice(), resources.getExtractPrice()).doubleValue();
                        //插入流水
                        billService.income(resources.getUid(), "提现失败", BillDetailEnum.CATEGORY_1.getValue(),
                                BillDetailEnum.TYPE_4.getValue(), resources.getExtractPrice().doubleValue(), balance,
                                mark, resources.getId().toString());
                        //返回提现金额
                        userService.incBrokeragePrice(resources.getExtractPrice(), resources.getUid());
                        resources.setFailMsg("提现失败");
                        resources.setFailTime(new Date());
                    }
                    yxUserExtractService.updateById(resources);
                }


                break;
            case TYPE_9:
                weixinTemplateService.refundSuccessNotice("您的订单退款申请已提交,等待审核。",templateBean.getOrderId(), templateBean.getPrice(),
                        templateBean.getUid(), templateBean.getTime());
                /**************给客服发送消息**************/
                try {
                    List<YxStoreCustomer> yxStoreCustomers = yxStoreCustomerService.lambdaQuery().eq(YxStoreCustomer::getIsEnable, ShopConstants.YSHOP_ONE_NUM).list();
                    yxStoreCustomers.forEach(msg -> {
                        if (StrUtil.isNotBlank(msg.getOpenId())) {
                            weixinTemplateService.refundSuccessNoticeToKefu("尊敬的客服,您有新的退款申请待处理!",templateBean.getOrderId()
                                    , templateBean.getPrice(), msg.getOpenId(), templateBean.getTime());
                        }
                    });
                } catch (Exception e) {
                    log.error("消息发送失败:{}", e);
                }
                break;
            default:
                //todo
        }


    }
}
