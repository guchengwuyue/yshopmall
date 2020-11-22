/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_material_group")
public class YxMaterialGroup implements Serializable {

    /** PK */
    @TableId(type = IdType.UUID)
    private String id;

    /** 逻辑删除标记（0：显示；1：隐藏） */
    @TableLogic
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Boolean delFlag;


    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    /** 创建者ID */
    private String createId;


    /** 分组名 */
    private String name;


    public void copy(YxMaterialGroup source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
