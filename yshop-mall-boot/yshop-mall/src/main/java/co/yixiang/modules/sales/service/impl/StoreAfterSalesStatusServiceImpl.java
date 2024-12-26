package co.yixiang.modules.sales.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.sales.domain.StoreAfterSalesStatus;
import co.yixiang.modules.sales.service.StoreAfterSalesStatusService;
import co.yixiang.modules.sales.service.mapper.StoreAfterSalesStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gzlv 2021/6/27 15:56
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StoreAfterSalesStatusServiceImpl extends BaseServiceImpl<StoreAfterSalesStatusMapper, StoreAfterSalesStatus> implements StoreAfterSalesStatusService {
}
