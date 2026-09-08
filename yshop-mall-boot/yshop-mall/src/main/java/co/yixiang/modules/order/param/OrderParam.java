package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName OrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/28
 **/
@Data
public class OrderParam implements Serializable {

    @Schema(description = "地址ID")
    private String addressId;


    @Schema(description = "拼团ID")
    private String combinationId;

    @Schema(description = "优惠券ID")
    private String couponId;

    @Schema(description = "来源")
    private String from;

    @Size(max = 200,message = "长度超过了限制")
    @Schema(description = "备注")
    private String mark;

    @NotBlank(message="请选择支付方式")
    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "门店电话")
    private String phone;

    @Schema(description = "拼团id 0没有拼团")
    private String pinkId;

    @Schema(description = "门店联系人")
    private String realName;

    @Schema(description = "秒杀产品ID")
    private String seckillId;

    @Schema(description = "配送方式 1=快递 ，2=门店自提")
    private String shippingType;

    @Schema(description = "使用积分 1-表示使用")
    private String useIntegral;

    @Schema(description = "支付渠道(0微信公众号1微信小程序) ")
    private String isChannel;

    @Schema(description = "门店ID")
    private String storeId;

}
