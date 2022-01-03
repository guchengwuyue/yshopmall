/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
@TableName("yx_store_coupon")
public class YxStoreCoupon extends BaseDomain {

    /** 优惠券表ID */
    @TableId
    private Integer id;


    /** 优惠券名称 */
    @NotBlank(message = "请填写优惠券名称")
    private String title;


    /** 兑换消耗积分值 */
    private Integer integral;


    /** 兑换的优惠券面值 */
    @DecimalMin(value = "0.00", message = "优惠券面值不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "优惠券面值不在合法范围内")
    private BigDecimal couponPrice;


    /** 最低消费多少金额可用优惠券 */
    @DecimalMin(value = "0.00", message = "最低消费不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "最低消费不在合法范围内")
    private BigDecimal useMinPrice;


    /** 优惠券有效期限（单位：天） */
    @NotNull(message = "请输入有效期限")
    private Integer couponTime;


    /** 排序 */
    private Integer sort;


    /** 状态（0：关闭，1：开启） */
    private Integer status;



    public void copy(YxStoreCoupon source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
