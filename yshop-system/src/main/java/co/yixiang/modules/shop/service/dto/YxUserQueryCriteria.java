package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-10-06
*/
@Data
public class YxUserQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String nickname;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String phone;

    @Query
    private Integer isPromoter;
}