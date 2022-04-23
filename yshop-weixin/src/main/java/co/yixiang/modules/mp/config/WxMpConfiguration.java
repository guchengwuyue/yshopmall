/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.config;

import co.yixiang.modules.mp.handler.KfSessionHandler;
import co.yixiang.modules.mp.handler.LocationHandler;
import co.yixiang.modules.mp.handler.LogHandler;
import co.yixiang.modules.mp.handler.MenuHandler;
import co.yixiang.modules.mp.handler.MsgHandler;
import co.yixiang.modules.mp.handler.NullHandler;
import co.yixiang.modules.mp.handler.RedisHandler;
import co.yixiang.modules.mp.handler.ScanHandler;
import co.yixiang.modules.mp.handler.StoreCheckNotifyHandler;
import co.yixiang.modules.mp.handler.SubscribeHandler;
import co.yixiang.modules.mp.handler.UnsubscribeHandler;
import co.yixiang.utils.RedisUtil;
import com.google.common.collect.Maps;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.EventType;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * 公众号配置
 * @author hupeng
 * @date 2020/01/20
 */
@Configuration
public class WxMpConfiguration {

    private static Map<String, WxMpService> mpServices = Maps.newHashMap();
    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();

    private static LogHandler logHandler;
    private static NullHandler nullHandler;
    private static KfSessionHandler kfSessionHandler;
    private static StoreCheckNotifyHandler storeCheckNotifyHandler;
    private static LocationHandler locationHandler;
    private static MenuHandler menuHandler;
    private static MsgHandler msgHandler;
    private static UnsubscribeHandler unsubscribeHandler;
    private static SubscribeHandler subscribeHandler;
    private static ScanHandler scanHandler;
    private static RedisHandler redisHandler;

    public WxMpConfiguration(LogHandler logHandler, NullHandler nullHandler, KfSessionHandler kfSessionHandler,
                             StoreCheckNotifyHandler storeCheckNotifyHandler, LocationHandler locationHandler,
                             MenuHandler menuHandler, MsgHandler msgHandler, UnsubscribeHandler unsubscribeHandler,
                             SubscribeHandler subscribeHandler, ScanHandler scanHandler,
                             RedisHandler redisHandler) {
        WxMpConfiguration.logHandler = logHandler;
        WxMpConfiguration.nullHandler = nullHandler;
        WxMpConfiguration.kfSessionHandler = kfSessionHandler;
        WxMpConfiguration.storeCheckNotifyHandler = storeCheckNotifyHandler;
        WxMpConfiguration.locationHandler = locationHandler;
        WxMpConfiguration.menuHandler = menuHandler;
        WxMpConfiguration.msgHandler = msgHandler;
        WxMpConfiguration.unsubscribeHandler = unsubscribeHandler;
        WxMpConfiguration.subscribeHandler = subscribeHandler;
        WxMpConfiguration.scanHandler = scanHandler;
        WxMpConfiguration.redisHandler = redisHandler;
    }


    /**
     * 获取WxMpService
     * @return
     */
    public static WxMpService getWxMpService() {

        WxMpService wxMpService = mpServices.get(ShopKeyUtils.getYshopWeiXinMpSevice());
        //增加一个redis标识
        if (wxMpService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinMpSevice()) == null) {
            WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
            configStorage.setAppId(RedisUtil.get(ShopKeyUtils.getWechatAppId()));
            configStorage.setSecret(RedisUtil.get(ShopKeyUtils.getWechatAppSecret()));
            configStorage.setToken(RedisUtil.get(ShopKeyUtils.getWechatToken()));
            configStorage.setAesKey(RedisUtil.get(ShopKeyUtils.getWechatEncodingAESKey()));
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(configStorage);
            mpServices.put(ShopKeyUtils.getYshopWeiXinMpSevice(), wxMpService);
            routers.put(ShopKeyUtils.getYshopWeiXinMpSevice(), newRouter(wxMpService));

            //增加标识
            RedisUtil.set(ShopKeyUtils.getYshopWeiXinMpSevice(), "yshop");
        }
        return wxMpService;
    }

    /**
     * 移除WxMpService
     */
    public static void removeWxMpService() {
        RedisUtil.del(ShopKeyUtils.getYshopWeiXinMpSevice());
        mpServices.remove(ShopKeyUtils.getYshopWeiXinMpSevice());
        routers.remove(ShopKeyUtils.getYshopWeiXinMpSevice());
    }

    /**
     *  获取WxMpMessageRouter
     */
    public static WxMpMessageRouter getWxMpMessageRouter() {
        WxMpMessageRouter wxMpMessageRouter = routers.get(ShopKeyUtils.getYshopWeiXinMpSevice());
        return wxMpMessageRouter;
    }

    private static WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(kfSessionHandler)
                .end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.CLICK).handler(menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.VIEW).handler(menuHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCANCODE_WAITMSG).handler(menuHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.LOCATION).handler(locationHandler)
                .end();


        // 默认
        newRouter.rule().async(false).handler(msgHandler).end();

        return newRouter;
    }

}
