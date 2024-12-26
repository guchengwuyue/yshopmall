package co.yixiang.modules.activity.vo;

import cn.hutool.core.util.StrUtil;
import co.yixiang.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 拼团产品表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-11-19
 */
@Data
@ApiModel(value = "YxStoreCombinationQueryVo对象", description = "拼团产品表查询参数")
public class YxStoreCombinationQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商户id")
    private Integer merId;

    @ApiModelProperty(value = "推荐图")
    private String image;

    @ApiModelProperty(value = "轮播图")
    private String images;

    private List<String> sliderImageArr;

    public List<String> getSliderImageArr() {
        if(StrUtil.isNotEmpty(images)){
            return Arrays.asList(images.split(","));
        }else {
            return new ArrayList<>();
        }

    }

    @ApiModelProperty(value = "活动标题")
    private String title;


    @ApiModelProperty(value = "参团人数")
    private Integer people;

    @ApiModelProperty(value = "简介")
    private String info;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;


    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    private Integer combination;

    @ApiModelProperty(value = "是否包邮1是0否")
    private Integer isPostage;

    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(value = "邮费")
    private BigDecimal postage;

    @ApiModelProperty(value = "拼团内容")
    private String description;

    @ApiModelProperty(value = "拼团订单有效时间")
    private Integer effectiveTime;

    @ApiModelProperty(value = "浏览量")
    private Integer browse;

    @ApiModelProperty(value = "单位名")
    private String unitName;

    /** 是否单独分佣 */
    @JsonProperty("is_sub")
    private Integer isSub;


}
