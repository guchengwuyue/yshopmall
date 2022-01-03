/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
public class YxStoreProductDto implements Serializable {

    /** 商品id */
    private Long id;

    /** 商品图片 */
    private String image;

    /** 轮播图 */
    private String sliderImage;

    /** 商品名称 */
    private String storeName;

    /** 商品简介 */
    private String storeInfo;

    /** 关键字 */
    private String keyword;

    /** 产品条码（一维码） */
    private String barCode;

    /** 分类id */
    private String cateId;

    /** 商品价格 */
    private BigDecimal price;

    /** 会员价格 */
    private BigDecimal vipPrice;

    /** 市场价 */
    private BigDecimal otPrice;

    /** 邮费 */
    private BigDecimal postage;

    /** 单位名 */
    private String unitName;

    /** 排序 */
    private Integer sort;

    /** 销量 */
    private Integer sales;

    /** 库存 */
    private Integer stock;

    /** 状态（0：未上架，1：上架） */
    private Integer isShow;

    /** 是否热卖 */
    private Integer isHot;

    /** 是否优惠 */
    private Integer isBenefit;

    /** 是否精品 */
    private Integer isBest;

    /** 是否新品 */
    private Integer isNew;

    /** 产品描述 */
    private String description;

    /** 添加时间 */
    private Date createTime;

    /** 是否包邮 */
    private Integer isPostage;

    /** 获得积分 */
    private BigDecimal giveIntegral;

    /** 成本价 */
    private BigDecimal cost;

    /** 是否优品推荐 */
    private Integer isGood;

    /** 虚拟销量 */
    private Integer ficti;

    /** 浏览量 */
    private Integer browse;


    private YxStoreCategorySmallDto storeCategory;
}
