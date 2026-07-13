/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.product.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxStoreProductQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String storeName;

    // 精确
    @Query
    private Integer isDel;

    @Query
    private Integer isShow;

    @Query
    private Integer cateId;

    @Query
    private Integer isIntegral;
}
