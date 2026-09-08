package co.yixiang.modules.manage.param;

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
public class OrderRemarkParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @Schema(description = "订单ID")
    private String orderId;

    @NotBlank(message = "备注必填")
    @Schema(description = "备注")
    private String remark;
}
