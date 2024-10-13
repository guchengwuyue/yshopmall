/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 */
package co.yixiang.modules.security.security;

import cn.hutool.core.util.StrUtil;
import co.yixiang.modules.security.config.SecurityProperties;
import co.yixiang.modules.security.service.OnlineUserService;
import co.yixiang.modules.user.vo.OnlineUser;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author /
 */
@Slf4j
public class TokenFilter extends GenericFilterBean {

    private final TokenUtil tokenUtil;

    private final SecurityProperties properties;

    private final OnlineUserService onlineUserService;

    TokenFilter(TokenUtil tokenUtil, SecurityProperties properties, OnlineUserService onlineUserService) {
        this.tokenUtil = tokenUtil;
        this.properties = properties;
        this.onlineUserService = onlineUserService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = resolveToken(httpServletRequest);
        // 对于 Token 为空的不需要去查 Redis
        if (StrUtil.isNotBlank(token)) {
            OnlineUser onlineUserDto = null;
            boolean cleanUserCache = false;
            try {
                onlineUserDto = onlineUserService.getOne(properties.getOnlineKey() + token);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
                cleanUserCache = true;
            } finally {
                if (cleanUserCache || Objects.isNull(onlineUserDto)) {
                    tokenUtil.removeToken(token);
                }
            }
            if (onlineUserDto != null && StringUtils.hasText(token)) {
                UserDetails userDetails = tokenUtil.getUserDetails(token);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("set Authentication to security context for '{}', token: {}", authentication.getName(), token);
                    // Token 续期
                    tokenUtil.checkRenewal(token);
                } else {
                    tokenUtil.removeToken(token);
                    log.debug("no valid JWT token found, uri: {}", token);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
}
