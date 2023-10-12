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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("yx_store_pink")
public class YxStorePink extends BaseDomain {

    @TableId
    @ApiModelProperty(value = "拼团产品ID")
    private Long id;


    /** 用户id */
    @ApiModelProperty(value = "拼团产品用户ID")
    private Long uid;


    /** 订单id 生成 */
    @ApiModelProperty(value = "拼团产品订单ID 生成")
    private String orderId;


    /** 订单id  数据库 */
    @ApiModelProperty(value = "拼团产品订单id  数据库")
    private Long orderIdKey;


    /** 购买商品个数 */
    @ApiModelProperty(value = "购买商品个数")
    private Integer totalNum;


    /** 购买总金额 */
    @ApiModelProperty(value = "购买总金额")
    private BigDecimal totalPrice;


    /** 拼团产品id */
    @ApiModelProperty(value = "拼团产品id")
    private Long cid;


    /** 产品id */
    @ApiModelProperty(value = "产品id")
    private Long pid;


    /** 拼团总人数 */
    @ApiModelProperty(value = "拼团总人数")
    private Integer people;


    /** 拼团产品单价 */
    @ApiModelProperty(value = "拼团产品单价")
    private BigDecimal price;

    /** 拼团产品停止时间 */
    @ApiModelProperty(value = "拼团产品停止时间")
    private Date stopTime;


    /** 团长id 0为团长 */
    @ApiModelProperty(value = "拼团产品团长id 0为团长")
    private Long kId;


    /** 是否发送模板消息0未发送1已发送 */
    @ApiModelProperty(value = "是否发送模板消息0未发送1已发送")
    private Integer isTpl;


    /** 是否退款 0未退款 1已退款 */
    @ApiModelProperty(value = "是否退款 0未退款 1已退款")
    private Integer isRefund;


    /** 状态1进行中2已完成3未完成 */
    @ApiModelProperty(value = "状态1进行中2已完成3未完成")
    private Integer status;

    @ApiModelProperty(value = "库存唯一值")
    private String uniqueId;

    public void copy(YxStorePink source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
