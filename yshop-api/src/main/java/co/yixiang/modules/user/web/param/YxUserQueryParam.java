package co.yixiang.modules.user.web.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import co.yixiang.common.web.param.QueryParam;

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
@ApiModel(value="YxUserQueryParam对象", description="用户表查询参数")
public class YxUserQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
