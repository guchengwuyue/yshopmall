package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ExpressParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/9
 **/
@Data
public class ExpressParam  implements Serializable {

    @Schema(description = "订单编号")
    private String orderCode;

    @Schema(description = "快递公司编码")
    private String shipperCode;

    @Schema(description = "物流单号")
    private String logisticCode;
}
