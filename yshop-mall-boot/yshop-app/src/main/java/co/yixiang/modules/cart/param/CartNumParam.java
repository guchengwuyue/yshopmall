package co.yixiang.modules.cart.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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
    @Schema(description = "购物车数量")
    private Integer number;

    @NotNull(message = "参数有误")
    @Schema(description = "购物车ID")
    private Long id;

}
