package co.yixiang.modules.coupon.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 优惠券发放记录表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxStoreCouponUserQueryParam对象", description="优惠券发放记录表查询参数")
public class YxStoreCouponUserQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
