package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户任务完成记录表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxUserTaskFinishQueryParam对象")
public class YxUserTaskFinishQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
