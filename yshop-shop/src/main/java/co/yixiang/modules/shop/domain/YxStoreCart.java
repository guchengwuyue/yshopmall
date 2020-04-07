package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author xwb
 * @date 2020-04-02
 */
@Entity
@Data
@Table(name="yx_store_cart")
public class YxStoreCart implements Serializable {

    /** 购物车表ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 用户ID */
    @Column(name = "uid",nullable = false)
    @NotNull
    private Integer uid;

    /** 类型 */
    @Column(name = "type",nullable = false)
    @NotBlank
    private String type;

    /** 商品ID */
    @Column(name = "product_id",nullable = false)
    @NotNull
    private Integer productId;

    /** 商品属性 */
    @Column(name = "product_attr_unique",nullable = false)
    @NotBlank
    private String productAttrUnique;

    /** 商品数量 */
    @Column(name = "cart_num",nullable = false)
    @NotNull
    private Integer cartNum;

    /** 添加时间 */
    @Column(name = "add_time",nullable = false)
    @NotNull
    private Integer addTime;

    /** 0 = 未购买 1 = 已购买 */
    @Column(name = "is_pay",nullable = false)
    @NotNull
    private Integer isPay;

    /** 是否删除 */
    @Column(name = "is_del",nullable = false)
    @NotNull
    private Integer isDel;

    /** 是否为立即购买 */
    @Column(name = "is_new",nullable = false)
    @NotNull
    private Integer isNew;

    /** 拼团id */
    @Column(name = "combination_id")
    private Integer combinationId;

    /** 秒杀产品ID */
    @Column(name = "seckill_id",nullable = false)
    @NotNull
    private Integer seckillId;

    /** 砍价id */
    @Column(name = "bargain_id",nullable = false)
    @NotNull
    private Integer bargainId;

    public void copy(YxStoreCart source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
