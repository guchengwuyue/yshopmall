package co.yixiang.modules.activity.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2019-11-18
*/
@Data
public class YxStorePinkQueryCriteria{
    @Query
    private Integer kId;
}