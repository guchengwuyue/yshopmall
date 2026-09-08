package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxUserQueryParam对象")
public class YxUserQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
