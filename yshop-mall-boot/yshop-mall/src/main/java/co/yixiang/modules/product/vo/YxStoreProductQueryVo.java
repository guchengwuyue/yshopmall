package co.yixiang.modules.product.vo;



import cn.hutool.core.util.StrUtil;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxStoreProductQueryVo对象")
public class YxStoreProductQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "商品id")
    private Long id;

    @Schema(description = "商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)")
    private Integer merId;

    @Schema(description = "商品图片")
    private String image;

    private String image_base;

    private String codeBase;

    public String getImage_base() {
        return image;
    }

    @Schema(description = "是否收藏")
    private Boolean userCollect = false;

    @Schema(description = "是否喜欢")
    private Boolean userLike = false;

    @Schema(description = "轮播图，多个用,分割")
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

    @Schema(description = "商品名称")
    private String storeName;

    @Schema(description = "商品简介")
    private String storeInfo;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "分类id")
    private String cateId;

    @Schema(description = "商品价格")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    @Schema(description = "会员价格")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal vipPrice;

    @Schema(description = "市场价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal otPrice;

    @Schema(description = "邮费")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal postage;

    @Schema(description = "单位名")
    private String unitName;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "销量")
    private Integer sales;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "产品描述")
    private String description;

    @Schema(description = "是否包邮")
    private Integer isPostage;

    @Schema(description = "成本价")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cost;

    @Schema(description = "秒杀状态 0 未开启 1已开启")
    private Integer isSeckill;


    @Schema(description = "是否优品推荐")
    private Integer isGood;

    @Schema(description = "虚拟销量")
    private Integer ficti;

    @Schema(description = "浏览量")
    private Integer browse;

    @Schema(description = "状态（0：未上架，1：上架）")
    private Integer isShow;

    @Schema(description = "获得积分")
    private BigDecimal giveIntegral;

    @Schema(description = "运费模板ID")
    private Integer tempId;

    /** 是否单独分佣 */
    @Schema(description = "是否单独分佣")
    private Integer isSub;

    /** 规格 0单 1多 */
    @Schema(description = "规格 0单 1多 ")
    private Integer specType;

    /** 是否开启积分兑换*/
    @Schema(description = "是否开启积分兑换")
    private Integer isIntegral;

    /** 需要多少积分*/
    @Schema(description = "需要多少积分")
    private Integer integral;

}
