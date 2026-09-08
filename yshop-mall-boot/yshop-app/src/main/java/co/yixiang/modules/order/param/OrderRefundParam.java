package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName OrderRefundParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/26
 **/
@Data
public class OrderRefundParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @Schema(description = "订单ID")
    private String orderId;

    @NotNull(message = "退款金额必填")
    @Schema(description = "退款金额")
    private String price;

    @NotNull(message = "参数错误")
    @Schema(description = "类型：1同意 2拒绝")
    private Integer type;
}
