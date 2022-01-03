/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
public class YxStoreCartDto implements Serializable {

    /** 购物车表ID */
    private Long id;

    /** 用户ID */
    private Long uid;

    /** 类型 */
    private String type;

    /** 商品ID */
    private Long productId;

    /** 商品属性 */
    private String productAttrUnique;

    /** 商品数量 */
    private Integer cartNum;

    /** 0 = 未购买 1 = 已购买 */
    private Integer isPay;

    /** 是否为立即购买 */
    private Integer isNew;

    /** 拼团id */
    private Long combinationId;

    /** 秒杀产品ID */
    private Long seckillId;

    /** 砍价id */
    private Long bargainId;
}
