/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
public class YxStoreCouponDto implements Serializable {

    // 优惠券表ID
    private Integer id;

    // 优惠券名称
    private String title;

    // 兑换消耗积分值
    private Integer integral;

    // 兑换的优惠券面值
    private BigDecimal couponPrice;

    // 最低消费多少金额可用优惠券
    private BigDecimal useMinPrice;

    // 优惠券有效期限（单位：天）
    private Integer couponTime;

    // 排序
    private Integer sort;

    // 状态（0：关闭，1：开启）
    private Integer status;

    // 兑换项目添加时间
    private Date createTime;

}
