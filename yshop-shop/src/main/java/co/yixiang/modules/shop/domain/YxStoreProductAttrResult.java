package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-13
*/
@Entity
@Data
@Table(name="yx_store_product_attr_result")
public class YxStoreProductAttrResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 商品ID
    @Column(name = "product_id",nullable = false)
    private Integer productId;

    // 商品属性参数
    @Column(name = "result",nullable = false)
    private String result;

    // 上次修改时间
    @Column(name = "change_time",nullable = false)
    private Integer changeTime;

    public void copy(YxStoreProductAttrResult source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}