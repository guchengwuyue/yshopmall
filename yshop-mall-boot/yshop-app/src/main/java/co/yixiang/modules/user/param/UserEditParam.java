package co.yixiang.modules.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName UserEditParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/02/07
 **/
@Data
public class UserEditParam implements Serializable {

    @NotBlank(message = "请上传头像")
    @Schema(description = "用户头像")
    private String avatar;

    @NotBlank(message = "请填写昵称")
    @Size(min = 1, max = 60,message = "长度超过了限制")
    @Schema(description = "用户昵称")
    private String nickname;


}
