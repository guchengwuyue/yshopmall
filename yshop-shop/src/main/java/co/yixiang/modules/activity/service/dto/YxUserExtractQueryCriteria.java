package co.yixiang.modules.activity.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2019-11-14
*/
@Data
public class YxUserExtractQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String realName;
}