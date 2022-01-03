/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2020-05-14
 */
@Data
@TableName("user_avatar")
public class UserAvatar implements Serializable {

    @TableId
    private Long id;


    /** 真实文件名 */
    private String realName;


    /** 路径 */
    private String path;


    /** 大小 */
    private String size;


    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;


    public void copy(UserAvatar source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
