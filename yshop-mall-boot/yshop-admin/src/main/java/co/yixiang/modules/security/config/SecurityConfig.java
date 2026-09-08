/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.security.config;

import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.modules.security.security.JwtAccessDeniedHandler;
import co.yixiang.modules.security.security.JwtAuthenticationEntryPoint;
import co.yixiang.modules.security.security.TokenFilter;
import co.yixiang.modules.security.security.TokenUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author hupeng
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final TokenUtil tokenUtil;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final ApplicationContext applicationContext;

    public SecurityConfig(TokenUtil tokenUtil, CorsFilter corsFilter, JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler, ApplicationContext applicationContext) {
        this.tokenUtil = tokenUtil;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.applicationContext = applicationContext;
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // 去除 ROLE_ 前缀
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 搜寻匿名标记 url： @AnonymousAccess
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> anonymousUrls = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                RequestMappingInfo requestMappingInfo = infoEntry.getKey();
                PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
                if (pathPatternsCondition != null) {
                    pathPatternsCondition.getPatterns().forEach(pattern -> anonymousUrls.add(pattern.getPatternString()));
                } else {
                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    if (patternsCondition != null) {
                        anonymousUrls.addAll(patternsCondition.getPatterns());
                    }
                }
            }
        }
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new TokenFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationErrorHandler)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/*.html"),
                            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/**/*.html"),
                            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/**/*.css"),
                            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/**/*.js"),
                            AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/webSocket/**")
                    ).permitAll();
                    // OpenAPI3 / Knife4j
                    auth.requestMatchers(
                            AntPathRequestMatcher.antMatcher("/swagger-ui.html"),
                            AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                            AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                            AntPathRequestMatcher.antMatcher("/webjars/**"),
                            AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                            AntPathRequestMatcher.antMatcher("/v3/api-docs-ext/**"),
                            AntPathRequestMatcher.antMatcher("/doc.html"),
                            AntPathRequestMatcher.antMatcher("/*/api-docs"),
                            AntPathRequestMatcher.antMatcher("/v2/api-docs-ext")
                    ).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/avatar/**")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/file/**")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/druid/**")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/canvas/**")).permitAll();
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/**")).permitAll();
                    if (!anonymousUrls.isEmpty()) {
                        auth.requestMatchers(anonymousUrls.stream()
                                .map(AntPathRequestMatcher::antMatcher)
                                .toArray(AntPathRequestMatcher[]::new)).permitAll();
                    }
                    auth.anyRequest().authenticated();
                })
                .build();
    }
}
