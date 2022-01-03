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
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
public class YxStoreBargainDto implements Serializable {

    /** 砍价产品ID */
    private Long id;

    /** 关联产品ID */
    private Long productId;

    /** 砍价活动名称 */
    private String title;

    /** 砍价活动图片 */
    private String image;

    /** 单位名称 */
    private String unitName;

    /** 库存 */
    private Integer stock;

    /** 销量 */
    private Integer sales;

    /** 砍价产品轮播图 */
    private String images;

    /** 砍价开启时间 */
    private Date startTime;

    /** 砍价结束时间 */
    private Date stopTime;

    /** 砍价产品名称 */
    private String storeName;

    /** 砍价金额 */
    private BigDecimal price;

    /** 砍价商品最低价 */
    private BigDecimal minPrice;

    /** 每次购买的砍价产品数量 */
    private Integer num;

    /** 用户每次砍价的最大金额 */
    private BigDecimal bargainMaxPrice;

    /** 用户每次砍价的最小金额 */
    private BigDecimal bargainMinPrice;

    /** 用户每次砍价的次数 */
    private Integer bargainNum;

    /** 砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间) */
    private Integer status;

    /** 砍价详情 */
    private String description;

    /** 反多少积分 */
    private BigDecimal giveIntegral;

    /** 砍价活动简介 */
    private String info;

    /** 成本价 */
    private BigDecimal cost;

    /** 排序 */
    private Integer sort;

    /** 添加时间 */
    private Date createTime;

    /** 砍价规则 */
    private String rule;

    /** 砍价产品浏览量 */
    private Integer look;

    /** 砍价产品分享量 */
    private Integer share;

    private String statusStr;
}
