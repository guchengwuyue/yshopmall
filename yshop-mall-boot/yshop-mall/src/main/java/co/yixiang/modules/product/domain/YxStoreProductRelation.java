package co.yixiang.modules.product.domain;


import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 商品点赞和收藏表
 * </p>
 *
 * @author hupeng
 * @since 2019-10-23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YxStoreProductRelation extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "类型(收藏(collect）、点赞(like)、足迹(foot))")
    private String type;

    @ApiModelProperty(value = "某种类型的商品(普通商品、秒杀商品)")
    private String category;



}
