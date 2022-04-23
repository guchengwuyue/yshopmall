/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
@TableName("yx_wechat_menu")
public class YxWechatMenu implements Serializable {

    @TableId(value = "`key`")
    private String key;


    /** 缓存数据 */
    private String result;


    /** 缓存时间 */
    @TableField(fill = FieldFill.INSERT)
    private Integer addTime;


    public void copy(YxWechatMenu source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
