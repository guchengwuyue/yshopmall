package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="YxUserAddressQueryParam对象", description="用户地址表查询参数")
public class YxUserAddressQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private Integer isDel;
}
