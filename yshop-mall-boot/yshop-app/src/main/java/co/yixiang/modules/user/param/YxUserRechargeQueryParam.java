package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户充值表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxUserRechargeQueryParam对象")
public class YxUserRechargeQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
