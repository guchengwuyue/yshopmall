/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * api页面 /doc.html
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/1/9
 **/
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.token-start-with}")
    private String tokenStartWith;

    @Value("${swagger.enabled:true}")
    private Boolean enabled;

    @Value("${swagger.title:yshop}")
    private String title;

    @Value("${swagger.version:}")
    private String version;

    @Value("${swagger.serverUrl:}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(title)
                        .version(version)
                        .termsOfService(serverUrl)
                        .contact(new Contact()
                                .name("hupeng")
                                .url("https://www.yixiang.co")
                                .email("guchengwuyue@163.com")))
                .components(new Components()
                        .addSecuritySchemes(tokenHeader, new SecurityScheme()
                                .name(tokenHeader)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Token 认证，默认前缀: " + tokenStartWith + " ")))
                .addSecurityItem(new SecurityRequirement().addList(tokenHeader));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("yshop")
                .packagesToScan("co.yixiang.modules")
                .pathsToExclude("/error/**")
                .build();
    }
}
