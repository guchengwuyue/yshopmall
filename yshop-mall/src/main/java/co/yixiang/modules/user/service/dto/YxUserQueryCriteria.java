/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-12
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

    @Query
    private String userType;

    private Long uid;

    private Integer grade;
}
