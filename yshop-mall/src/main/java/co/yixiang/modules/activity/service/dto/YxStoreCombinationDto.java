/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.dto;

import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class YxStoreCombinationDto implements Serializable {

    private Long id;

    // 商品id
    private Long productId;

    // 商户id
    private Integer merId;

    // 推荐图
    private String image;

    private String images;

    /** 轮播图 */
    @JsonProperty("slider_image")
    private List<String> sliderImage;

    //参与人数
    private Long countPeopleAll;

    //成团人数
    private Long countPeoplePink;

    //访问人数
    private Long countPeopleBrowse;

    // 活动标题
    private String title;

    // 参团人数
    private Integer people;

    // 简介
    private String info;

    // 价格
    private BigDecimal price;

    // 排序
    private Integer sort;

    // 销量
    private Integer sales;

    // 库存
    private Integer stock;

    // 添加时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    // 推荐
    private Integer isHost;

    // 产品状态
    private Integer isShow;


    private Integer combination;

    // 商户是否可用1可用0不可用
    private Integer merUse;

    // 是否包邮1是0否
    private Integer isPostage;

    // 邮费
    private BigDecimal postage;

    // 拼团内容
    private String description;

    // 拼团开始时间
    private Date startTime;

    // 拼团结束时间
    private Date stopTime;

    // 拼团订单有效时间
    private Integer effectiveTime;

    // 拼团产品成本
    private Integer cost;

    // 浏览量
    private Integer browse;

    // 单位名
    private String unitName;

    /** 规格 0单 1多 */
    @JsonProperty("spec_type")
    private Integer specType;
    // 模板id
    @JsonProperty("temp_id")
    private Integer tempId;

    /** 是否单独分佣 */
    @JsonProperty("is_sub")
    private Integer isSub;

    private ProductFormatDto attr;
    //属性项目
    private List<FromatDetailDto> items;

    //sku结果集
    private List<ProductFormatDto> attrs;

}
