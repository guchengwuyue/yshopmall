package co.yixiang.modules.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName OrderRefundParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/26
 **/
@Data
public class OrderRefundParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @NotNull(message = "退款金额必填")
    @ApiModelProperty(value = "退款金额")
    private String price;

    @NotNull(message = "参数错误")
    @ApiModelProperty(value = "类型：1同意 2拒绝")
    private Integer type;
}
