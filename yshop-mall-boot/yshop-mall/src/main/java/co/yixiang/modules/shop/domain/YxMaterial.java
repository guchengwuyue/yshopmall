/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author hupeng
* @date 2020-05-12
*/

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yx_material")
public class YxMaterial extends BaseDomain {

    /** PK */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;


    /** 创建者ID */
    private String createId;


    /** 类型1、图片；2、视频 */
    private String type;


    /** 分组ID */
    private String groupId;


    /** 素材名 */
    private String name;


    /** 素材链接 */
    private String url;


    public void copy(YxMaterial source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
