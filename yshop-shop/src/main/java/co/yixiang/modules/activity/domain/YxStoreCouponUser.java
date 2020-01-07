package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-11-10
*/
@Entity
@Data
@Table(name="yx_store_coupon_user")
public class YxStoreCouponUser implements Serializable {

    // 优惠券发放记录id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 兑换的项目id
    @Column(name = "cid",nullable = false)
    private Integer cid;

    // 优惠券所属用户
    @Column(name = "uid",nullable = false)
    private Integer uid;

    // 优惠券名称
    @Column(name = "coupon_title",nullable = false)
    private String couponTitle;

    // 优惠券的面值
    @Column(name = "coupon_price",nullable = false)
    private BigDecimal couponPrice;

    // 最低消费多少金额可用优惠券
    @Column(name = "use_min_price",nullable = false)
    private BigDecimal useMinPrice;

    // 优惠券创建时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 优惠券结束时间
    @Column(name = "end_time",nullable = false)
    private Integer endTime;

    // 使用时间
    @Column(name = "use_time",nullable = false)
    private Integer useTime;

    // 获取方式
    @Column(name = "type",nullable = false)
    private String type;

    // 状态（0：未使用，1：已使用, 2:已过期）
    @Column(name = "status",nullable = false)
    private Integer status;

    // 是否有效
    @Column(name = "is_fail",nullable = false)
    private Integer isFail;

    public void copy(YxStoreCouponUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}