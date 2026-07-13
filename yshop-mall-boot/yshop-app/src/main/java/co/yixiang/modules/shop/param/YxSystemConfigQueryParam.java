package co.yixiang.modules.shop.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 配置表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxSystemConfigQueryParam对象", description="配置表查询参数")
public class YxSystemConfigQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
