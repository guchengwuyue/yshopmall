/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
public class YxWechatUserDto implements Serializable {

    /** 微信用户id */
    private Integer uid;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
    private String unionid;

    /** 用户的标识，对当前公众号唯一 */
    private String openid;

    /** 小程序唯一身份ID */
    private String routineOpenid;

    /** 用户的昵称 */
    private String nickname;

    /** 用户头像 */
    private String headimgurl;

    /** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
    private Integer sex;

    /** 用户所在城市 */
    private String city;

    /** 用户的语言，简体中文为zh_CN */
    private String language;

    /** 用户所在省份 */
    private String province;

    /** 用户所在国家 */
    private String country;

    /** 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
    private String remark;

    /** 用户所在的分组ID（兼容旧的用户分组接口） */
    private Integer groupid;

    /** 用户被打上的标签ID列表 */
    private String tagidList;

    /** 用户是否订阅该公众号标识 */
    private Integer subscribe;

    /** 关注公众号时间 */
    private Integer subscribeTime;

    /** 添加时间 */
    private Integer addTime;

    /** 一级推荐人 */
    private Integer stair;

    /** 二级推荐人 */
    private Integer second;

    /** 一级推荐人订单 */
    private Integer orderStair;

    /** 二级推荐人订单 */
    private Integer orderSecond;

    /** 佣金 */
    private BigDecimal nowMoney;

    /** 小程序用户会话密匙 */
    private String sessionKey;

    /** 用户类型 */
    private String userType;
}
