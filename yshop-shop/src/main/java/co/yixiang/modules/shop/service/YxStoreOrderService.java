/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.dto.OrderCountDto;
import co.yixiang.modules.shop.service.dto.OrderTimeDataDto;
import co.yixiang.modules.shop.service.dto.YxStoreOrderDto;
import co.yixiang.modules.shop.service.dto.YxStoreOrderQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hupeng
 * @date 2020-05-12
 */
public interface YxStoreOrderService extends BaseService<YxStoreOrder> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreOrderDto>
     */
    List<YxStoreOrder> queryAll(YxStoreOrderQueryCriteria criteria);


    YxStoreOrderDto create(YxStoreOrder resources);

    void update(YxStoreOrder resources);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreOrderDto> all, HttpServletResponse response) throws IOException;


    Map<String, Object> queryAll(List<String> ids);


    String orderType(Long id, Long pinkId, Long combinationId, Long seckillId,
                     Long bargainId, int shippingType);

    void refund(YxStoreOrder resources);

    OrderCountDto getOrderCount();

    OrderTimeDataDto getOrderTimeData();

    Map<String, Object> chartCount();
}
