/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.tools.express.config;


import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.tools.express.ExpressService;
import co.yixiang.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ExpressAutoConfiguration {


    private static RedisUtils redisUtil;

    @Autowired
    public ExpressAutoConfiguration(RedisUtils redisUtil) {
        ExpressAutoConfiguration.redisUtil = redisUtil;
    }

    public static ExpressService expressService() {
        ExpressService expressService = (ExpressService)redisUtil.get(ShopConstants.YSHOP_EXPRESS_SERVICE);
        if(expressService != null) {
            return expressService;
        }

        ExpressProperties properties = new ExpressProperties();
        String enable = redisUtil.getY("exp_enable");
        String appId = redisUtil.getY("exp_appId");
        String appKey = redisUtil.getY("exp_appKey");
        properties.setAppId(appId);
        properties.setAppKey(appKey);

        if(ShopCommonEnum.ENABLE_2.getValue().toString().equals(enable)){
            properties.setEnable(false);
        }else{
            properties.setEnable(true);
        }
        ExpressService service = new ExpressService();
        service.setProperties(properties);
        redisUtil.set(ShopConstants.YSHOP_EXPRESS_SERVICE,service);
        return service;
    }

}
