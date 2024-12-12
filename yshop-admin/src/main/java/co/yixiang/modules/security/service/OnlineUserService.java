/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 */
package co.yixiang.modules.security.service;

import cn.hutool.core.util.StrUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.modules.security.config.SecurityProperties;
import co.yixiang.modules.security.security.vo.JwtUser;
import co.yixiang.modules.user.vo.OnlineUser;
import co.yixiang.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author hupeng
 * @Date 2019年10月26日21:56:27
 */
@Service
@Slf4j
public class OnlineUserService {

    private final SecurityProperties properties;
    private RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     * @param jwtUser /
     * @param token /
     * @param request /
     */
    public void save(JwtUser jwtUser, String token, HttpServletRequest request) {
        String job = jwtUser.getDept() + "/" + jwtUser.getJob();
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(jwtUser.getUsername(), jwtUser.getNickName(), job, browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUser, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据
     * @param filter /
     * @param pageable /
     * @return /
     */
    public Map<String, Object> getAll(String filter, int type, Pageable pageable) {
        List<OnlineUser> onlineUsers = getAll(filter, type);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUsers),
                onlineUsers.size()
        );
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<OnlineUser> getAll(String filter, int type) {
        List<String> keys = null;
        if (type == 1) {
            keys = redisUtils.scan(ShopConstants.YSHOP_APP_LOGIN_USER + "*");
        } else {
            keys = redisUtils.scan(properties.getOnlineKey() + "*");
        }


        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    /**
     * 踢出用户
     * @param key /
     * @throws Exception /
     */
    public void kickOut(String key) throws Exception {
        key = properties.getOnlineKey() + EncryptUtils.desDecrypt(key);
        redisUtils.del(key);

    }

    /**
     * 踢出移动端用户
     * @param key /
     * @throws Exception /
     */
    public void kickOutT(String key) throws Exception {
        List<String> split = StrUtil.split(key, StrUtil.COLON);
        String keyt = ShopConstants.YSHOP_APP_LOGIN_USER + split.get(0) + StrUtil.COLON + EncryptUtils.desDecrypt(split.get(1));
        redisUtils.del(keyt);

    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 导出
     * @param all /
     * @param response /
     * @throws IOException /
     */
    public void download(List<OnlineUser> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUser user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUserName());
            map.put("用户昵称", user.getNickName());
            map.put("登录IP", user.getIp());
            map.put("登录地点", user.getAddress());
            map.put("浏览器", user.getBrowser());
            map.put("登录日期", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 查询用户
     * @param key /
     * @return /
     */
    public OnlineUser getOne(String key) {
        return (OnlineUser) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUser> onlineUsers = getAll(userName, 0);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(onlineUser.getKey());
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(onlineUser.getKey());
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

}
