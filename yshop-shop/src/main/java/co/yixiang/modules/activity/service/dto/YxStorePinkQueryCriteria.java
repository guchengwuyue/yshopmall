package co.yixiang.modules.activity.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-11-18
*/
@Data
public class YxStorePinkQueryCriteria{
    @Query
    private Integer kId;
}