/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.dto;

import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-13
*/
@Getter
@Setter
@ToString
public class YxStoreSeckillDto implements Serializable {


    // 商品秒杀产品表id
    private Long id;

    // 商品id
    private Long productId;

    // 推荐图
    private String image;

    // 轮播图
    private String images;

    /** 轮播图 */
    @JsonProperty("slider_image")
    private List<String> sliderImage;

    // 活动标题
    private String title;

    // 简介
    private String info;

    // 返多少积分
    private BigDecimal giveIntegral;

    /** 秒杀价 */
    @ApiModelProperty(value = "秒杀价")
    private BigDecimal price;

    /** 原价 */
    @ApiModelProperty(value = "原价")
    private BigDecimal otPrice;

    /** 成本 */
    @ApiModelProperty(value = "成本")
    private BigDecimal cost;

    /** 销量 */
    @ApiModelProperty(value = "秒杀销量")
    private Integer sales;

    // 排序
    private Integer sort;

    // 库存
    private Integer stock;

    // 单位名
    private String unitName;

    // 邮费
    private BigDecimal postage;

    // 内容
    private String description;

    // 开始时间
    private Date startTime;

    // 结束时间
    private Date stopTime;

    // 添加时间
    private Date createTime;

    // 产品状态
    private Integer status;

    // 是否包邮
    private Integer isPostage;

    // 热门推荐
    private Integer isHot;


    // 最多秒杀几个
    private Integer num;

    // 显示
    private Integer isShow;


    private String statusStr;

    private Integer timeId;
    // 模板id
    @JsonProperty("temp_id")
    private Integer tempId;
    /** 规格 0单 1多 */
    @JsonProperty("spec_type")
    private Integer specType;

    /** 是否单独分佣 */
    @JsonProperty("is_sub")
    private Integer isSub;

    private ProductFormatDto attr;
    //属性项目
    private List<FromatDetailDto> items;

    //sku结果集
    private List<ProductFormatDto> attrs;

}
