/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.product.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.serializer.BigDecimalSerializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2020-05-12
*/

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("yx_store_product_attr_value")
public class YxStoreProductAttrValue implements Serializable {

    @TableId
    private Long id;


    /** 商品ID */
    @Schema(description = "商品ID")
    private Long productId;


    /** 商品属性索引值 (attr_value|attr_value[|....]) */
    @Schema(description = "商品属性索引值 (attr_value|attr_value[|....])")
    private String sku;


    /** 属性对应的库存 */
    @Schema(description = "属性对应的库存")
    private Integer stock;

    /** 拼团库存属性对应的库存 */
    @Schema(description = "拼团库存属性对应的库存")
    private Integer pinkStock;

    /** 秒杀库存属性对应的库存 */
    @Schema(description = "秒杀库存属性对应的库存")
    private Integer seckillStock;

    /** 销量 */
    @Schema(description = "销量")
    private Integer sales;


    /** 属性金额 */
    @Schema(description = "属性金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    /** 拼团属性对应的金额 */
    @Schema(description = "拼团属性对应的金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pinkPrice;

    /** 秒杀属性对应的金额 */
    @Schema(description = "秒杀属性对应的金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal seckillPrice;

    /** 图片 */
    @Schema(description = "属性对应的图片")
    private String image;


    /** 唯一值 */
     @TableField(value = "`unique`")
     @Schema(description = "唯一值")
    private String unique;


    /** 成本价 */
    @Schema(description = "成本价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cost;

    /** 商品条码 */
    @Schema(description = "商品条码")
    private String barCode;

    /** 原价 */
    @Schema(description = "原价")
    private BigDecimal otPrice;

    /** 重量 */
    @Schema(description = "重量")
    private BigDecimal weight;

    /** 体积 */
    @Schema(description = "体积")
    private BigDecimal volume;


    /** 一级返佣 */
    @Schema(description = "一级返佣")
    private BigDecimal brokerage;

    /** 二级返佣 */
    @Schema(description = "二级返佣")
    private BigDecimal brokerageTwo;

    /** 所需多少积分兑换商品 */
    @Schema(description = "所需多少积分兑换商品")
    private Integer integral;


    public void copy(YxStoreProductAttrValue source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
