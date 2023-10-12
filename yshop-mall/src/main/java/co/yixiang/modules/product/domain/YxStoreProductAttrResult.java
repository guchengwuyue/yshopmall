/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.product.domain;

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
@TableName("yx_store_product_attr_result")
public class YxStoreProductAttrResult implements Serializable {

    @TableId
    private Long id;


    /** 商品ID */
    private Long productId;


    /** 商品属性参数 */
    private String result;


    /** 上次修改时间 */
    private Date changeTime;


    public void copy(YxStoreProductAttrResult source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
