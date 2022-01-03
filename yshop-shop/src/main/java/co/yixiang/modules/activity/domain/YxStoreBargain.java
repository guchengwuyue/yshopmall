/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
@TableName(value = "yx_store_bargain")
public class YxStoreBargain extends BaseDomain {

    /** 砍价产品ID */
    @TableId
    private Long id;


    /** 关联产品ID */
    private Long productId;


    /** 砍价活动名称 */
    @NotBlank(message = "请填写砍价名称")
    private String title;


    /** 砍价活动图片 */
    @NotBlank(message = "请上传商品图片")
    private String image;


    /** 单位名称 */
    private String unitName;


    /** 库存 */
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0", value = 1)
    private Integer stock;


    /** 销量 */
    private Integer sales;


    /** 砍价产品轮播图 */
    @NotBlank(message = "请上传商品轮播")
    private String images;


    /** 砍价开启时间 */
    @NotNull(message = "请输入拼团开始时间")
    private Date startTime;


    /** 砍价结束时间 */
    @NotNull(message = "请输入拼团结束时间")
    private Date stopTime;


    /** 砍价产品名称 */
    private String storeName;


    /** 砍价金额 */
    @NotNull(message = "请输入砍价金额")
    @DecimalMin(value = "0.00", message = "砍价金额不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "砍价金额不在合法范围内")
    private BigDecimal price;


    /** 砍价商品最低价 */
    @NotNull(message = "请输入砍到最低价")
    @DecimalMin(value = "0.00", message = "砍到最低价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "砍到最低价不在合法范围内")
    private BigDecimal minPrice;


    /** 每次购买的砍价产品数量 */
    @NotNull(message = "请输入限购")
    @Min(message = "限购不能小于0", value = 1)
    private Integer num;


    /** 用户每次砍价的最大金额 */
    @NotNull(message = "请输入单次砍最高价")
    @DecimalMin(value = "0.00", message = "单次砍最高价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "单次砍最高价不在合法范围内")
    private BigDecimal bargainMaxPrice;


    /** 用户每次砍价的最小金额 */
    @NotNull(message = "请输入单次砍最低价")
    @DecimalMin(value = "0.00", message = "单次砍最低价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "单次砍最低价小金额不在合法范围内")
    private BigDecimal bargainMinPrice;


    /** 用户每次砍价的次数 */
    @NotNull(message = "请输入砍价的次数")
    @Min(message = "砍价的次数不能小于0", value = 1)
    private Integer bargainNum;


    /** 砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间) */
    private Integer status;


    /** 砍价详情 */
    @NotBlank(message = "请填写详情")
    private String description;


    /** 反多少积分 */
    private BigDecimal giveIntegral;


    /** 砍价活动简介 */
    private String info;


    /** 成本价 */
    private BigDecimal cost;


    /** 排序 */
    private Integer sort;



    /** 砍价规则 */
    @NotBlank(message = "请填写砍价规则")
    private String rule;


    /** 砍价产品浏览量 */
    private Integer look;


    /** 砍价产品分享量 */
    private Integer share;



    public void copy(YxStoreBargain source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
