/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.config;

import co.yixiang.modules.mp.handler.RedisHandler;
import co.yixiang.utils.RedisUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 支付配置
 * @author hupeng
 * @date 2020/03/01
 */
@Slf4j
@Configuration
public class WxPayConfiguration {

    private static Map<String, WxPayService> payServices = Maps.newHashMap();

    private static RedisHandler redisHandler;

    @Autowired
    public WxPayConfiguration(RedisHandler redisHandler) {
        WxPayConfiguration.redisHandler = redisHandler;
    }

    /**
     *  获取WxPayService
     * @return
     */
    public static WxPayService getPayService() {
        WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinPayService());
        if (wxPayService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWechatAppId()));
            payConfig.setMchId(RedisUtil.get(ShopKeyUtils.getWxPayMchId()));
            payConfig.setMchKey(RedisUtil.get(ShopKeyUtils.getWxPayMchKey()));
            payConfig.setKeyPath(RedisUtil.get(ShopKeyUtils.getWxPayKeyPath()));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            payServices.put(ShopKeyUtils.getYshopWeiXinPayService(), wxPayService);

            //增加标识
            RedisUtil.set(ShopKeyUtils.getYshopWeiXinPayService(), "yshop");
        }
        return wxPayService;
    }

    /**
     *  获取小程序WxAppPayService
     * @return
     */
    public static WxPayService getWxAppPayService() {
        WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinMiniPayService());
        if (wxPayService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxAppAppId()));
            payConfig.setMchId(RedisUtil.get(ShopKeyUtils.getWxPayMchId()));
            payConfig.setMchKey(RedisUtil.get(ShopKeyUtils.getWxPayMchKey()));
            payConfig.setKeyPath(RedisUtil.get(ShopKeyUtils.getWxPayKeyPath()));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            payServices.put(ShopKeyUtils.getYshopWeiXinMiniPayService(), wxPayService);

            //增加标识
            RedisUtil.set(ShopKeyUtils.getYshopWeiXinPayService(), "yshop");
        }
        return wxPayService;
    }

    /**
     *  获取APPPayService
     * @return
     */
    public static WxPayService getAppPayService() {
        WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinAppPayService());
        if (wxPayService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxNativeAppAppId()));
            payConfig.setMchId(RedisUtil.get(ShopKeyUtils.getWxPayMchId()));
            payConfig.setMchKey(RedisUtil.get(ShopKeyUtils.getWxPayMchKey()));
            payConfig.setKeyPath(RedisUtil.get(ShopKeyUtils.getWxPayKeyPath()));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
            payServices.put(ShopKeyUtils.getYshopWeiXinAppPayService(), wxPayService);

            //增加标识
            RedisUtil.set(ShopKeyUtils.getYshopWeiXinPayService(), "yshop");
        }
        return wxPayService;
    }

    /**
     * 移除WxPayService
     */
    public static void removeWxPayService() {
        RedisUtil.del(ShopKeyUtils.getYshopWeiXinPayService());
        payServices.remove(ShopKeyUtils.getYshopWeiXinPayService());
        payServices.remove(ShopKeyUtils.getYshopWeiXinMiniPayService());
        payServices.remove(ShopKeyUtils.getYshopWeiXinAppPayService());
    }

}
