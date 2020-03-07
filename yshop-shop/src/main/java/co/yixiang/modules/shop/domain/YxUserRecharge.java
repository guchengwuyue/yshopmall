package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-03-02
*/
@Entity
@Data
@Table(name="yx_user_recharge")
public class YxUserRecharge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 充值用户UID */
    @Column(name = "uid")
    private Integer uid;

    /** 订单号 */
    @Column(name = "order_id",unique = true)
    private String orderId;

    /** 充值金额 */
    @Column(name = "price")
    private BigDecimal price;

    /** 充值类型 */
    @Column(name = "recharge_type")
    private String rechargeType;

    /** 是否充值 */
    @Column(name = "paid")
    private Integer paid;

    /** 充值支付时间 */
    @Column(name = "pay_time")
    private Integer payTime;

    /** 充值时间 */
    @Column(name = "add_time")
    private Integer addTime;

    /** 退款金额 */
    @Column(name = "refund_price")
    private BigDecimal refundPrice;

    /** 昵称 */
    @Column(name = "nickname")
    private String nickname;

    public void copy(YxUserRecharge source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}