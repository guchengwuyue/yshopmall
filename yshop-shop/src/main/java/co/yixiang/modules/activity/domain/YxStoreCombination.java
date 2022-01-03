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
@TableName("yx_store_combination")
public class YxStoreCombination extends BaseDomain {

    @TableId
    private Long id;


    /** 商品id */
    private Long productId;


    /** 推荐图 */
    @NotBlank(message = "请上传商品图片")
    private String image;


    /** 轮播图 */
    @NotBlank(message = "请上传商品轮播")
    private String images;


    /** 活动标题 */
    @NotBlank(message = "请填写拼团名称")
    private String title;


    /** 活动属性 */
    private String attr;


    /** 参团人数 */
    @NotNull(message = "请输入拼团人数")
    @Min(message = "拼团人数小于0", value = 1)
    private Integer people;


    /** 简介 */
    private String info;


    /** 价格 */
    @NotNull(message = "请输入拼团价")
    @DecimalMin(value = "0.00", message = "拼团价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "拼团价不在合法范围内")
    private BigDecimal price;


    /** 排序 */
    private Integer sort;


    /** 销量 */
    private Integer sales;


    /** 库存 */
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0", value = 1)
    private Integer stock;


    /** 推荐 */
    private Integer isHost;


    /** 产品状态 */
    private Integer isShow;


    private Integer combination;


    /** 拼团内容 */
    @NotBlank(message = "请填写详情")
    private String description;


    /** 拼团开始时间 */
    @NotNull(message = "请输入拼团开始时间")
    private Date startTime;


    /** 拼团结束时间 */
    @NotNull(message = "请输入拼团结束时间")
    private Date stopTime;


    /** 拼团订单有效时间 */
    @NotNull(message = "请输入拼团时效")
    @Min(message = "拼团时效不能小于0", value = 1)
    private Integer effectiveTime;


    /** 拼团产品成本 */
    private Integer cost;


    /** 浏览量 */
    private Integer browse;


    /** 单位名 */
    private String unitName;



    public void copy(YxStoreCombination source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
