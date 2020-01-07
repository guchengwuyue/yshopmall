package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
* @author hupeng
* @date 2019-11-18
*/
@Data
public class YxStoreCombinationDTO implements Serializable {

    private Integer id;

    // 商品id
    private Integer productId;

    // 商户id
    private Integer merId;

    // 推荐图
    private String image;

    // 轮播图
    private String images;

    //参与人数
    private Integer countPeopleAll;

    //成团人数
    private Integer countPeoplePink;

    //访问人数
    private Integer countPeopleBrowse;

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
    private String addTime;

    // 推荐
    private Integer isHost;

    // 产品状态
    private Integer isShow;

    private Integer isDel;

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
    private Integer startTime;

    // 拼团结束时间
    private Integer stopTime;

    private Date startTimeDate;

    private Date endTimeDate;

    // 拼团订单有效时间
    private Integer effectiveTime;

    // 拼图产品成本
    private Integer cost;

    // 浏览量
    private Integer browse;

    // 单位名
    private String unitName;
}