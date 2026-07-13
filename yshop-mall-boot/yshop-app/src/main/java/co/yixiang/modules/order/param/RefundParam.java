package co.yixiang.modules.order.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName RefundParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/6
 **/
@Data
public class RefundParam implements Serializable {

    @JsonProperty(value = "refund_reason_wap_explain")
    @ApiModelProperty(value = "退款备注")
    private String refundReasonWapExplain;

    @JsonProperty(value = "refund_reason_wap_img")
    @ApiModelProperty(value = "退款图片")
    private String refundReasonWapImg;

    @NotBlank(message = "请填写退款原因")
    @ApiModelProperty(value = "退款原因")
    private String text;

    @NotBlank(message = "参数错误")
    @ApiModelProperty(value = "订单唯一值")
    private String uni;
}
