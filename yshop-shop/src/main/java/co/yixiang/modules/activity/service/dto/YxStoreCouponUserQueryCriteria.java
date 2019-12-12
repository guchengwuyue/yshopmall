package co.yixiang.modules.activity.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-11-10
*/
@Data
public class YxStoreCouponUserQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String couponTitle;
}