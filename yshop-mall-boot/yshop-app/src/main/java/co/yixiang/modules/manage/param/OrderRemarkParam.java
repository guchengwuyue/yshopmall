package co.yixiang.modules.manage.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName OrderPriceParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/26
 **/
@Data
public class OrderRemarkParam implements Serializable {

    @NotBlank(message = "订单编号错误")
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @NotBlank(message = "备注必填")
    @ApiModelProperty(value = "备注")
    private String remark;
}
