/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import co.yixiang.modules.product.service.dto.FromatDetailDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yx_store_combination")
public class YxStoreCombination extends BaseDomain {

    @TableId
    @ApiModelProperty(value = "拼团产品ID")
    private Long id;


    /** 商品id */
    @ApiModelProperty(value = "关联产品ID")
    private Long productId;


    /** 商户id */
    @ApiModelProperty(value = "商户id 预留字段")
    private Integer merId;


    /** 推荐图 */
    @NotBlank(message = "请上传商品图片")
    @ApiModelProperty(value = "推荐图")
    private String image;


    /** 轮播图 */
    @NotBlank(message = "请上传商品轮播")
    @ApiModelProperty(value = "轮播图")
    private String images;


    /** 活动标题 */
    @NotBlank(message = "请填写拼团名称")
    @ApiModelProperty(value = "拼团产品名称")
    private String title;



    /** 参团人数 */
    @NotNull(message = "请输入拼团人数")
    @Min(message = "拼团人数小于0",value = 1)
    @ApiModelProperty(value = "拼团产品拼团人数")
    private Integer people;


    /** 简介 */
    @ApiModelProperty(value = "拼团产品简介")
    private String info;


    /** 价格 */
    @NotNull(message = "请输入拼团价")
    @DecimalMin(value="0.00", message = "拼团价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "拼团价不在合法范围内")
    @ApiModelProperty(value = "拼团产品拼团价")
    private BigDecimal price;

    private BigDecimal productPrice;


    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;


    /** 销量 */
    @ApiModelProperty(value = "销量")
    private Integer sales;


    /** 库存 */
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0",value = 1)
    private Integer stock;


    /** 推荐 */
    @ApiModelProperty(value = "是否推荐0不推荐1推荐")
    private Integer isHost;


    /** 产品状态 */
    @ApiModelProperty(value = "拼团产品状态0不显示 1显示")
    private Integer isShow;


    private Integer combination;


    /** 商户是否可用1可用0不可用 */
    @ApiModelProperty(value = "商户是否可用1可用0不可用 预留字段")
    private Integer merUse;


    /** 拼团内容 */
    @NotBlank(message = "请填写详情")
    @ApiModelProperty(value = "拼团产品详情")
    private String description;


    /** 拼团开始时间 */
    @NotNull(message = "请选择结束时间")
    @ApiModelProperty(value = "拼团产品结束时间")
    private Date startTime;


    /** 拼团结束时间 */
    @NotNull(message = "请选择开始时间")
    @ApiModelProperty(value = "拼团产品开始时间")
    private Date stopTime;


    /** 拼团订单有效时间 */
    @NotNull(message = "请输入拼团时效")
    @Min(message = "拼团时效不能小于0",value = 1)
    @ApiModelProperty(value = "拼团订单有效时间")
    private Integer effectiveTime;


    /** 拼团产品成本价 */
    @ApiModelProperty(value = "拼团产品成本价")
    private Integer cost;


    /** 浏览量 */
    @ApiModelProperty(value = "拼团产品浏览量")
    private Integer browse;


    /** 单位名 */
    @ApiModelProperty(value = "拼团产品单位名")
    private String unitName;

    /**
     * 规格 0单 1多
     */
    @ApiModelProperty(value = "拼团产品规格 0单规格 1多规格")
    private Integer specType;

    /** 运费模板ID */
    @JsonProperty("temp_id")
    @ApiModelProperty(value = "运费模板ID")
    private Long tempId;


    /** 属性项目 */
    @TableField(exist = false)
    @ApiModelProperty(value = "属性项目")
    private List<FromatDetailDto> items;

    /** sku结果集 */
    @TableField(exist = false)
    private List<Map<String,Object>> attrs;



    public void copy(YxStoreCombination source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

}
