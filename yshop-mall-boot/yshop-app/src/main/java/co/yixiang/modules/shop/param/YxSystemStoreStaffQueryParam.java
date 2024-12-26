package co.yixiang.modules.shop.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="YxSystemStoreStaffQueryParam对象", description="门店店员表查询参数")
public class YxSystemStoreStaffQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
