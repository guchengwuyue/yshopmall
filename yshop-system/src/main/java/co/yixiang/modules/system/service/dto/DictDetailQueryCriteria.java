/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package co.yixiang.modules.system.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
public class DictDetailQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    private String dictName;
}
