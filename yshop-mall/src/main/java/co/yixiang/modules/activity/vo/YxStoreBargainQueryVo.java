package co.yixiang.modules.activity.vo;

import co.yixiang.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 砍价表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-21
 */
@Data
@ApiModel(value = "YxStoreBargainQueryVo对象", description = "砍价表查询参数")
public class YxStoreBargainQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "砍价产品ID")
    private Long id;

    @ApiModelProperty(value = "关联产品ID")
    private Long productId;

    @ApiModelProperty(value = "砍价活动名称")
    private String title;

    @ApiModelProperty(value = "砍价活动图片")
    private String image;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "砍价产品轮播图")
    private String images;

    @ApiModelProperty(value = "砍价开启时间")
    private Date startTime;

    @ApiModelProperty(value = "砍价结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "砍价产品名称")
    private String storeName;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "砍价金额")
    private BigDecimal price;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "砍价商品最低价")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "每次购买的砍价产品数量")
    private Integer num;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "用户每次砍价的最大金额")
    private BigDecimal bargainMaxPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "用户每次砍价的最小金额")
    private BigDecimal bargainMinPrice;

    @ApiModelProperty(value = "用户每次砍价的次数")
    private Integer bargainNum;

    @ApiModelProperty(value = "砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)")
    private Integer status;

    @ApiModelProperty(value = "砍价详情")
    private String description;

    @ApiModelProperty(value = "反多少积分")
    private BigDecimal giveIntegral;

    @ApiModelProperty(value = "砍价活动简介")
    private String info;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "邮费")
    private BigDecimal postage;

    @ApiModelProperty(value = "砍价规则")
    private String rule;

    @ApiModelProperty(value = "砍价产品浏览量")
    private Integer look;

    @ApiModelProperty(value = "砍价产品分享量")
    private Integer share;

    @ApiModelProperty(value = "砍价产品参与人数")
    private Long people;


}
