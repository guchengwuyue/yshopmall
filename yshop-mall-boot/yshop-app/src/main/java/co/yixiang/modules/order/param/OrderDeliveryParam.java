package co.yixiang.modules.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName OrderPriceParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/26
 **/
@Data
public class OrderDeliveryParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @NotBlank(message = "快递单号必填")
    @ApiModelProperty(value = "快递单号")
    private String deliveryId;

    @NotBlank(message = "快递公司必填")
    @ApiModelProperty(value = "快递公司")
    private String deliveryName;

    @NotBlank(message = "快递方式必填")
    @ApiModelProperty(value = "快递方式")
    private String deliveryType;
}
