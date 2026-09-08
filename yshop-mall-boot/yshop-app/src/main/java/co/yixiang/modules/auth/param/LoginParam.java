package co.yixiang.modules.auth.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @ClassName LoginParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/01/15
 **/
@Data
public class LoginParam {

    @NotBlank(message = "code参数缺失")
    @Schema(description = "小程序登陆code")
    private String code;

    @Schema(description = "分销绑定关系的ID")
    private String spread;

    @Schema(description = "小程序完整用户信息的加密数据")
    private String encryptedData;

    @Schema(description = "小程序加密算法的初始向量")
    private String iv;
}
