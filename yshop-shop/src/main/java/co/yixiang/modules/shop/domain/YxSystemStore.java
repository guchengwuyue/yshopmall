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
import java.io.Serializable;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_system_store")
public class YxSystemStore extends BaseDomain {

    @TableId
    private Integer id;


    /** 门店名称 */
    @NotBlank(message = "请填写门店名称")
    private String name;


    /** 简介 */
    @NotBlank(message = "请填写门店简介")
    private String introduction;


    /** 手机号码 */
    @NotBlank(message = "请填手机号码")
    private String phone;


    /** 省市区 */
    @NotBlank(message = "请填地址")
    private String address;


    /** 详细地址 */
    private String detailedAddress;


    /** 门店logo */
    @NotBlank(message = "请上传门店logo")
    private String image;


    /** 纬度 */
    @NotBlank(message = "请输入纬度")
    private String latitude;


    /** 经度 */
    @NotBlank(message = "请输入经度")
    private String longitude;


    /** 核销有效日期 */
    @NotBlank(message = "请输入核销时效")
    private String validTime;


    /** 每日营业开关时间 */
    @NotBlank(message = "请输入营业时间")
    private String dayTime;


    /** 是否显示 */
    private Integer isShow;

    private Date validTimeEnd;

    private Date validTimeStart;

    private Date dayTimeStart;

    private Date dayTimeEnd;


    public void copy(YxSystemStore source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
