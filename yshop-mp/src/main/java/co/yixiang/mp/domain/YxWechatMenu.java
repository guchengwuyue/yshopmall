/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.mp.domain;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
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
    @TableField(fill= FieldFill.INSERT)
    private Integer addTime;


    public void copy(YxWechatMenu source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
