package co.yixiang.modules.sales.service.vo;

import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : gzlv 2021/6/27 22:40
 */
@Data
public class YxStoreOrderCartInfoVo {

    @TableId
    private Long id;


    /** 订单id */
    private Long oid;


    /** 购物车id */
    private Long cartId;


    /** 商品ID */
    private Long productId;


    /** 购买东西的详细信息 */
    private YxStoreCartQueryVo cartInfo;


    /** 唯一id */
    @TableField(value = "`unique`")
    private String unique;

    /** 是否能售后0不能1能 */
    private Integer isAfterSales;

    /** 可退價格 */
    private BigDecimal refundablePrice;

    /** 申请原因 */
    private String reasons;

}
