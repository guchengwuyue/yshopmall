package co.yixiang.modules.auth.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : gzlv 2021/7/20 15:09
 */
@Data
public class LoginVerifyParam {

    @NotBlank(message = "手机号必填")
    @ApiModelProperty(value = "手机号码")
    private String account;

    @NotBlank(message = "验证码必填")
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "分销绑定关系的ID")
    private String spread;
}
