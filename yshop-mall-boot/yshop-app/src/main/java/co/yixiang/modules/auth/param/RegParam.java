package co.yixiang.modules.auth.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @ClassName RegParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/25
 **/
@Data
public class RegParam {

    @NotBlank(message = "手机号必填")
    @Schema(description = "手机号码")
    private String account;

    //@NotBlank(message = "验证码必填")
    @Schema(description = "验证码")
    private String captcha;

    @NotBlank(message = "密码必填")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "分销绑定关系的ID")
    private String spread;

    @Schema(description = "邀请码")
    private String inviteCode;
}
