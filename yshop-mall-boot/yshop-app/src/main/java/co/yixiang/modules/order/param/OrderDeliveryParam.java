package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName OrderPriceParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/26
 **/
@Data
public class OrderDeliveryParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @Schema(description = "订单ID")
    private String orderId;

    @NotBlank(message = "快递单号必填")
    @Schema(description = "快递单号")
    private String deliveryId;

    @NotBlank(message = "快递公司必填")
    @Schema(description = "快递公司")
    private String deliveryName;

    @NotBlank(message = "快递方式必填")
    @Schema(description = "快递方式")
    private String deliveryType;
}
