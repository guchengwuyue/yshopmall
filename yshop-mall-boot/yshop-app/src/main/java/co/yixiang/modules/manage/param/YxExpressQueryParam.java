package co.yixiang.modules.manage.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 快递公司表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxExpressQueryParam对象")
public class YxExpressQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
