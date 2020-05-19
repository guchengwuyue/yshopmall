/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.domain;
import co.yixiang.annotation.Query;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/

@Data
@TableName("yx_store_product")
public class YxStoreProduct implements Serializable {

    /** 商品id */
    @TableId
    private Integer id;


    /** 商户Id(0为总后台管理员创建,不为0的时候是商户后台创建) */
    private Integer merId;


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
    @DecimalMin(value="0.00", message = "商品价格不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "商品价格不在合法范围内")
    private BigDecimal price;


    /** 会员价格 */
    private BigDecimal vipPrice;


    /** 市场价 */
    @NotNull(message = "请输入市场价")
    @DecimalMin(value="0.00", message = "市场价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "市场价不在合法范围内")
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


    /** 添加时间 */
    private Integer addTime;


    /** 是否包邮 */
    private Integer isPostage;


    /** 是否删除 */
    private Integer isDel;


    /** 商户是否代理 0不可代理1可代理 */
    private Integer merUse;


    /** 获得积分 */
    @DecimalMin(value="0.00", message = "获得积分不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "获得积分不在合法范围内")
    private BigDecimal giveIntegral;


    /** 成本价 */
    @NotNull(message = "请输入成本价")
    @DecimalMin(value="0.00", message = "成本价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "成本价不在合法范围内")
    private BigDecimal cost;


    /** 秒杀状态 0 未开启 1已开启 */
    private Integer isSeckill;


    /** 砍价状态 0未开启 1开启 */
    private Integer isBargain;


    /** 是否优品推荐 */
    private Integer isGood;


    /** 虚拟销量 */
    private Integer ficti;


    /** 浏览量 */
    private Integer browse;


    /** 产品二维码地址(用户小程序海报) */
    private String codePath;


    /** 淘宝京东1688类型 */
    private String soureLink;

    @TableField(exist = false)
    private YxStoreCategory storeCategory;


    public void copy(YxStoreProduct source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
