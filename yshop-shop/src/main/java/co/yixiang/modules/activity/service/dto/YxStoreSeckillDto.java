/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
public class YxStoreSeckillDto implements Serializable {


    // 商品秒杀产品表id
    private Long id;

    // 商品id
    private Long productId;

    // 推荐图
    private String image;

    // 轮播图
    private String images;

    // 活动标题
    private String title;

    // 简介
    private String info;

    // 价格
    private BigDecimal price;

    // 成本
    private BigDecimal cost;

    // 原价
    private BigDecimal otPrice;

    // 返多少积分
    private BigDecimal giveIntegral;

    // 排序
    private Integer sort;

    // 库存
    private Integer stock;

    // 销量
    private Integer sales;

    // 单位名
    private String unitName;

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


    // 最多秒杀几个
    private Integer num;

    // 显示
    private Integer isShow;


    private String statusStr;

    private Integer timeId;
}
