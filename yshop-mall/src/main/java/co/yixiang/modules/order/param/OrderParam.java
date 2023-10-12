package co.yixiang.modules.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName OrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/28
 **/
@Data
public class OrderParam implements Serializable {

    @ApiModelProperty(value = "地址ID")
    private String addressId;

    @ApiModelProperty(value = "砍价产品ID")
    private String bargainId;

    @ApiModelProperty(value = "拼团ID")
    private String combinationId;

    @ApiModelProperty(value = "优惠券ID")
    private String couponId;

    @ApiModelProperty(value = "来源")
    private String from;

    @Size(max = 200,message = "长度超过了限制")
    @ApiModelProperty(value = "备注")
    private String mark;

    @NotBlank(message="请选择支付方式")
    @ApiModelProperty(value = "支付方式")
    private String payType;

    @ApiModelProperty(value = "门店电话")
    private String phone;

    @ApiModelProperty(value = "拼团id 0没有拼团")
    private String pinkId;

    @ApiModelProperty(value = "门店联系人")
    private String realName;

    @ApiModelProperty(value = "秒杀产品ID")
    private String seckillId;

    @ApiModelProperty(value = "配送方式 1=快递 ，2=门店自提")
    private String shippingType;

    @ApiModelProperty(value = "使用积分 1-表示使用")
    private String useIntegral;

    @ApiModelProperty(value = "支付渠道(0微信公众号1微信小程序) ")
    private String isChannel;

    @ApiModelProperty(value = "门店ID")
    private String storeId;

}
