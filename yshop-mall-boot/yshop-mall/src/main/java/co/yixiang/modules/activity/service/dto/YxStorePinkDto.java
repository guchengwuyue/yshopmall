/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxStorePinkDto implements Serializable {

    private Long id;

    /** 用户id */
    private Long uid;

    /** 订单id 生成 */
    private String orderId;

    /** 订单id  数据库 */
    private Long orderIdKey;

    /** 购买商品个数 */
    private Integer totalNum;

    /** 购买总金额 */
    private BigDecimal totalPrice;

    /** 拼团产品id */
    private Long cid;

    /** 产品id */
    private Long pid;

    /** 拼团总人数 */
    private Integer people;

    /** 拼团产品单价 */
    private BigDecimal price;

    /** 开始时间 */
    @JsonFormat(
            pattern = "yyyy年MM月dd日HH时mm分",
            timezone = "GMT+8"
    )
    private Date createTime;

    private String stopTime;

    /** 团长id 0为团长 */
    private Long kId;

    /** 是否发送模板消息0未发送1已发送 */
    private Integer isTpl;

    /** 是否退款 0未退款 1已退款 */
    private Integer isRefund;

    /** 状态1进行中2已完成3未完成 */
    private Integer status;

    private String nickname;

    private String phone;

    private String userImg;

    private String product;

    private String image;
    /**
     * 参团人数
     */
    private Long countPeople;
}
