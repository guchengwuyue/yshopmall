package co.yixiang.modules.user.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName AddressParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/28
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressParam  implements Serializable {

    @ApiModelProperty(value = "地址ID")
    private String id;
    @NotBlank
    @Size(min = 1, max = 30,message = "长度超过了限制")
    @ApiModelProperty(value = "收货地址真实名字")
    private String real_name;

    @ApiModelProperty(value = "收货地址邮编")
    private String post_code;

    @ApiModelProperty(value = "是否默认收货地址 true是 false否")
    private String is_default;

    private String wx_export;

    @NotBlank
    @Size(min = 1, max = 60,message = "长度超过了限制")
    @ApiModelProperty(value = "收货详细地址")
    private String detail;

    @NotBlank
    @ApiModelProperty(value = "收货手机号码")
    private String phone;

    @ApiModelProperty(value = "收货地址详情")
    private AddressDetailParam address;
}
