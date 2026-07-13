package co.yixiang.modules.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单操作记录表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@ApiModel(value = "YxStoreOrderStatusQueryVo对象", description = "订单操作记录表查询参数")
public class YxStoreOrderStatusQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer oid;

    @ApiModelProperty(value = "操作类型")
    private String changeType;

    @ApiModelProperty(value = "操作备注")
    private String changeMessage;

    @ApiModelProperty(value = "操作时间")
    private Integer changeTime;

}