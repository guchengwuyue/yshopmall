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

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_system_store_staff")
public class YxSystemStoreStaff extends BaseDomain {

    @TableId
    private Integer id;


    /** 微信用户id */
    private Long uid;


    /** 店员头像 */
    @NotBlank(message = "请选择用户")
    private String avatar;


    /** 门店id */
    @NotNull(message = "请选择门店")
    private Integer storeId;


    /** 店员名称 */
    @NotBlank(message = "请输入店员名称")
    private String staffName;


    /** 手机号码 */
    @NotBlank(message = "请输入手机号码")
    private String phone;


    /** 核销开关 */
    private Integer verifyStatus;


    /** 状态 */
    private Integer status;


    /** 微信昵称 */
    private String nickname;


    /** 所属门店 */
    private String storeName;


    public void copy(YxSystemStoreStaff source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
