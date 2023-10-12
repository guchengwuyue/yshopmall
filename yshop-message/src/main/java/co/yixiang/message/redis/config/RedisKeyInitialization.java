/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.message.redis.config;

import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * api服务启动初始化reids
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisKeyInitialization  implements CommandLineRunner {


    private final YxSystemConfigService systemConfigService;


    private final RedisUtils redisUtils;


    /**
     * 初始化redis
     */
    private void redisKeyInitialization(){
        try {
            List<YxSystemConfig> systemConfigs = systemConfigService.list();
            for (YxSystemConfig systemConfig : systemConfigs) {
                redisUtils.set(systemConfig.getMenuName(),systemConfig.getValue());
            }

            log.info("---------------redisKey初始化成功---------------");
        }catch (Exception e){
            log.error("redisKey初始化失败: {}",e.getMessage());
        }

    }

    @Override
    public void run(String... args) throws Exception {
        this.redisKeyInitialization();
    }
}
