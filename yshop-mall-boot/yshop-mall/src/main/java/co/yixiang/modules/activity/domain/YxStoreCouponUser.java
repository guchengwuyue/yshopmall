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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("yx_store_coupon_user")
public class YxStoreCouponUser extends BaseDomain {

    /** 优惠券发放记录id */
    @TableId
    @Schema(description = "优惠券发放记录id")
    private Long id;


    /** 兑换的项目id */
    @Schema(description = "兑换的项目id")
    private Integer cid;


    /** 优惠券所属用户 */
    @Schema(description = "优惠券所属用户")
    private Long uid;


    /** 优惠券名称 */
    @Schema(description = "优惠券名称")
    private String couponTitle;


    /** 优惠券的面值 */
    @Schema(description = "优惠券的面值")
    private BigDecimal couponPrice;


    /** 最低消费多少金额可用优惠券 */
    @Schema(description = "最低消费多少金额可用优惠券")
    private BigDecimal useMinPrice;


    /** 优惠券结束时间 */
    @Schema(description = "优惠券结束时间")
    private Date endTime;


    /** 使用时间 */
    @Schema(description = "使用时间")
    private Date useTime;


    /** 获取方式 */
    @Schema(description = "获取方式：get领取 send派送")
    private String type;


    /** 状态（0：未使用，1：已使用, 2:已过期） */
    @Schema(description = "状态（0：未使用，1：已使用, 2:已过期）")
    private Integer status;


    /** 是否有效 */
    @Schema(description = "是否有效1是 0否")
    private Integer isFail;


    public void copy(YxStoreCouponUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
