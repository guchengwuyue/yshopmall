package co.yixiang.modules.cart.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName 购物车数量CartNumParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/19
 **/
@Getter
@Setter
public class CartNumParam {

    @Min(value = 1,message = "数量不在合法范围内")
    @Max(value = 9999,message = "数量不在合法范围内")
    @ApiModelProperty(value = "购物车数量")
    private Integer number;

    @NotNull(message = "参数有误")
    @ApiModelProperty(value = "购物车ID")
    private Long id;

}
