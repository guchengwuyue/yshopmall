package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 等级任务设置 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxSystemUserTaskQueryParam对象")
public class YxSystemUserTaskQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
