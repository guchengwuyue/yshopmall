package co.yixiang.modules.order.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductAttrDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/3
 **/
@Data
public class ProductAttrDto implements Serializable {

    @ApiModelProperty(value = "产品属性ID")
    private Long productId;

    @ApiModelProperty(value = "产品属性sku")
    private String sku;

    @ApiModelProperty(value = "产品属性价格")
    private Double price;

    @ApiModelProperty(value = "产品属性图片")
    private String image;
}
