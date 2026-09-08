package co.yixiang.modules.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单购物详情表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@Schema(description = "YxStoreOrderCartInfoQueryVo对象")
public class YxStoreOrderCartInfoQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "订单id")
    private Integer oid;

    @Schema(description = "购物车id")
    private Integer cartId;

    @Schema(description = "商品ID")
    private Integer productId;

    @Schema(description = "购买东西的详细信息")
    private String cartInfo;

    @Schema(description = "唯一id")
    private String unique;

}