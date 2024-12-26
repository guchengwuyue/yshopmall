package co.yixiang.modules.shop.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 门店自提 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="门店自提", description="门店自提查询参数")
public class YxSystemStoreQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "经度")
    private String longitude;

}
