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

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_system_user_task")
public class YxSystemUserTask extends BaseDomain {

    @TableId
    private Integer id;


    /** 任务名称 */
    private String name;


    /** 配置原名 */
    private String realName;


    /** 任务类型 */
    private String taskType;


    /** 限定数 */
    private Integer number;


    /** 等级id */
    private Integer levelId;


    /** 排序 */
    private Integer sort;


    /** 是否显示 */
    private Integer isShow;


    /** 是否务必达成任务,1务必达成,0=满足其一 */
    private Integer isMust;


    /** 任务说明 */
    private String illustrate;



    public void copy(YxSystemUserTask source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
