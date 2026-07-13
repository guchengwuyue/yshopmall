/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import co.yixiang.modules.user.service.dto.WechatUserDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-12
*/

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@TableName(value = "yx_user",autoResultMap = true)
public class YxUser extends BaseDomain {

    /** 用户id */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;


    /** 用户账户(跟accout一样) */
    private String username;




    /** 用户密码（跟pwd） */
    private String password;



    /** 真实姓名 */
    private String realName;


    /** 生日 */
    private Integer birthday;


    /** 身份证号码 */
    private String cardId;


    /** 用户备注 */
    private String mark;


    /** 合伙人id */
    private Integer partnerId;


    /** 用户分组id */
    private Integer groupId;


    /** 用户昵称 */
    private String nickname;


    /** 用户头像 */
    private String avatar;


    /** 手机号码 */
    private String phone;



    /** 添加ip */
    private String addIp;



    /** 最后一次登录ip */
    private String lastIp;


    /** 用户余额 */
    private BigDecimal nowMoney;


    /** 佣金金额 */
    private BigDecimal brokeragePrice;


    /** 用户剩余积分 */
    private BigDecimal integral;


    /** 连续签到天数 */
    private Integer signNum;


    /** 1为正常，0为禁止 */
    private Integer status;


    /** 等级 */
    private Integer level;


    /** 推广元id */
    private Long spreadUid;


    /** 推广员关联时间 */
    private Date spreadTime;


    /** 用户类型 */
    private String userType;


    /** 是否为推广员 */
    private Integer isPromoter;


    /** 用户购买次数 */
    private Integer payCount;


    /** 下级人数 */
    private Long spreadCount;



    /** 详细地址 */
    private String addres;


    /** 管理员编号  */
    private Integer adminid;


    /** 用户登陆类型，h5,wechat,routine */
    private String loginType;

    /** 微信用户json信息 */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private WechatUserDto wxProfile;


    public void copy(YxUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
