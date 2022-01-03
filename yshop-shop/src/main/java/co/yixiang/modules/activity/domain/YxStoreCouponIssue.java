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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
@TableName("yx_store_coupon_issue")
public class YxStoreCouponIssue extends BaseDomain {

    @TableId
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



    public void copy(YxStoreCouponIssue source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
