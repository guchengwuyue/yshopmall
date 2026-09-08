package co.yixiang.modules.order.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductAttrDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/3
 **/
@Data
public class ProductAttrDto implements Serializable {

    @Schema(description = "产品属性ID")
    private Long productId;

    @Schema(description = "产品属性sku")
    private String sku;

    @Schema(description = "产品属性价格")
    private Double price;

    @Schema(description = "产品属性图片")
    private String image;
}
