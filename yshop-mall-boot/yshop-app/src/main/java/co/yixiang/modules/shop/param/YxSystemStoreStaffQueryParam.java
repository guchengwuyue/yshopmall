package co.yixiang.modules.shop.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店店员表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxSystemStoreStaffQueryParam对象")
public class YxSystemStoreStaffQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
