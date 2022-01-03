/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
public class YxStoreCouponIssueDto implements Serializable {

    private Integer id;

    private String cname;

    /** 优惠券ID */
    private Integer cid;

    /** 优惠券领取开启时间 */
    private Date startTime;

    /** 优惠券领取结束时间 */
    private Date endTime;

    /** 优惠券领取数量 */
    private Integer totalCount;

    /** 优惠券剩余领取数量 */
    private Integer remainCount;

    /** 是否无限张数 */
    private Integer isPermanent;

    /** 1 正常 0 未开启 -1 已无效 */
    private Integer status;

    /** 优惠券添加时间 */
    private Date createTime;

}
