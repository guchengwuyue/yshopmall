package co.yixiang.modules.order.vo;

import co.yixiang.modules.order.service.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单商品对象
 * </p>
 *
 * @author hupeng
 * @date 2019-11-03
 */
@Data
@Builder
public class OrderCartInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private String orderId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "购物车数量")
    private Integer cartNum;

    @Schema(description = "拼团产品ID")
    private Long combinationId;

    @Schema(description = "秒杀产品ID")
    private Long seckillId;


    @Schema(description = "产品信息")
    private ProductDto productInfo;


}
