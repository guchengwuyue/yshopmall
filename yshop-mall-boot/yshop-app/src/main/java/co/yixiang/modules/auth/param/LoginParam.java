package co.yixiang.modules.auth.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/01/15
 **/
@Data
public class LoginParam {

    @NotBlank(message = "code参数缺失")
    @ApiModelProperty(value = "小程序登陆code")
    private String code;

    @ApiModelProperty(value = "分销绑定关系的ID")
    private String spread;

    @ApiModelProperty(value = "小程序完整用户信息的加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "小程序加密算法的初始向量")
    private String iv;
}
