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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_system_user_level")
public class YxSystemUserLevel extends BaseDomain {

    @TableId
    private Integer id;

    /** 会员名称 */
    @NotBlank(message = "名称必填")
    private String name;


    /** 购买金额 */
    private BigDecimal money;


    /** 有效时间 */
    private Integer validDate;


    /** 是否为永久会员 */
    private Integer isForever;


    /** 是否购买,1=购买,0=不购买 */
    private Integer isPay;


    /** 是否显示 1=显示,0=隐藏 */
    private Integer isShow;


    /** 会员等级 */
    @NotNull(message = "请输入会员等级")
    private Integer grade;


    /** 享受折扣 */
    @NotNull(message = "请输入会员折扣")
    private BigDecimal discount;

    /** 会员卡背景 */
    @NotBlank(message = "请上传会员背景")
    private String image;


    /** 会员图标 */
    @NotBlank(message = "请上传会员图标")
    private String icon;


    /** 说明 */
    @TableField(value = "`explain`")
    private String explain;



    public void copy(YxSystemUserLevel source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
