package co.yixiang.modules.auth.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : gzlv 2021/7/20 17:27
 */
@Data
public class UpdatePasswordParam {

    @NotBlank(message = "手机号必填")
    @ApiModelProperty(value = "手机号码")
    private String account;

    @NotBlank(message = "验证码必填")
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @NotBlank(message = "密码必填")
    @ApiModelProperty(value = "密码")
    private String password;
}
