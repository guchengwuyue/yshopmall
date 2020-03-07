package co.yixiang.mp.config;

import co.yixiang.constant.ShopConstants;
import co.yixiang.mp.handler.RedisHandler;
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
        if(wxPayService == null) {
			WxPayConfig payConfig = new WxPayConfig();
			payConfig.setAppId(redisHandler.getVal("wxpay_appId"));
			payConfig.setMchId(redisHandler.getVal("wxpay_mchId"));
			payConfig.setMchKey(redisHandler.getVal("wxpay_mchKey"));
			payConfig.setKeyPath(redisHandler.getVal("wxpay_keyPath"));
			// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			payServices.put(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE, wxPayService);


        }
		return wxPayService;
    }

	/**
	 * 移除WxPayService
	 */
	public static void removeWxPayService(){
		payServices.remove(ShopConstants.YSHOP_WEIXIN_PAY_SERVICE);
	}
}
