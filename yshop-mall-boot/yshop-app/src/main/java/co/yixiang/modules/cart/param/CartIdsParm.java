package co.yixiang.modules.cart.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName CartIds
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/15
 **/
@Data
public class CartIdsParm {

    @NotNull(message = "参数有误")
    @Schema(description = "购物车ID，多个用,分隔开")
    List<String> ids;
}
