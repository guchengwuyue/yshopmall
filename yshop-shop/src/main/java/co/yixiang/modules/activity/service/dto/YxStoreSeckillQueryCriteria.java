package co.yixiang.modules.activity.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import co.yixiang.annotation.Query;

/**
* @author xuwenbo
* @date 2019-12-14
*/
@Data
public class YxStoreSeckillQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String title;
}