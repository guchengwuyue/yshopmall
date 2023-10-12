package co.yixiang.modules.product.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxStoreProductQueryParam对象", description="商品表查询参数")
public class YxStoreProductQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别")
    private String type;

    @ApiModelProperty(value = "分类ID")
    private String sid;

    @ApiModelProperty(value = "是否新品")
    private String news;

    @ApiModelProperty(value = "是否积分兑换商品")
    private Integer isIntegral;

    @ApiModelProperty(value = "价格排序")
    private String priceOrder;

    @ApiModelProperty(value = "销量排序")
    private String salesOrder;

    @ApiModelProperty(value = "关键字")
    private String keyword;
}
