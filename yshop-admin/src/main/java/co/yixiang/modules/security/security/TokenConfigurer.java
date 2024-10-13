/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.security.security;

import co.yixiang.modules.security.config.SecurityProperties;
import co.yixiang.modules.security.service.OnlineUserService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author /
 */
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenUtil tokenUtil;
    private final SecurityProperties properties;

    private final OnlineUserService onlineUserService;

    public TokenConfigurer(TokenUtil tokenUtil, SecurityProperties properties, OnlineUserService onlineUserService){
        this.tokenUtil = tokenUtil;
        this.properties = properties;
        this.onlineUserService = onlineUserService;
    }

    @Override
    public void configure(HttpSecurity http) {
        TokenFilter customFilter = new TokenFilter(tokenUtil, properties, onlineUserService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
