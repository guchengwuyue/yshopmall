/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @author hupeng
* @date 2020-05-12
*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("yx_system_group_data")
public class YxSystemGroupData extends BaseDomain {

    /** 组合数据详情ID */
    @TableId
    private Integer id;


    /** 对应的数据名称 */
    private String groupName;


    /** 数据组对应的数据值（json数据） */
    private String value;


    /** 数据排序 */
    private Integer sort;


    /** 状态（1：开启；2：关闭；） */
    private Integer status;


    public void copy(YxSystemGroupData source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
