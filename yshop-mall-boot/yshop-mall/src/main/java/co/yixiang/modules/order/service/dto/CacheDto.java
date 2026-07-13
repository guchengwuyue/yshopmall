package co.yixiang.modules.order.service.dto;

import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CacheDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/27
 **/
@Data
public class CacheDto implements Serializable {
    private List<YxStoreCartQueryVo> cartInfo;
    private PriceGroupDto priceGroup;
    private OtherDto other;
}
