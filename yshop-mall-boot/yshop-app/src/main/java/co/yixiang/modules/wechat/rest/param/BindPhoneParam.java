package co.yixiang.modules.wechat.rest.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName BindPhoneParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/2/7
 **/
@Getter
@Setter
public class BindPhoneParam {

    @NotBlank(message = "验证码必填")
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @NotBlank(message = "手机号必填")
    @ApiModelProperty(value = "手机号码")
    private String phone;
}
