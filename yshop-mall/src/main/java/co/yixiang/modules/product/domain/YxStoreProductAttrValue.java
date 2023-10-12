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
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "商品ID")
    private Long productId;


    /** 商品属性索引值 (attr_value|attr_value[|....]) */
    @ApiModelProperty(value = "商品属性索引值 (attr_value|attr_value[|....])")
    private String sku;


    /** 属性对应的库存 */
    @ApiModelProperty(value = "属性对应的库存")
    private Integer stock;

    /** 拼团库存属性对应的库存 */
    @ApiModelProperty(value = "拼团库存属性对应的库存")
    private Integer pinkStock;

    /** 秒杀库存属性对应的库存 */
    @ApiModelProperty(value = "秒杀库存属性对应的库存")
    private Integer seckillStock;

    /** 销量 */
    @ApiModelProperty(value = "销量")
    private Integer sales;


    /** 属性金额 */
    @ApiModelProperty(value = "属性金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    /** 拼团属性对应的金额 */
    @ApiModelProperty(value = "拼团属性对应的金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pinkPrice;

    /** 秒杀属性对应的金额 */
    @ApiModelProperty(value = "秒杀属性对应的金额")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal seckillPrice;

    /** 图片 */
    @ApiModelProperty(value = "属性对应的图片")
    private String image;


    /** 唯一值 */
     @TableField(value = "`unique`")
     @ApiModelProperty(value = "唯一值")
    private String unique;


    /** 成本价 */
    @ApiModelProperty(value = "成本价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cost;

    /** 商品条码 */
    @ApiModelProperty(value = "商品条码")
    private String barCode;

    /** 原价 */
    @ApiModelProperty(value = "原价")
    private BigDecimal otPrice;

    /** 重量 */
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

    /** 体积 */
    @ApiModelProperty(value = "体积")
    private BigDecimal volume;


    /** 一级返佣 */
    @ApiModelProperty(value = "一级返佣")
    private BigDecimal brokerage;

    /** 二级返佣 */
    @ApiModelProperty(value = "二级返佣")
    private BigDecimal brokerageTwo;

    /** 所需多少积分兑换商品 */
    @ApiModelProperty(value = "所需多少积分兑换商品")
    private Integer integral;


    public void copy(YxStoreProductAttrValue source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
