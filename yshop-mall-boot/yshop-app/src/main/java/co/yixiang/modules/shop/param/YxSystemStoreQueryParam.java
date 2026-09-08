package co.yixiang.modules.shop.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "门店自提")
public class YxSystemStoreQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "经度")
    private String longitude;

}
