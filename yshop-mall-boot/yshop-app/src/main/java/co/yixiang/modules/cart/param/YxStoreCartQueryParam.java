package co.yixiang.modules.cart.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 购物车表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-25
 */
@Data
@Schema(description = "YxStoreCartQueryParam对象")
public class YxStoreCartQueryParam  {
    private static final long serialVersionUID = 1L;

    private Integer numType = 0;
}
