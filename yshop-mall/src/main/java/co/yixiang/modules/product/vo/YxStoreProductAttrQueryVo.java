package co.yixiang.modules.product.vo;

import co.yixiang.modules.product.service.dto.AttrValueDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品属性表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-23
 */
@Data
@ApiModel(value = "YxStoreProductAttrQueryVo对象", description = "商品属性表查询参数")
public class YxStoreProductAttrQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "属性名")
    private String attrName;

    @ApiModelProperty(value = "属性值")
    private String attrValues;

    @ApiModelProperty(value = "属性值集合")
    private List<AttrValueDto> attrValue;

    @ApiModelProperty(value = "属性")
    private List<String> attrValueArr;

}
