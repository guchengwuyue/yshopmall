package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-10-06
*/
@Entity
@Data
@Table(name="yx_user")
public class YxUser implements Serializable {

    // 用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    // 用户账号
    @Column(name = "account",nullable = false)
    private String account;

    // 用户密码
    @Column(name = "pwd",nullable = false)
    private String pwd;

    // 真实姓名
    @Column(name = "real_name",nullable = false)
    private String realName;

    // 生日
    @Column(name = "birthday",nullable = false)
    private Integer birthday;

    // 身份证号码
    @Column(name = "card_id",nullable = false)
    private String cardId;

    // 用户备注
    @Column(name = "mark",nullable = false)
    private String mark;

    // 合伙人id
    @Column(name = "partner_id",nullable = false)
    private Integer partnerId;

    // 用户分组id
    @Column(name = "group_id",nullable = false)
    private Integer groupId;

    // 用户昵称
    @Column(name = "nickname",nullable = false)
    private String nickname;

    // 用户头像
    @Column(name = "avatar",nullable = false)
    private String avatar;

    // 手机号码
    @Column(name = "phone")
    private String phone;

    // 添加时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 添加ip
    @Column(name = "add_ip",nullable = false)
    private String addIp;

    // 最后一次登录时间
    @Column(name = "last_time",nullable = false)
    private Integer lastTime;

    // 最后一次登录ip
    @Column(name = "last_ip",nullable = false)
    private String lastIp;

    // 用户余额
    @Column(name = "now_money",nullable = false)
    private BigDecimal nowMoney;

    // 佣金金额
    @Column(name = "brokerage_price",nullable = false)
    private BigDecimal brokeragePrice;

    // 用户剩余积分
    @Column(name = "integral",nullable = false)
    private BigDecimal integral;

    // 连续签到天数
    @Column(name = "sign_num",nullable = false)
    private Integer signNum;

    // 1为正常，0为禁止
    @Column(name = "status",nullable = false)
    private Integer status;

    // 等级
    @Column(name = "level",nullable = false)
    private Integer level;

    // 推广元id
    @Column(name = "spread_uid",nullable = false)
    private Integer spreadUid;

    // 推广员关联时间
    @Column(name = "spread_time",nullable = false)
    private Integer spreadTime;

    // 用户类型
    @Column(name = "user_type",nullable = false)
    private String userType;

    // 是否为推广员
    @Column(name = "is_promoter",nullable = false)
    private Integer isPromoter;

    // 用户购买次数
    @Column(name = "pay_count")
    private Integer payCount;

    // 下级人数
    @Column(name = "spread_count")
    private Integer spreadCount;

    // 清理会员时间
    @Column(name = "clean_time")
    private Integer cleanTime;

    // 详细地址
    @Column(name = "addres",nullable = false)
    private String addres;

    // 管理员编号 
    @Column(name = "adminid")
    private Integer adminid;

    // 用户登陆类型，h5,wechat,routine
    @Column(name = "login_type",nullable = false)
    private String loginType;

    public void copy(YxUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}