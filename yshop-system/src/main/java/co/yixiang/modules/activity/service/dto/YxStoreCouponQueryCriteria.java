package co.yixiang.modules.activity.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-11-09
*/
@Data
public class YxStoreCouponQueryCriteria{
    @Query
    private Integer isDel;
}