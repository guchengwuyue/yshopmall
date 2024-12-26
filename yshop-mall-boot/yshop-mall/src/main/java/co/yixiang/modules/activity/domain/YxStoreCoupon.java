/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yx_store_coupon")
public class YxStoreCoupon extends BaseDomain {

    /** 优惠券表ID */
    @TableId
    @ApiModelProperty(value = "优惠券ID")
    private Integer id;


    /** 优惠券名称 */
    @NotBlank(message = "请填写优惠券名称")
    @ApiModelProperty(value = "优惠券名称")
    private String title;


    /** 兑换消耗积分值 */
    @ApiModelProperty(value = "兑换消耗积分值")
    private Integer integral;


    /** 兑换的优惠券面值 */
    @DecimalMin(value="0.00", message = "优惠券面值不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "优惠券面值不在合法范围内")
    @ApiModelProperty(value = "兑换的优惠券面值")
    private BigDecimal couponPrice;


    /** 最低消费多少金额可用优惠券 */
    @DecimalMin(value="0.00", message = "最低消费不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "最低消费不在合法范围内")
    @ApiModelProperty(value = "最低消费多少金额可用优惠券")
    private BigDecimal useMinPrice;


    /** 优惠券有效期限（单位：天） */
    @NotNull(message = "请输入有效期限")
    @ApiModelProperty(value = "优惠券有效期限（单位：天）")
    private Integer couponTime;


    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;


    /** 状态（0：关闭，1：开启） */
    @ApiModelProperty(value = "优惠券状态（0：关闭，1：开启） ")
    private Integer status;

    /** 优惠券类型（0：通用券，1：商品券，2：内部券）  */
    @ApiModelProperty(value = "优惠券类型（0：通用券，1：商品券，2：内部券） ")
    private Integer type;

    /** 优惠券绑定产品ID  */
    @ApiModelProperty(value = "优惠券绑定产品ID ")
    private String productId;



    public void copy(YxStoreCoupon source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
