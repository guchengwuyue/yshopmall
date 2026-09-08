/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.security.security;

/**
 * @deprecated TokenFilter is registered directly in SecurityFilterChain (Spring Security 6).
 * Kept for reference; no longer used as SecurityConfigurerAdapter.
 */
@Deprecated
public class TokenConfigurer {
    private final TokenUtil tokenUtil;

    public TokenConfigurer(TokenUtil tokenUtil){
        this.tokenUtil = tokenUtil;
    }

    public TokenUtil getTokenUtil() {
        return tokenUtil;
    }
}
