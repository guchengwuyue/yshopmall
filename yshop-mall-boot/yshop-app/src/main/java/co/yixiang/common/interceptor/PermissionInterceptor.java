/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.common.interceptor;


import co.yixiang.api.ApiCode;
import co.yixiang.api.UnAuthenticatedException;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.util.JwtToken;
import co.yixiang.constant.ShopConstants;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.RedisUtils;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * 权限拦截器
 * @author hupeng
 * @date 2020-04-30
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private YxUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<AuthCheck> authCheck = this.getAuthCheck(handler);
        if (!authCheck.isPresent()) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.hasLength(bearerToken)) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }

        if (!bearerToken.startsWith("Bearer")) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }
        String[] tokens = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }
        String token = tokens[1];

        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap
                .orElseThrow(() -> new UnAuthenticatedException(ApiCode.UNAUTHORIZED));

        String uName = map.get("uName").asString();

        //检测用户是否被踢出
        if (redisUtils.get(ShopConstants.YSHOP_APP_LOGIN_USER + uName + ":" + token) == null) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }
        boolean valid = this.hasPermission(authCheck.get(), map);
        if(valid){
            this.setToThreadLocal(map);
        }
        return valid;
    }

    private void setToThreadLocal(Map<String,Claim> map) {
        Integer uid = map.get("uid").asInt();
        Integer scope = map.get("scope").asInt();
        YxUser user = userService.getById(uid);
        if(user == null) {
            throw new UnAuthenticatedException(ApiCode.NOT_PERMISSION);
        }
        LocalUser.set(user, scope);
    }

    private boolean hasPermission(AuthCheck authCheck, Map<String, Claim> map) {
        Integer level = authCheck.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new UnAuthenticatedException(ApiCode.NOT_PERMISSION);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalUser.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private Optional<AuthCheck> getAuthCheck(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthCheck authCheck = handlerMethod.getMethod().getAnnotation(AuthCheck.class);
            if (authCheck == null) {
                return Optional.empty();
            }
            return Optional.of(authCheck);
        }
        return Optional.empty();
    }

}
