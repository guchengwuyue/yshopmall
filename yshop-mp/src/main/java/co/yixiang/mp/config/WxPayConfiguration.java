package co.yixiang.mp.config;

import co.yixiang.constant.ShopConstants;
import co.yixiang.mp.handler.RedisHandler;
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
		this.redisHandler = redisHandler;
	}

	/**
	 *  获取WxPayService
	 * @return
	 */
	public static WxPayService getPayService() {
		WxPayService wxPayService = payServices.get(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE);
        if(wxPayService == null || RedisUtil.get(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE) == null) {
			WxPayConfig payConfig = new WxPayConfig();
			payConfig.setAppId(RedisUtil.get("wechat_appid"));
			payConfig.setMchId(RedisUtil.get("wxpay_mchId"));
			payConfig.setMchKey(RedisUtil.get("wxpay_mchKey"));
			payConfig.setKeyPath(RedisUtil.get("wxpay_keyPath"));
			// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			payServices.put(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE, wxPayService);

			//增加标识
			RedisUtil.set(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE,"yshop");
        }
		return wxPayService;
    }

	/**
	 *  获取小程序WxAppPayService
	 * @return
	 */
	public static WxPayService getWxAppPayService() {
		WxPayService wxPayService = payServices.get(ShopConstants.YSHOP_WEIXIN_MINI_PAY_SERVICE);
		if(wxPayService == null || RedisUtil.get(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE) == null) {
			WxPayConfig payConfig = new WxPayConfig();
			payConfig.setAppId(RedisUtil.get("wxapp_appId"));
			payConfig.setMchId(RedisUtil.get("wxpay_mchId"));
			payConfig.setMchKey(RedisUtil.get("wxpay_mchKey"));
			payConfig.setKeyPath(RedisUtil.get("wxpay_keyPath"));
			// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			payServices.put(ShopConstants.YSHOP_WEIXIN_MINI_PAY_SERVICE, wxPayService);

			//增加标识
			RedisUtil.set(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE,"yshop");
		}
		return wxPayService;
	}

	/**
	 *  获取APPPayService
	 * @return
	 */
	public static WxPayService getAppPayService() {
		WxPayService wxPayService = payServices.get(ShopConstants.YSHOP_WEIXIN_APP_PAY_SERVICE);
		if(wxPayService == null || RedisUtil.get(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE) == null) {
			WxPayConfig payConfig = new WxPayConfig();
			payConfig.setAppId(RedisUtil.get("wx_native_app_appId"));
			payConfig.setMchId(RedisUtil.get("wxpay_mchId"));
			payConfig.setMchKey(RedisUtil.get("wxpay_mchKey"));
			payConfig.setKeyPath(RedisUtil.get("wxpay_keyPath"));
			// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			payServices.put(ShopConstants.YSHOP_WEIXIN_APP_PAY_SERVICE, wxPayService);

			//增加标识
			RedisUtil.set(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE,"yshop");
		}
		return wxPayService;
	}

	/**
	 * 移除WxPayService
	 */
	public static void removeWxPayService(){
		RedisUtil.del(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE);
		payServices.remove(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE);
		payServices.remove(ShopConstants.YSHOP_WEIXIN_MINI_PAY_SERVICE);
		payServices.remove(ShopConstants.YSHOP_WEIXIN_APP_PAY_SERVICE);
	}



}
