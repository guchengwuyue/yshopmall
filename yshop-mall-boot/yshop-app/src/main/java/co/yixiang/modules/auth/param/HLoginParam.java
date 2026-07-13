package co.yixiang.modules.auth.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName 登陆LoginDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/05/02
 **/
@Getter
@Setter
public class HLoginParam {

    @NotBlank(message = "用户名必填")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码必填")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "分销绑定关系的ID")
    private String spread;


}
