package co.yixiang.modules.manage.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hupeng
 * @date 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="商户查询参数", description="商户查询参数")
public class ShoperQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品状态,默认为0未支付 1待发货 2待收货 3待评价 4已完成 5退款中 6已退款 7退款")
    private Integer status = 0;

    @ApiModelProperty(value = "分类")
    private Integer cate = 1;

    private Integer type = 1;
}
