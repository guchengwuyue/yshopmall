package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户等级记录表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxUserLevelQueryParam对象", description="用户等级记录表查询参数")
public class YxUserLevelQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
