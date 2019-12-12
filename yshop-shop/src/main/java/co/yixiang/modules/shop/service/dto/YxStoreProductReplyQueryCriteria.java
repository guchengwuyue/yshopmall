package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-11-03
*/
@Data
public class YxStoreProductReplyQueryCriteria{
    @Query
    private Integer isDel;
}