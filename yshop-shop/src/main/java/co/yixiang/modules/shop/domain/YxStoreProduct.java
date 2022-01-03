/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
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
    private Long id;


    /** 商品图片 */
    @NotBlank(message = "请上传商品图片")
    private String image;


    /** 轮播图 */
    @NotBlank(message = "请上传商品轮播")
    private String sliderImage;


    /** 商品名称 */
    @NotBlank(message = "商品名称不能空")
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
    @NotNull(message = "请输入商品价格")
    @DecimalMin(value = "0.00", message = "商品价格不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "商品价格不在合法范围内")
    private BigDecimal price;


    /** 会员价格 */
    private BigDecimal vipPrice;


    /** 市场价 */
    @NotNull(message = "请输入市场价")
    @DecimalMin(value = "0.00", message = "市场价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "市场价不在合法范围内")
    private BigDecimal otPrice;


    /** 邮费 */
    private BigDecimal postage;


    /** 单位名 */
    @NotBlank(message = "请填写单位")
    private String unitName;


    /** 排序 */
    private Integer sort;


    /** 销量 */
    private Integer sales;


    /** 库存 */
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0", value = 1)
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
    @NotBlank(message = "请填写商品详情")
    private String description;


    /** 是否包邮 */
    private Integer isPostage;


    /** 获得积分 */
    @DecimalMin(value = "0.00", message = "获得积分不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "获得积分不在合法范围内")
    private BigDecimal giveIntegral;


    /** 成本价 */
    @NotNull(message = "请输入成本价")
    @DecimalMin(value = "0.00", message = "成本价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "成本价不在合法范围内")
    private BigDecimal cost;


    /** 是否优品推荐 */
    private Integer isGood;


    /** 虚拟销量 */
    private Integer ficti;


    /** 浏览量 */
    private Integer browse;

    @TableField(exist = false)
    private YxStoreCategory storeCategory;


    public void copy(YxStoreProduct source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
