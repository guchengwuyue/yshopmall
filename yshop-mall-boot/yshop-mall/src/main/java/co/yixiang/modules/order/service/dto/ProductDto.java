package co.yixiang.modules.order.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/3
 **/
@Data
public class ProductDto implements Serializable {

    @ApiModelProperty(value = "产品图片")
    private String image;

    @ApiModelProperty(value = "产品价格")
    private Double price;

    @ApiModelProperty(value = "产品名称")
    private String storeName;

    @ApiModelProperty(value = "产品属性信息")
    private ProductAttrDto attrInfo;

}
