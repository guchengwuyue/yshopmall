package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-10-13
*/
@Entity
@Data
@Table(name="yx_store_product_attr_value")
public class YxStoreProductAttrValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 商品ID
    @Column(name = "product_id",nullable = false)
    private Integer productId;

    // 商品属性索引值 (attr_value|attr_value[|....])
    @Column(name = "suk",nullable = false)
    private String suk;

    // 属性对应的库存
    @Column(name = "stock",nullable = false)
    private Integer stock;

    // 销量
    @Column(name = "sales",nullable = false)
    private Integer sales;

    // 属性金额
    @Column(name = "price",nullable = false)
    private BigDecimal price;

    // 图片
    @Column(name = "image")
    private String image;

    // 唯一值
    @Column(name = "`unique`",nullable = false)
    private String unique;

    // 成本价
    @Column(name = "cost",nullable = false)
    private BigDecimal cost;

    public void copy(YxStoreProductAttrValue source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}