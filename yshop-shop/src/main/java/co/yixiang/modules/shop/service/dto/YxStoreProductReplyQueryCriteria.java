package co.yixiang.modules.shop.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2019-11-03
*/
@Data
public class YxStoreProductReplyQueryCriteria{
    @Query
    private Integer isDel;
}