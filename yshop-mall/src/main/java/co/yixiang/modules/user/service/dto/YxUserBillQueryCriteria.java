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
public class YxUserBillQueryCriteria{
    @Query(type = Query.Type.EQUAL)
    private String nickname = "";
    @Query(type = Query.Type.EQUAL)
    private String category = "";
    @Query(type = Query.Type.EQUAL)
    private String type  = "";
    @Query(type = Query.Type.EQUAL)
    private Integer pm;
    @Query(type = Query.Type.EQUAL)
    private String title = "";
    private String startTime;

    private String endTime;
}
