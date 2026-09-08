package co.yixiang.modules.user.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName RechargeParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/8
 **/
@Data
public class RechargeParam  implements Serializable {

    @NotBlank(message = "充值参数有误")
    @JsonProperty(value = "rechar_id")
    @Schema(description = "用户充值ID")
    private String recharId;

    @Schema(description = "来源")
    private String from;

    //@NotNull(message = "金额必填")
   // @Min(value = 1,message = "充值金额不能低于1")
    @Schema(description = "充值金额")
    private Double price;

    @JsonProperty(value = "paid_price")
    @Schema(description = "赠送金额")
    private Double paidPrice;

    @Schema(description = "充值单号")
    private String orderSn;
}
