/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.order.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-12
*/

@Data
@TableName("yx_store_order_status")
public class YxStoreOrderStatus implements Serializable {

    @TableId
    private Long id;


    /** 订单id */
    private Long oid;


    /** 操作类型 */
    private String changeType;


    /** 操作备注 */
    private String changeMessage;


    /** 操作时间 */
    private Date changeTime;


    public void copy(YxStoreOrderStatus source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
