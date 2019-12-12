package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-10-03
*/
@Data
public class YxStoreCategoryQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String cateName;
}