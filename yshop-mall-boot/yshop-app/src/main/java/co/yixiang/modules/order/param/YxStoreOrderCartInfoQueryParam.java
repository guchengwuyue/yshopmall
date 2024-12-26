package co.yixiang.modules.order.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单购物详情表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxStoreOrderCartInfoQueryParam对象", description="订单购物详情表查询参数")
public class YxStoreOrderCartInfoQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
