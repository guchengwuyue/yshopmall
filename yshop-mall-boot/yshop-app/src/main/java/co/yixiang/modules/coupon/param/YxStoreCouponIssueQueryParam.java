package co.yixiang.modules.coupon.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 优惠券前台领取表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxStoreCouponIssueQueryParam对象")
public class YxStoreCouponIssueQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
