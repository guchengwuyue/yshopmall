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
public class YxStoreCombinationDto implements Serializable {

    private Long id;

    // 商品id
    private Long productId;


    // 推荐图
    private String image;

    // 轮播图
    private String images;

    //参与人数
    private Long countPeopleAll;

    //成团人数
    private Long countPeoplePink;

    //访问人数
    private Integer countPeopleBrowse = 0;

    // 活动标题
    private String title;

    // 活动属性
    private String attr;

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
    private Date createTime;

    // 产品状态
    private Integer isShow;

    private Integer combination;

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

}
