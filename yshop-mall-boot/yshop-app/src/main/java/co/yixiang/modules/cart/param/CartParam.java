package co.yixiang.modules.cart.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @ClassName 添加购物车CartParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/19
 **/
@Getter
@Setter
public class CartParam {

    @Min(value = 1,message = "数量不在合法范围内")
    @Max(value = 9999,message = "数量不在合法范围内")
    @Schema(description = "购物车数量")
    private Integer cartNum;

    @JsonProperty(value = "new")
    @Schema(description = "是否新购买")
    private Integer isNew = 0;

    @NotNull(message = "参数有误")
    @Schema(description = "产品ID")
    private Long productId;

    //@NotBlank(message = "参数有误")
    @Schema(description = "唯一的ID")
    private String uniqueId;

    @Schema(description = "产品拼团ID")
    private Long combinationId = 0L;

    @Schema(description = "产品秒杀ID")
    private Long secKillId = 0L;

}
