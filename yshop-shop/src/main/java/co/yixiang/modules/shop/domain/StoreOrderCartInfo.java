package co.yixiang.modules.shop.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName StoreOrderCartInfo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/14
 **/

@Entity
@Data
@Table(name="yx_store_order_cart_info")
public class StoreOrderCartInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "oid")
    private Integer oid;

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "cart_info")
    private String cartInfo;

    @Column(name = "unique")
    private String unique;


}
