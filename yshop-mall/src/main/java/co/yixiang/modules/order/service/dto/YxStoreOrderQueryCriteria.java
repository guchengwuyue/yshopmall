/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.order.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxStoreOrderQueryCriteria{

    // 模糊
    @Query(type = Query.Type.UNIX_TIMESTAMP)
    private List<String> createTime;


    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String orderId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String realName;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String userPhone;

    @Query
    private Integer paid;

    @Query
    private Integer status;

    @Query
    private Integer refundStatus;

    @Query
    private Integer isDel;

    @Query
    private Integer combinationId;

    @Query
    private Integer seckillId;

    @Query
    private Integer bargainId;

    @Query(propName="combinationId",type = Query.Type.NOT_EQUAL)
    private Integer newCombinationId;

    @Query(propName="seckillId",type = Query.Type.NOT_EQUAL)
    private Integer newSeckillId;

    @Query(propName="bargainId",type = Query.Type.NOT_EQUAL)
    private Integer newBargainId;

    @Query
    private Integer shippingType;

    @Query(type = Query.Type.GREATER_THAN_NQ)
    private BigDecimal payIntegral;

    @Query
    private Integer storeId;
}
