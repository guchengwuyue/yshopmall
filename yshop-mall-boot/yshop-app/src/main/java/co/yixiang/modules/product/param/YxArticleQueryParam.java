package co.yixiang.modules.product.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章管理表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxArticleQueryParam对象")
public class YxArticleQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
