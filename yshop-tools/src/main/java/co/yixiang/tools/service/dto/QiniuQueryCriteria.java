/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.tools.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author hupeng
 * @date 2019-6-4 09:54:37
 */
@Data
public class QiniuQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
