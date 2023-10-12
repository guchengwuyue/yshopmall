package co.yixiang.modules.order.vo;

import co.yixiang.modules.order.service.dto.ProductDto;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "购物车数量")
    private Integer cartNum;

    @ApiModelProperty(value = "拼团产品ID")
    private Long combinationId;

    @ApiModelProperty(value = "秒杀产品ID")
    private Long seckillId;

    @ApiModelProperty(value = "砍价产品ID")
    private Long bargainId;

    @ApiModelProperty(value = "产品信息")
    private ProductDto productInfo;


}
