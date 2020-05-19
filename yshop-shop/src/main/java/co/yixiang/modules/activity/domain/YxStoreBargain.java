/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.domain;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
@TableName(value="yx_store_bargain")
public class YxStoreBargain implements Serializable {

    /** 砍价产品ID */
    @TableId
    private Integer id;


    /** 关联产品ID */
    private Integer productId;


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
    @Min(message = "库存不能小于0",value = 1)
    private Integer stock;


    /** 销量 */
    private Integer sales;


    /** 砍价产品轮播图 */
    @NotBlank(message = "请上传商品轮播")
    private String images;


    /** 砍价开启时间 */
    private Integer startTime;


    /** 砍价结束时间 */
    private Integer stopTime;


    /** 砍价产品名称 */
    private String storeName;


    /** 砍价金额 */
    @NotNull(message = "请输入砍价金额")
    @DecimalMin(value="0.00", message = "砍价金额不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "砍价金额不在合法范围内")
    private BigDecimal price;


    /** 砍价商品最低价 */
    @NotNull(message = "请输入砍到最低价")
    @DecimalMin(value="0.00", message = "砍到最低价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "砍到最低价不在合法范围内")
    private BigDecimal minPrice;


    /** 每次购买的砍价产品数量 */
    @NotNull(message = "请输入限购")
    @Min(message = "限购不能小于0",value = 1)
    private Integer num;


    /** 用户每次砍价的最大金额 */
    @NotNull(message = "请输入单次砍最高价")
    @DecimalMin(value="0.00", message = "单次砍最高价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "单次砍最高价不在合法范围内")
    private BigDecimal bargainMaxPrice;


    /** 用户每次砍价的最小金额 */
    @NotNull(message = "请输入单次砍最低价")
    @DecimalMin(value="0.00", message = "单次砍最低价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "单次砍最低价小金额不在合法范围内")
    private BigDecimal bargainMinPrice;


    /** 用户每次砍价的次数 */
    @NotNull(message = "请输入砍价的次数")
    @Min(message = "砍价的次数不能小于0",value = 1)
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


    /** 是否推荐0不推荐1推荐 */
    private Integer isHot;


    /** 是否删除 0未删除 1删除 */
    private Integer isDel;


    /** 添加时间 */
    private Integer addTime;


    /** 是否包邮 0不包邮 1包邮 */
    private Integer isPostage;


    /** 邮费 */
    private BigDecimal postage;


    /** 砍价规则 */
    @NotBlank(message = "请填写砍价规则")
    private String rule;


    /** 砍价产品浏览量 */
    private Integer look;


    /** 砍价产品分享量 */
    private Integer share;

    @NotNull(message = "请选择结束时间")
    private Timestamp endTimeDate;

    @NotNull(message = "请选择开始时间")
    private Timestamp startTimeDate;


    public void copy(YxStoreBargain source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
