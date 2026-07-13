package co.yixiang.modules.sales.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.sales.domain.StoreAfterSalesItem;
import co.yixiang.modules.sales.service.StoreAfterSalesItemService;
import co.yixiang.modules.sales.service.mapper.StoreAfterSalesItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : gzlv 2021/6/27 15:55
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StoreAfterSalesItemServiceImpl extends BaseServiceImpl<StoreAfterSalesItemMapper, StoreAfterSalesItem> implements StoreAfterSalesItemService {
}
