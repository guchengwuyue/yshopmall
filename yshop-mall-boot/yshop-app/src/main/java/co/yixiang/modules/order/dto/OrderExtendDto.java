package co.yixiang.modules.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName OrderExtendDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderExtendDto implements Serializable {

    @ApiModelProperty(value = "唯一的key")
    private String key;

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "微信相关配置")
    private Map<String,String> jsConfig;
}
