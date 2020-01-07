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
@Table(name="yx_store_product_attr")
public class YxStoreProductAttr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    // 商品ID
    @Column(name = "product_id",nullable = false)
    private Integer productId;

    // 属性名
    @Column(name = "attr_name",nullable = false)
    private String attrName;

    // 属性值
    @Column(name = "attr_values",nullable = false)
    private String attrValues;

    public void copy(YxStoreProductAttr source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}