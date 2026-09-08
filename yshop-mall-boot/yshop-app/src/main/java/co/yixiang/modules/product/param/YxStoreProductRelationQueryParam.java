package co.yixiang.modules.product.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * <p>
 * 商品点赞和收藏表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-23
 */
@Getter
@Setter
@Schema(description = "查询参数对象")
public class YxStoreProductRelationQueryParam  {

    @NotBlank(message = "参数有误")
    @Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "某种类型的商品(普通商品、秒杀商品)")
    private String category;

    @Schema(description = "collect-收藏，foot-足迹")
    private String type;
}
