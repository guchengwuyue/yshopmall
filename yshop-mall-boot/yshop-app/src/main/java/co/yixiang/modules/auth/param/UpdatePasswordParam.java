package co.yixiang.modules.auth.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author : gzlv 2021/7/20 17:27
 */
@Data
public class UpdatePasswordParam {

    @NotBlank(message = "手机号必填")
    @Schema(description = "手机号码")
    private String account;

    @NotBlank(message = "验证码必填")
    @Schema(description = "验证码")
    private String captcha;

    @NotBlank(message = "密码必填")
    @Schema(description = "密码")
    private String password;
}
