/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.product.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import co.yixiang.modules.category.domain.YxStoreCategory;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2020-05-12
*/


@TableName("yx_store_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YxStoreProduct extends BaseDomain {

    /** 商品id */
    @TableId
    @ApiModelProperty(value = "商品ID")
    private Long id;


    /** 商户Id(0为总后台管理员创建,不为0的时候是商户后台创建) */
    @ApiModelProperty(value = "商户Id(0为总后台管理员创建,不为0的时候是商户后台创建) 预留字段")
    private Integer merId;


    /** 商品图片 */
    @NotBlank(message = "请上传商品图片")
    @ApiModelProperty(value = "商品图片")
    private String image;


    /** 轮播图 */
    @NotBlank(message = "请上传商品轮播图")
    @ApiModelProperty(value = "商品轮播图")
    private String sliderImage;


    /** 商品名称 */
    @NotBlank(message = "商品名称不能空")
    @ApiModelProperty(value = "商品名称")
    private String storeName;


    /** 商品简介 */
    @ApiModelProperty(value = "商品简介")
    private String storeInfo;


    /** 关键字 */
    @ApiModelProperty(value = "关键字")
    private String keyword;


    /** 产品条码（一维码） */
    @ApiModelProperty(value = "产品条码（一维码）")
    private String barCode;


    /** 分类id */
    @ApiModelProperty(value = "分类id")
    private String cateId;


    /** 商品价格 */
    @ApiModelProperty(value = "商品价格")
    @NotNull(message = "请输入商品价格")
    @DecimalMin(value="0.00", message = "商品价格不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "商品价格不在合法范围内")
    private BigDecimal price;


    /** 会员价格 */
    @ApiModelProperty(value = "会员价格")
    private BigDecimal vipPrice;


    /** 市场价 */
    @ApiModelProperty(value = "市场价")
    @NotNull(message = "请输入市场价")
    @DecimalMin(value="0.00", message = "市场价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "市场价不在合法范围内")
    private BigDecimal otPrice;


    /** 邮费 */
    @ApiModelProperty(value = "邮费")
    private BigDecimal postage;


    /** 单位名 */
    @NotBlank(message = "请填写单位")
    @ApiModelProperty(value = "单位名")
    private String unitName;


    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;


    /** 销量 */
    @ApiModelProperty(value = "销量")
    private Integer sales;


    /** 库存 */
    @ApiModelProperty(value = "库存")
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0",value = 1)
    private Integer stock;


    /** 需要多少积分兑换 */
    @ApiModelProperty(value = "需要多少积分兑换 只在开启积分兑换时生效")
    private Integer integral;


    /** 状态（0：未上架，1：上架） */
    @ApiModelProperty(value = "状态（0：未上架，1：上架）")
    private Integer isShow;


    /** 是否热卖 */
    @ApiModelProperty(value = "是否热卖（0：否，1：是）")
    private Integer isHot;


    /** 是否优惠 */
    @ApiModelProperty(value = "是否猜你喜欢（0：否，1：是）")
    private Integer isBenefit;


    /** 是否精品 */
    @ApiModelProperty(value = "是否精品（0：否，1：是）")
    private Integer isBest;


    /** 是否新品 */
    @ApiModelProperty(value = "是否新品（0：否，1：是）")
    private Integer isNew;


    /** 产品描述 */
    @NotBlank(message = "请填写商品详情")
    @ApiModelProperty(value = "商品详情")
    private String description;


    /** 是否包邮 */
    @ApiModelProperty(value = "是否包邮")
    private Integer isPostage;


    /** 商户是否代理 0不可代理1可代理 */
    @ApiModelProperty(value = "商户是否代理 0不可代理1可代理 ")
    private Integer merUse;


    /** 获得积分 */
    @ApiModelProperty(value = "获得积分")
    @DecimalMin(value="0.00", message = "获得积分不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "获得积分不在合法范围内")
    private BigDecimal giveIntegral;


    /** 成本价 */
    @ApiModelProperty(value = "成本价")
    @NotNull(message = "请输入成本价")
    @DecimalMin(value="0.00", message = "成本价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "成本价不在合法范围内")
    private BigDecimal cost;


    /** 秒杀状态 0 未开启 1已开启 */
    @ApiModelProperty(value = "秒杀状态 0 未开启 1已开启")
    private Integer isSeckill;


    /** 砍价状态 0未开启 1开启 */
    @ApiModelProperty(value = "砍价状态 0 未开启 1已开启")
    private Integer isBargain;


    /** 是否优品推荐 */
    @ApiModelProperty(value = "是否优品推荐（0：否，1：是）")
    private Integer isGood;


    /** 虚拟销量 */
    @ApiModelProperty(value = "虚拟销量")
    private Integer ficti;


    /** 浏览量 */
    @ApiModelProperty(value = "浏览量")
    private Integer browse;


    /** 产品二维码地址(用户小程序海报) */
    @ApiModelProperty(value = "品二维码地址(用户小程序海报) ")
    private String codePath;

    @ApiModelProperty(value = "邮费模版ID")
    private Integer tempId;

    @ApiModelProperty(value = "规格 0单 1多 ")
    private Integer specType;

    @ApiModelProperty(value = "是否单独分佣")
    private Integer isSub;

    @ApiModelProperty(value = "是否开启积分兑换")
    private Integer isIntegral;

    @TableField(exist = false)
    private YxStoreCategory storeCategory;


    public void copy(YxStoreProduct source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
