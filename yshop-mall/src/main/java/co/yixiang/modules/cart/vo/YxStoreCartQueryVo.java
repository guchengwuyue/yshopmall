package co.yixiang.modules.cart.vo;

import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.serializer.DoubleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 购物车表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-25
 */
@Data
@ApiModel(value = "YxStoreCartQueryVo对象", description = "购物车表查询参数")
public class YxStoreCartQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车表ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品属性")
    private String productAttrUnique;

    @ApiModelProperty(value = "商品数量")
    private Integer cartNum;


    @ApiModelProperty(value = "拼团id")
    private Long combinationId;

    @ApiModelProperty(value = "秒杀产品ID")
    private Long seckillId;

    @ApiModelProperty(value = "砍价id")
    private Long bargainId;

    @ApiModelProperty(value = "商品信息")
    private YxStoreProductQueryVo productInfo;

    @ApiModelProperty(value = "成本价")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double costPrice;

    @ApiModelProperty(value = "真实价格")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double truePrice;

    @ApiModelProperty(value = "真实库存")
    private Integer trueStock;

    @JsonSerialize(using = DoubleSerializer.class)
    @ApiModelProperty(value = "vip真实价格")
    private Double vipTruePrice;

    @ApiModelProperty(value = "唯一id")
    private String unique;

    @ApiModelProperty(value = "是否评价")
    private Long isReply;

}
