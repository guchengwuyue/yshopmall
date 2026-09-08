package co.yixiang.modules.order.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName RefundParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/6
 **/
@Data
public class RefundParam implements Serializable {

    @JsonProperty(value = "refund_reason_wap_explain")
    @Schema(description = "退款备注")
    private String refundReasonWapExplain;

    @JsonProperty(value = "refund_reason_wap_img")
    @Schema(description = "退款图片")
    private String refundReasonWapImg;

    @NotBlank(message = "请填写退款原因")
    @Schema(description = "退款原因")
    private String text;

    @NotBlank(message = "参数错误")
    @Schema(description = "订单唯一值")
    private String uni;
}
