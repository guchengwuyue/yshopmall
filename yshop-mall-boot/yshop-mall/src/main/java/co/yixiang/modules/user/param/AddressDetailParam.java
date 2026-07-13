package co.yixiang.modules.user.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddressDetailParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/28
 **/
@Data
public class AddressDetailParam  implements Serializable {

    @JsonProperty(value = "city_id")
    @ApiModelProperty(value = "城市ID")
    private Integer cityId;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地区")
    private String district;

    @ApiModelProperty(value = "省份")
    private String province;
}
