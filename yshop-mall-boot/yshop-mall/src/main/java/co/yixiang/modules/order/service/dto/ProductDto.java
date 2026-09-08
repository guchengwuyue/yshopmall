package co.yixiang.modules.order.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/3
 **/
@Data
public class ProductDto implements Serializable {

    @Schema(description = "产品图片")
    private String image;

    @Schema(description = "产品价格")
    private Double price;

    @Schema(description = "产品名称")
    private String storeName;

    @Schema(description = "产品属性信息")
    private ProductAttrDto attrInfo;

}
