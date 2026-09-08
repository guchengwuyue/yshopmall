package co.yixiang.modules.manage.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hupeng
 * @date 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "商户查询参数")
public class ShoperQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "商品状态,默认为0未支付 1待发货 2待收货 3待评价 4已完成 5退款中 6已退款 7退款")
    private Integer status = 0;

    @Schema(description = "分类")
    private Integer cate = 1;

    private Integer type = 1;
}
