/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.message.redis.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * reids相关配置
 * @author hupeng
 * @since 2020-02-27
 */
@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

	private String host = "host";
	private String port = "port";
	private String password = "password";
	private String database = "database";

}
