package co.yixiang.modules.coupon.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 优惠券表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxStoreCouponQueryParam对象", description="优惠券表查询参数")
public class YxStoreCouponQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "参数有误")
    @ApiModelProperty(value = "优惠券ID")
    private String couponId;
}
