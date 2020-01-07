package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @author xuwenbo
* @date 2019-12-22
*/
@Entity
@Data
@Table(name="yx_store_bargain")
public class YxStoreBargain implements Serializable {

    // 砍价产品ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 关联产品ID
    @Column(name = "product_id",nullable = false)
    private Integer productId;

    // 砍价活动名称
    @Column(name = "title",nullable = false)
    @NotBlank(message = "请输入砍价活动名称")
    private String title;

    // 砍价活动图片
    @Column(name = "image",nullable = false)
    @NotBlank(message = "请上传产品图片")
    private String image;

    // 单位名称
    @Column(name = "unit_name")
    @NotBlank(message = "单位名不能为空")
    private String unitName;

    // 库存
    @Column(name = "stock")
    @NotNull(message = "库存必填")
    private Integer stock;

    // 销量
    @Column(name = "sales")
    @NotNull(message = "销量必填")
    private Integer sales;

    // 砍价产品轮播图
    @Column(name = "images",nullable = false)
    @NotBlank(message = "请上传产品轮播图")
    private String images;

    // 砍价开启时间
    @Column(name = "start_time",nullable = false)
    private Integer startTime;

    // 砍价结束时间
    @Column(name = "stop_time",nullable = false)
    private Integer stopTime;

    @NotNull(message = "开始时间不能为空")
    private Date startTimeDate;

    @NotNull(message = "结束时间不能为空")
    private Date endTimeDate;

    // 砍价产品名称
    @Column(name = "store_name")
    private String storeName;

    // 砍价金额
    @Column(name = "price")
    @NotNull(message = "砍价金额必填")
    @Min(value = 0,message = "砍价金额必须大于等于0")
    private BigDecimal price;

    // 砍价商品最低价
    @Column(name = "min_price")
    @NotNull(message = "砍价商品最低价必填")
    @Min(value = 0,message = "砍价商品最低价必须大于等于0")
    private BigDecimal minPrice;

    // 每次购买的砍价产品数量
    @Column(name = "num")
    @NotNull(message = "购买的砍价产品数量必填")
    @Min(value = 0,message = "购买的砍价产品数量必须大于等于0")
    private Integer num;

    // 用户每次砍价的最大金额
    @Column(name = "bargain_max_price")
    @NotNull(message = "砍价的最大金额必填")
    private BigDecimal bargainMaxPrice;

    // 用户每次砍价的最小金额
    @Column(name = "bargain_min_price")
    @NotNull(message = "砍价的最小金额必填")
    private BigDecimal bargainMinPrice;

    // 用户每次砍价的次数
    @Column(name = "bargain_num",insertable = false)
    private Integer bargainNum;

    // 砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)
    @Column(name = "status",nullable = false)
    @NotNull(message = "请选择活动状态")
    private Integer status;

    // 砍价详情
    @Column(name = "description")
    private String description;

    // 反多少积分
    @Column(name = "give_integral",insertable = false)
    private BigDecimal giveIntegral;

    // 砍价活动简介
    @Column(name = "info")
    private String info;

    // 成本价
    @Column(name = "cost")
    @NotNull(message = "成本价必填")
    private BigDecimal cost;

    // 排序
    @Column(name = "sort",nullable = false)
    @NotNull(message = "排序必填")
    private Integer sort;

    // 是否推荐0不推荐1推荐
    @Column(name = "is_hot",insertable = false)
    private Integer isHot;

    // 是否删除 0未删除 1删除
    @Column(name = "is_del",insertable = false)
    private Integer isDel;

    // 添加时间
    @Column(name = "add_time")
    private Integer addTime;

    // 是否包邮 0不包邮 1包邮
    @Column(name = "is_postage",nullable = false)
    @NotNull(message = "请选择是否包邮")
    private Integer isPostage;

    // 邮费
    @Column(name = "postage")
    private BigDecimal postage;

    // 砍价规则
    @Column(name = "rule")
    private String rule;

    // 砍价产品浏览量
    @Column(name = "look",insertable = false)
    private Integer look;

    // 砍价产品分享量
    @Column(name = "share",insertable = false)
    private Integer share;

    public void copy(YxStoreBargain source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}