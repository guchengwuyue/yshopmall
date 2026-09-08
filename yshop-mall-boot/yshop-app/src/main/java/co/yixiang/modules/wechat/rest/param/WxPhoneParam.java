package co.yixiang.modules.wechat.rest.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * @ClassName WxPhoneParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/02/07
 **/
@Getter
@Setter
public class WxPhoneParam {

    @Schema(description = "小程序完整用户信息的加密数据")
    private String encryptedData;

    @Schema(description = "小程序加密算法的初始向量")
    private String iv;
}
