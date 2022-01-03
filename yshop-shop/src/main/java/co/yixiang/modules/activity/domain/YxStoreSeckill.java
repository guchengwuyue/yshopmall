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
@TableName("yx_store_seckill")
public class YxStoreSeckill extends BaseDomain {

    /** 商品秒杀产品表id */
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
    @NotBlank(message = "请填写秒杀名称")
    private String title;


    /** 简介 */
    private String info;


    /** 价格 */
    @NotNull(message = "请输入秒杀价")
    @DecimalMin(value = "0.00", message = "秒杀价不在合法范围内")
    @DecimalMax(value = "99999999.99", message = "秒杀价不在合法范围内")
    private BigDecimal price;


    /** 成本 */
    private BigDecimal cost;


    /** 原价 */
    private BigDecimal otPrice;


    /** 返多少积分 */
    private BigDecimal giveIntegral;


    /** 排序 */
    private Integer sort;


    /** 库存 */
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0", value = 1)
    private Integer stock;


    /** 销量 */
    private Integer sales;


    /** 单位名 */
    private String unitName;



    /** 内容 */
    @NotBlank(message = "请填写详情")
    private String description;


    /** 开始时间 */
    @NotNull(message = "请输入拼团开始时间")
    private Date startTime;


    /** 结束时间 */
    @NotNull(message = "请输入拼团结束时间")
    private Date stopTime;



    /** 产品状态 */
    private Integer status;



    /** 最多秒杀几个 */
    @NotNull(message = "请输入限购")
    @Min(message = "限购不能小于0", value = 1)
    private Integer num;


    /** 显示 */
    private Integer isShow;

    /** 时间段id */
    @NotNull(message = "请选择开始时间")
    private Integer timeId;


    public void copy(YxStoreSeckill source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
