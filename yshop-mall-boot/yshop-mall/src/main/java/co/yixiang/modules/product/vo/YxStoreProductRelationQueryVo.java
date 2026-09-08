package co.yixiang.modules.product.vo;

import co.yixiang.serializer.DoubleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品点赞和收藏表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-23
 */
@Data
@Schema(description = "YxStoreProductRelationQueryVo对象")
public class YxStoreProductRelationQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "类型(收藏(collect）、点赞(like))")
    private String type;

    @Schema(description = "某种类型的商品(普通商品、秒杀商品)")
    private String category;

    @Schema(description = "添加时间")
    private Date createTime;

    @Schema(description = "产品图片")
    private String image;

    @Schema(description = "是否显示")
    private Integer isShow;

    @Schema(description = "原价")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double otPrice;

    @Schema(description = "父ID")
    private Integer pid;

    @Schema(description = "产品价格")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double price;

    @Schema(description = "产品销量")
    private Integer sales;

    @Schema(description = "商品名称")
    private String storeName;

    @Schema(description = "是否开启积分兑换")
    private Integer isIntegral;

}
