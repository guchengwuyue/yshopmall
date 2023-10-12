package co.yixiang.modules.product.vo;



import cn.hutool.core.util.StrUtil;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.serializer.BigDecimalSerializer;
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
 * 商品表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-19
 */
@Data
@ApiModel(value = "YxStoreProductQueryVo对象", description = "商品表查询参数")
public class YxStoreProductQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)")
    private Integer merId;

    @ApiModelProperty(value = "商品图片")
    private String image;

    private String image_base;

    private String codeBase;

    public String getImage_base() {
        return image;
    }

    @ApiModelProperty(value = "是否收藏")
    private Boolean userCollect = false;

    @ApiModelProperty(value = "是否喜欢")
    private Boolean userLike = false;

    @ApiModelProperty(value = "轮播图，多个用,分割")
    private String sliderImage;

    private List<String> sliderImageArr;

    public List<String> getSliderImageArr() {
        //Arrays.asList(sliderImage.split(","));
        if(StrUtil.isNotEmpty(sliderImage)){
            return Arrays.asList(sliderImage.split(","));
        }
        return new ArrayList<>();
    }

    private YxStoreProductAttrValue attrInfo;

    @ApiModelProperty(value = "商品名称")
    private String storeName;

    @ApiModelProperty(value = "商品简介")
    private String storeInfo;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "分类id")
    private String cateId;

    @ApiModelProperty(value = "商品价格")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    @ApiModelProperty(value = "会员价格")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "市场价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal otPrice;

    @ApiModelProperty(value = "邮费")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal postage;

    @ApiModelProperty(value = "单位名")
    private String unitName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "产品描述")
    private String description;

    @ApiModelProperty(value = "是否包邮")
    private Integer isPostage;

    @ApiModelProperty(value = "成本价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cost;

    @ApiModelProperty(value = "秒杀状态 0 未开启 1已开启")
    private Integer isSeckill;

    @ApiModelProperty(value = "砍价状态 0未开启 1开启")
    private Integer isBargain;

    @ApiModelProperty(value = "是否优品推荐")
    private Integer isGood;

    @ApiModelProperty(value = "虚拟销量")
    private Integer ficti;

    @ApiModelProperty(value = "浏览量")
    private Integer browse;

    @ApiModelProperty(value = "状态（0：未上架，1：上架）")
    private Integer isShow;

    @ApiModelProperty(value = "获得积分")
    private BigDecimal giveIntegral;

    @ApiModelProperty(value = "运费模板ID")
    private Integer tempId;

    /** 是否单独分佣 */
    @ApiModelProperty(value = "是否单独分佣")
    private Integer isSub;

    /** 规格 0单 1多 */
    @ApiModelProperty(value = "规格 0单 1多 ")
    private Integer specType;

    /** 是否开启积分兑换*/
    @ApiModelProperty(value = "是否开启积分兑换")
    private Integer isIntegral;

    /** 需要多少积分*/
    @ApiModelProperty(value = "需要多少积分")
    private Integer integral;

}
