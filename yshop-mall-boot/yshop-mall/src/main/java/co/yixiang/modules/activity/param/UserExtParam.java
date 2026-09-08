package co.yixiang.modules.activity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName UserExtParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/13
 **/
@Data
public class UserExtParam implements Serializable {

    @Schema(description = "提现支付宝用户名")
    private String alipayCode;

    @NotBlank(message = "体现类型不能为空")
    @Schema(description = "提现类型 weixin alipay")
    private String extractType;

    @NotBlank(message = "金额不能为空")
    @Schema(description = "提现金额")
    private String money;

    @Schema(description = "微信号")
    private String weixin;

    @Schema(description = "支付宝账号")
    private String name;
}
