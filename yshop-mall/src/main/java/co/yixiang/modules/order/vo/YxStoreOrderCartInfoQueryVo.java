package co.yixiang.modules.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单购物详情表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@ApiModel(value = "YxStoreOrderCartInfoQueryVo对象", description = "订单购物详情表查询参数")
public class YxStoreOrderCartInfoQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer oid;

    @ApiModelProperty(value = "购物车id")
    private Integer cartId;

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "购买东西的详细信息")
    private String cartInfo;

    @ApiModelProperty(value = "唯一id")
    private String unique;

}