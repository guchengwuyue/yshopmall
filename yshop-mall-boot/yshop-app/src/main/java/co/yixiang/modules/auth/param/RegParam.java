package co.yixiang.modules.auth.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RegParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/25
 **/
@Data
public class RegParam {

    @NotBlank(message = "手机号必填")
    @ApiModelProperty(value = "手机号码")
    private String account;

    //@NotBlank(message = "验证码必填")
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @NotBlank(message = "密码必填")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "分销绑定关系的ID")
    private String spread;

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
}
