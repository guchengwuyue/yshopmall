package co.yixiang.modules.cart.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @ApiModelProperty(value = "购物车数量")
    private Integer cartNum;

    @JsonProperty(value = "new")
    @ApiModelProperty(value = "是否新购买")
    private Integer isNew = 0;

    @NotNull(message = "参数有误")
    @ApiModelProperty(value = "产品ID")
    private Long productId;

    //@NotBlank(message = "参数有误")
    @ApiModelProperty(value = "唯一的ID")
    private String uniqueId;

    @ApiModelProperty(value = "产品拼团ID")
    private Long combinationId = 0L;

    @ApiModelProperty(value = "产品秒杀ID")
    private Long secKillId = 0L;

}
