package co.yixiang.modules.wechat.rest.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * @ClassName BindPhoneParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/2/7
 **/
@Getter
@Setter
public class BindPhoneParam {

    @NotBlank(message = "验证码必填")
    @Schema(description = "验证码")
    private String captcha;

    @NotBlank(message = "手机号必填")
    @Schema(description = "手机号码")
    private String phone;
}
