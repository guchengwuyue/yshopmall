/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
@TableName("yx_wechat_template")
public class YxWechatTemplate extends BaseDomain {

    /** 模板id */
    @TableId
    private Integer id;


    /** 模板编号 */
    private String tempkey;


    /** 模板名 */
    private String name;


    /** 回复内容 */
    private String content;


    /** 模板ID */
    private String tempid;


    /** 状态 */
    private Integer status;


    public void copy(YxWechatTemplate source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
