package co.yixiang.modules.auth.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName VerityParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/25
 **/
@Data
public class VerityParam {

    @NotBlank(message = "手机号必填")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "短信类型 bind绑定手机短信 login登陆短信 register注册短信")
    private String type;
}
