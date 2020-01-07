package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-11-09
*/
@Entity
@Data
@Table(name="yx_store_coupon")
public class YxStoreCoupon implements Serializable {

    // 优惠券表ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 优惠券名称
    @Column(name = "title",nullable = false)
    @NotBlank(message = "名称必填")
    private String title;

    // 兑换消耗积分值
    @Column(name = "integral",nullable = false)
    private Integer integral;

    // 兑换的优惠券面值
    @Column(name = "coupon_price",nullable = false)
    @Min(value = 1,message = "面值必须大于1")
    private BigDecimal couponPrice;

    // 最低消费多少金额可用优惠券
    @Column(name = "use_min_price",nullable = false)
    @Min(value = 1,message = "最低消费必须大于1")
    private BigDecimal useMinPrice;

    // 优惠券有效期限（单位：天）
    @Column(name = "coupon_time",nullable = false)
    private Integer couponTime;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 状态（0：关闭，1：开启）
    @Column(name = "status",nullable = false)
    private Integer status;

    // 兑换项目添加时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 是否删除
    @Column(name = "is_del",nullable = false,insertable = false)
    private Integer isDel;

    public void copy(YxStoreCoupon source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}