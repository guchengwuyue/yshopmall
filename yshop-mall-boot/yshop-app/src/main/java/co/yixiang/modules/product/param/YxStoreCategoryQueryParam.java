package co.yixiang.modules.product.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxStoreCategoryQueryParam对象", description="商品分类表查询参数")
public class YxStoreCategoryQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
