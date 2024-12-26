package co.yixiang.modules.activity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName BargainShareParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/20
 **/
@Getter
@Setter
public class PinkShareParam {
    @NotBlank(message = "参数有误")
    private String id;

    @ApiModelProperty(value = "来源")
    private String from;
}
