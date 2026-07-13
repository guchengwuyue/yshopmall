package co.yixiang.modules.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ProductOrderParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/23
 **/
@Getter
@Setter
public class ProductOrderParam {

    @NotBlank(message = "参数有误")
    @ApiModelProperty(value = "订单唯一值")
    private String unique;
}
