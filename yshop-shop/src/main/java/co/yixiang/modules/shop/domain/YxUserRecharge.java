/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_user_recharge")
public class YxUserRecharge extends BaseDomain {

    @TableId
    private Long id;


    /** 充值用户UID */
    private Long uid;


    /** 订单号 */
    private String orderId;


    /** 充值金额 */
    private BigDecimal price;


    /** 充值类型 */
    private String rechargeType;


    /** 是否充值 */
    private Integer paid;


    /** 充值支付时间 */
    private Date payTime;


    /** 退款金额 */
    private BigDecimal refundPrice;


    /** 昵称 */
    private String nickname;


    public void copy(YxUserRecharge source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
