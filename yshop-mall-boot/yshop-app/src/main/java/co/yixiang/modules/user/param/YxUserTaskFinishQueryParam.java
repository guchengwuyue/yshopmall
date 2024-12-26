package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="YxUserTaskFinishQueryParam对象", description="用户任务完成记录表查询参数")
public class YxUserTaskFinishQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
