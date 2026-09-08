package co.yixiang.modules.order.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ComputeOrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/22
 **/
@Getter
@Setter
@ToString
public class ComputeOrderParam {
    //@NotBlank(message = "请选择地址")
    @Schema(description = "地址ID")
    private String addressId;

    @Schema(description = "优惠券ID")
    private String couponId;

    //@NotBlank(message = "请选择支付方式")
    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "使用积分 1-表示使用")
    private String useIntegral;

    @JsonProperty(value = "shipping_type")
    @Schema(description = "配送方式 1=快递 ，2=门店自提")
    private String shippingType;


    @Schema(description = "拼团ID")
    private String pinkId;

    @Schema(description = "拼团ID")
    private String combinationId;
}
