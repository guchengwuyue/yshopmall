/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.services;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.util.IpUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.AppFromEnum;
import co.yixiang.modules.auth.param.LoginParam;
import co.yixiang.modules.auth.param.RegParam;
import co.yixiang.modules.mp.config.WxMaConfiguration;
import co.yixiang.modules.mp.config.WxMpConfiguration;
import co.yixiang.modules.shop.domain.YxSystemAttachment;
import co.yixiang.modules.shop.service.YxSystemAttachmentService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.WechatUserDto;
import co.yixiang.modules.user.vo.OnlineUser;
import co.yixiang.utils.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 登陆认证服务类
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/14
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final YxUserService userService;
    private final RedisUtils redisUtils;
    private static Integer expiredTimeIn;
    private final YxSystemAttachmentService systemAttachmentService;

    @Value("${yshop.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        AuthService.expiredTimeIn = expiredTimeIn;
    }


    /**
     * 小程序登陆
     *
     * @param loginParam loginParam
     * @param uid
     * @param sessionKey
     * @return long
     */
    @Transactional(rollbackFor = Exception.class)
    public YxUser loginAuth(LoginParam loginParam,Long uid,String sessionKey) {
//        String code = loginParam.getCode();
        String encryptedData = loginParam.getEncryptedData();
        String iv = loginParam.getIv();
        String spread = loginParam.getSpread();
        //读取redis配置
        String appId = redisUtils.getY(ShopKeyUtils.getWxAppAppId());
        String secret = redisUtils.getY(ShopKeyUtils.getWxAppSecret());
        if (StrUtil.isBlank(appId) || StrUtil.isBlank(secret)) {
            throw new YshopException("请先配置小程序");
        }
        WxMaService wxMaService = WxMaConfiguration.getWxMaService();
        //WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);

        WxMaUserInfo wxMpUser = wxMaService.getUserService()
                .getUserInfo(sessionKey, encryptedData, iv);

        YxUser yxUser = userService.getById(uid);
        //过滤掉表情
        String ip = IpUtil.getRequestIp();

        if(!StringUtils.isNotBlank(yxUser.getNickname())){
            yxUser.setNickname(wxMpUser.getNickName());
            yxUser.setAvatar(wxMpUser.getAvatarUrl());
        }
        yxUser.setLastIp(ip);
        //构建微信用户
        WechatUserDto wechatUserDTO = yxUser.getWxProfile();
        wechatUserDTO.setNickname(wxMpUser.getNickName());
        wechatUserDTO.setSex(Integer.valueOf(wxMpUser.getGender()));
        wechatUserDTO.setLanguage(wxMpUser.getLanguage());
        wechatUserDTO.setCity(wxMpUser.getCity());
        wechatUserDTO.setProvince(wxMpUser.getProvince());
        wechatUserDTO.setCountry(wxMpUser.getCountry());
        wechatUserDTO.setHeadimgurl(wxMpUser.getAvatarUrl());
        yxUser.setWxProfile(wechatUserDTO);
        userService.updateById(yxUser);
        userService.setSpread(spread, yxUser.getUid());
        return yxUser;
    }


    /**
     * 小程序登陆
     *
     * @param loginParam loginParam
     * @return long
     */
    @Transactional(rollbackFor = Exception.class)
    public YxUser wxappLogin(LoginParam loginParam) {
        String code = loginParam.getCode();
        String encryptedData = loginParam.getEncryptedData();
        String iv = loginParam.getIv();
        String spread = loginParam.getSpread();
        try {
            //读取redis配置
            String appId = redisUtils.getY(ShopKeyUtils.getWxAppAppId());
            String secret = redisUtils.getY(ShopKeyUtils.getWxAppSecret());
            if (StrUtil.isBlank(appId) || StrUtil.isBlank(secret)) {
                throw new YshopException("请先配置小程序");
            }
            WxMaService wxMaService = WxMaConfiguration.getWxMaService();
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);

            WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService()
                    .getPhoneNoInfo(session.getSessionKey(), encryptedData, iv);

            YxUser yxUser = this.userService.getOne(Wrappers.<YxUser>lambdaQuery()
                    .eq(YxUser::getPhone, phoneNoInfo.getPhoneNumber()), false);

            if (ObjectUtil.isNull(yxUser)) {

                //兼容旧系统
                yxUser = this.userService.getOne(Wrappers.<YxUser>lambdaQuery()
                        .eq(YxUser::getUsername, session.getOpenid()), false);

                if (ObjectUtil.isNull(yxUser)) {
                    //过滤掉表情
                    String ip = IpUtil.getRequestIp();
                    yxUser = YxUser.builder()
                            .username(phoneNoInfo.getPhoneNumber())
                            .phone(phoneNoInfo.getPhoneNumber())
                            .addIp(ip)
                            .lastIp(ip)
                            .nickname(phoneNoInfo.getPhoneNumber())
                            .avatar(ShopConstants.YSHOP_DEFAULT_AVATAR)
                            .userType(AppFromEnum.ROUNTINE.getValue())
                            .build();

                    //构建微信用户
                    WechatUserDto wechatUserDTO = WechatUserDto.builder()
                            .routineOpenid(session.getOpenid())
                            .unionId(session.getUnionid())
                            .build();

                    yxUser.setWxProfile(wechatUserDTO);

                    this.userService.save(yxUser);
                }else {
                    yxUser.setUsername(phoneNoInfo.getPhoneNumber());
                    yxUser.setPhone(phoneNoInfo.getPhoneNumber());
                    this.userService.updateById(yxUser);
                }

            } else {
                WechatUserDto wechatUser = yxUser.getWxProfile();
                if (null != wechatUser &&(StrUtil.isBlank(wechatUser.getRoutineOpenid()) && StrUtil.isNotBlank(session.getOpenid()))) {
                    wechatUser.setRoutineOpenid(session.getOpenid());
                    yxUser.setWxProfile(wechatUser);
                }
                yxUser.setUserType(AppFromEnum.ROUNTINE.getValue());
                this.userService.updateById(yxUser);
            }
            this.userService.setSpread(spread, yxUser.getUid());
            redisUtils.set(ShopConstants.YSHOP_MINI_SESSION_KET + yxUser.getUid(), session.getSessionKey());
            return yxUser;
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new YshopException(e.toString());
        }
    }

    /**
     * 公众号登陆
     *
     * @param code   code码
     * @param spread 上级用户
     * @return uid
     */
    @Transactional(rollbackFor = Exception.class)
    public YxUser wechatLogin(String code, String spread) {
        try {
            WxMpService wxService = WxMpConfiguration.getWxMpService();
            WxOAuth2AccessToken wxMpOAuth2AccessToken = wxService.getOAuth2Service().getAccessToken(code);
            WxOAuth2UserInfo wxMpUser = wxService.getOAuth2Service().getUserInfo(wxMpOAuth2AccessToken, null);
            String openid = wxMpUser.getOpenid();

            //如果开启了UnionId
            if (StrUtil.isNotBlank(wxMpUser.getUnionId())) {
                openid = wxMpUser.getUnionId();
            }

            YxUser yxUser = userService.getOne(Wrappers.<YxUser>lambdaQuery()
                    .eq(YxUser::getUsername, openid), false);

            //long uid = 0;
            YxUser returnUser = null;
            if (yxUser == null) {
                //过滤掉表情
                String nickname = wxMpUser.getNickname();
                log.info("昵称：{}", nickname);
                //用户保存
                String ip = IpUtil.getRequestIp();
                YxUser user = YxUser.builder()
                        .username(openid)
                        .nickname(nickname)
                        .avatar(wxMpUser.getHeadImgUrl())
                        .addIp(ip)
                        .lastIp(ip)
                        .userType(AppFromEnum.WECHAT.getValue())
                        .build();

                //构建微信用户
                WechatUserDto wechatUserDTO = WechatUserDto.builder()
                        .nickname(nickname)
                        .openid(wxMpUser.getOpenid())
                        .unionId(wxMpUser.getUnionId())
                        .language("")
                        .headimgurl(wxMpUser.getHeadImgUrl())
                        .subscribe(false)
                        .subscribeTime(0L)
                        .build();

                user.setWxProfile(wechatUserDTO);
                userService.save(user);

                returnUser = user;
            } else {
                returnUser = yxUser;
                WechatUserDto wechatUser = yxUser.getWxProfile();
                if ((StrUtil.isBlank(wechatUser.getOpenid()) && StrUtil.isNotBlank(wxMpUser.getOpenid()))
                        || (StrUtil.isBlank(wechatUser.getUnionId()) && StrUtil.isNotBlank(wxMpUser.getUnionId()))) {
                    wechatUser.setOpenid(wxMpUser.getOpenid());
                    wechatUser.setUnionId(wxMpUser.getUnionId());

                    yxUser.setWxProfile(wechatUser);
                }

                yxUser.setUserType(AppFromEnum.WECHAT.getValue());
                userService.updateById(yxUser);

            }

            userService.setSpread(spread, returnUser.getUid());

            log.error("spread:{}", spread);

            return returnUser;

        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new YshopException(e.toString());
        }
    }


    /**
     * 注册
     *
     * @param param RegDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public YxUser register(RegParam param) {

        String account = param.getAccount();
        String ip = IpUtil.getRequestIp();
        YxUser user = YxUser.builder()
                .username(account)
                .nickname(account)
                .password(SecureUtil.md5(param.getPassword()))
                .phone(account)
                .avatar(ShopConstants.YSHOP_DEFAULT_AVATAR)
                .addIp(ip)
                .lastIp(ip)
                .userType(AppFromEnum.H5.getValue())
                .build();

        userService.save(user);

        //设置推广关系
        if (StrUtil.isNotBlank(param.getInviteCode())) {
            YxSystemAttachment systemAttachment = systemAttachmentService.getByCode(param.getInviteCode());
            if (systemAttachment != null) {
                userService.setSpread(String.valueOf(systemAttachment.getUid()),
                        user.getUid());
            }
        }

        return user;

    }


    /**
     * 保存在线用户信息
     *
     * @param yxUser  /
     * @param token   /
     * @param request /
     */
    public void save(YxUser yxUser, String token, HttpServletRequest request) {
        String job = "yshop开发工程师";
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(yxUser.getUsername(), yxUser.getNickname(), job, browser,
                    ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(ShopConstants.YSHOP_APP_LOGIN_USER +onlineUser.getUserName() + ":" + token, onlineUser, AuthService.expiredTimeIn);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     *
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUser> onlineUsers = this.getAll(userName);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            try {
                String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                    this.kickOut(userName, onlineUser.getKey());
                } else if (StringUtils.isBlank(igoreToken)) {
                    this.kickOut(userName, onlineUser.getKey());
                }
            } catch (Exception e) {
                log.error("checkUser is error", e);
            }
        }
    }

    /**
     * 踢出用户
     *
     * @param key /
     */
    public void kickOut(String userName, String key) throws Exception {
        key = ShopConstants.YSHOP_APP_LOGIN_USER + userName + ":" + EncryptUtils.desDecrypt(key);
        redisUtils.del(key);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    public void logout(String userName, String token) {
        String key = ShopConstants.YSHOP_APP_LOGIN_USER + userName + ":" + token;
        redisUtils.del(key);
    }

    /**
     * 查询全部数据，不分页
     *
     * @param uName /
     * @return /
     */
    private List<OnlineUser> getAll(String uName) {
        List<String> keys = null;
        keys = redisUtils.scan(ShopConstants.YSHOP_APP_LOGIN_USER + uName + ":" + "*");

        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            onlineUsers.add(onlineUser);
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    /**
     * 根据手机号查询用户注册状态
     * @param phone 手机号
     * @return /
     */
    public YxUser authPhone(String phone) {
        return userService.getOne(Wrappers.<YxUser>lambdaQuery().eq(YxUser::getPhone, phone));
    }
}
