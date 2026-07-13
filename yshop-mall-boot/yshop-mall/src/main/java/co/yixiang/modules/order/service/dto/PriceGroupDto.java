package co.yixiang.modules.order.service.dto;

import co.yixiang.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PriceGroup
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/27
 **/
@Data
public class PriceGroupDto {

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal costPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal storeFreePostage;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal storePostage;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal vipPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal payIntegral;
}
