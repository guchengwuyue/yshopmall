package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName PayDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/7
 **/
@Data
public class PayParam implements Serializable {

    @Schema(description = "来源")
    private String from;

    @NotBlank(message = "选择支付类型")
    @Schema(description = "支付类型")
    private String paytype;

    @NotBlank(message = "参数错误")
    @Schema(description = "订单ID")
    private String uni;
}
