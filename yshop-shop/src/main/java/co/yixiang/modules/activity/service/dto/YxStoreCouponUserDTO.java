package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
* @author hupeng
* @date 2019-11-10
*/
@Data
public class YxStoreCouponUserDTO implements Serializable {

    // 优惠券发放记录id
    private Integer id;

    // 兑换的项目id
    private Integer cid;

    // 优惠券所属用户
    private Integer uid;

    private String nickname;

    // 优惠券名称
    private String couponTitle;

    // 优惠券的面值
    private BigDecimal couponPrice;

    // 最低消费多少金额可用优惠券
    private BigDecimal useMinPrice;

    // 优惠券创建时间
    private Integer addTime;

    // 优惠券结束时间
    private Integer endTime;

    // 使用时间
    private Integer useTime;

    // 获取方式
    private String type;

    // 状态（0：未使用，1：已使用, 2:已过期）
    private Integer status;

    // 是否有效
    private Integer isFail;
}