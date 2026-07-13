/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yx_store_coupon_issue")
public class YxStoreCouponIssue extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "优惠券前台领取ID")
    private Integer id;

    @ApiModelProperty(value = "前台显示优惠券名称")
    private String cname;


    /** 优惠券ID */
    @ApiModelProperty(value = "优惠券ID")
    private Integer cid;

    @ApiModelProperty(value = "优惠券类型 0-通用 1-商品券")
    private Integer ctype;


    /** 优惠券领取开启时间 */
    @NotNull(message = "请选择开启时间")
    @ApiModelProperty(value = "优惠券领取开启时间")
    private Date startTime;


    /** 优惠券领取结束时间 */
    @NotNull(message = "请选择结束时间")
    @ApiModelProperty(value = "优惠券领取结束时间")
    private Date endTime;


    /** 优惠券领取数量 */
    @ApiModelProperty(value = "优惠券领取数量")
    private Integer totalCount;


    /** 优惠券剩余领取数量 */
    @ApiModelProperty(value = "优惠券剩余领取数量")
    private Integer remainCount;


    /** 是否无限张数 */
    @ApiModelProperty(value = "是否无限张数1：是 0：否")
    private Integer isPermanent;


    /** 1 正常 0 未开启 -1 已无效 */
    @ApiModelProperty(value = "前台领取优惠券状态1 正常 0 未开启 -1 已无效")
    private Integer status;



    public void copy(YxStoreCouponIssue source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
