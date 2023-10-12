/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.mp.config;

import co.yixiang.enums.PayMethodEnum;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.RedisUtils;
import co.yixiang.utils.ShopKeyUtils;
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
@Configuration(proxyBeanMethods = false)
public class WxPayConfiguration {

	private static Map<String, WxPayService> payServices = Maps.newHashMap();

	private static RedisUtils redisUtils;

	@Autowired
	public WxPayConfiguration(RedisUtils redisUtils) {
		WxPayConfiguration.redisUtils = redisUtils;
	}

	/**
	 *  获取WxPayService
	 * @return
	 */
	public static WxPayService getPayService(PayMethodEnum payMethodEnum) {
		WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinPayService()+payMethodEnum.getValue());
		if(wxPayService == null || redisUtils.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
			WxPayConfig payConfig = new WxPayConfig();
			switch (payMethodEnum){
				case WECHAT:
					payConfig.setAppId(redisUtils.getY(ShopKeyUtils.getWechatAppId()));
					break;
				case WXAPP:
					payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxAppAppId()));
					break;
				case APP:
					payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxNativeAppAppId()));
					break;
				default:
			}

			payConfig.setMchId(redisUtils.getY(ShopKeyUtils.getWxPayMchId()));
			payConfig.setMchKey(redisUtils.getY(ShopKeyUtils.getWxPayMchKey()));
			payConfig.setKeyPath(redisUtils.getY(ShopKeyUtils.getWxPayKeyPath()));
			// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			payServices.put(ShopKeyUtils.getYshopWeiXinPayService()+payMethodEnum.getValue(), wxPayService);

			//增加标识
			redisUtils.set(ShopKeyUtils.getYshopWeiXinPayService(),"yshop");
		}
		return wxPayService;
    }

//	/**
//	 *  获取小程序WxAppPayService
//	 * @return
//	 */
//	public static WxPayService getWxAppPayService() {
//		WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinMiniPayService());
//		if(wxPayService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
//			WxPayConfig payConfig = new WxPayConfig();
//			payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxAppAppId()));
//			payConfig.setMchId(RedisUtil.get(ShopKeyUtils.getWxPayMchId()));
//			payConfig.setMchKey(RedisUtil.get(ShopKeyUtils.getWxPayMchKey()));
//			payConfig.setKeyPath(RedisUtil.get(ShopKeyUtils.getWxPayKeyPath()));
//			// 可以指定是否使用沙箱环境
//			payConfig.setUseSandboxEnv(false);
//			wxPayService = new WxPayServiceImpl();
//			wxPayService.setConfig(payConfig);
//			payServices.put(ShopKeyUtils.getYshopWeiXinMiniPayService(), wxPayService);
//
//			//增加标识
//			RedisUtil.set(ShopKeyUtils.getYshopWeiXinPayService(),"yshop");
//		}
//		return wxPayService;
//	}

//	/**
//	 *  获取APPPayService
//	 * @return
//	 */
//	public static WxPayService getAppPayService() {
//		WxPayService wxPayService = payServices.get(ShopKeyUtils.getYshopWeiXinAppPayService());
//		if(wxPayService == null || RedisUtil.get(ShopKeyUtils.getYshopWeiXinPayService()) == null) {
//			WxPayConfig payConfig = new WxPayConfig();
//			payConfig.setAppId(RedisUtil.get(ShopKeyUtils.getWxNativeAppAppId()));
//			payConfig.setMchId(RedisUtil.get(ShopKeyUtils.getWxPayMchId()));
//			payConfig.setMchKey(RedisUtil.get(ShopKeyUtils.getWxPayMchKey()));
//			payConfig.setKeyPath(RedisUtil.get(ShopKeyUtils.getWxPayKeyPath()));
//			// 可以指定是否使用沙箱环境
//			payConfig.setUseSandboxEnv(false);
//			wxPayService = new WxPayServiceImpl();
//			wxPayService.setConfig(payConfig);
//			payServices.put(ShopKeyUtils.getYshopWeiXinAppPayService(), wxPayService);
//
//			//增加标识
//			RedisUtil.set(ShopKeyUtils.getYshopWeiXinPayService(),"yshop");
//		}
//		return wxPayService;
//	}

	/**
	 * 移除WxPayService
	 */
	public static void removeWxPayService(){
		redisUtils.del(ShopKeyUtils.getYshopWeiXinPayService());
		payServices.remove(ShopKeyUtils.getYshopWeiXinPayService());
		//payServices.remove(ShopKeyUtils.getYshopWeiXinMiniPayService());
		//payServices.remove(ShopKeyUtils.getYshopWeiXinAppPayService());
	}

}
