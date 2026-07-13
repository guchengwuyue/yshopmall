package co.yixiang.modules.activity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName UserExtParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/13
 **/
@Data
public class UserExtParam implements Serializable {

    @ApiModelProperty(value = "提现支付宝用户名")
    private String alipayCode;

    @NotBlank(message = "体现类型不能为空")
    @ApiModelProperty(value = "提现类型 weixin alipay")
    private String extractType;

    @NotBlank(message = "金额不能为空")
    @ApiModelProperty(value = "提现金额")
    private String money;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "支付宝账号")
    private String name;
}
