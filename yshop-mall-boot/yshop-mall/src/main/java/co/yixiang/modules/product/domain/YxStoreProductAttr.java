/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.product.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("yx_store_product_attr")
public class YxStoreProductAttr implements Serializable {

    @TableId
    private Long id;


    /** 商品ID */
    private Long productId;


    /** 属性名 */
    private String attrName;


    /** 属性值 */
    private String attrValues;


    public void copy(YxStoreProductAttr source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
