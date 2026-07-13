/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxSystemStoreStaffQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String staffName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String nickname;
}
