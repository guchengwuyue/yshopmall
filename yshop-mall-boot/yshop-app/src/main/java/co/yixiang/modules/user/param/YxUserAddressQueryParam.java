package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户地址表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxUserAddressQueryParam对象")
public class YxUserAddressQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private Integer isDel;
}
