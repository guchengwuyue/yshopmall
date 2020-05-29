/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/

@Data
@TableName("yx_store_cart")
public class YxStoreCart implements Serializable {

    /** 购物车表ID */
    @TableId
    private Long id;


    /** 用户ID */
    private Integer uid;


    /** 类型 */
    private String type;


    /** 商品ID */
    private Integer productId;


    /** 商品属性 */
    private String productAttrUnique;


    /** 商品数量 */
    private Integer cartNum;


    /** 添加时间 */
    private Integer addTime;


    /** 0 = 未购买 1 = 已购买 */
    private Integer isPay;


    /** 是否删除 */
    private Integer isDel;


    /** 是否为立即购买 */
    private Integer isNew;


    /** 拼团id */
    //@Column(name = "combination_id")
    private Integer combinationId;


    /** 秒杀产品ID */
    private Integer seckillId;


    /** 砍价id */
    private Integer bargainId;


    public void copy(YxStoreCart source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
