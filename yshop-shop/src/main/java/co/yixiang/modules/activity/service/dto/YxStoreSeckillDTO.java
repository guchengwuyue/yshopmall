package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
* @author xuwenbo
* @date 2019-12-14
*/
@Data
public class YxStoreSeckillDTO implements Serializable {

    // 商品秒杀产品表id
    private Integer id;

    // 商品id
    private Integer productId;

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

    // 邮费
    private BigDecimal postage;

    // 内容
    private String description;

    // 开始时间
    private Integer startTime;

    // 结束时间
    private Integer stopTime;

    // 添加时间
    private String addTime;

    // 产品状态
    private Integer status;

    // 是否包邮
    private Integer isPostage;

    // 热门推荐
    private Integer isHot;

    // 删除 0未删除1已删除
    private Integer isDel;

    // 最多秒杀几个
    private Integer num;

    // 显示
    private Integer isShow;

    private Date startTimeDate;

    private Date endTimeDate;

    private String statusStr;

    private Integer timeId;
}