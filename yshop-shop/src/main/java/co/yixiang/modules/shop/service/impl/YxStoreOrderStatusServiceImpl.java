package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.repository.YxStoreOrderStatusRepository;
import co.yixiang.modules.shop.service.YxStoreOrderStatusService;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxStoreOrderStatusMapper;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author hupeng
* @date 2019-11-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreOrderStatusServiceImpl implements YxStoreOrderStatusService {

    private final YxStoreOrderStatusRepository yxStoreOrderStatusRepository;

    private final YxStoreOrderStatusMapper yxStoreOrderStatusMapper;

    public YxStoreOrderStatusServiceImpl(YxStoreOrderStatusRepository yxStoreOrderStatusRepository, YxStoreOrderStatusMapper yxStoreOrderStatusMapper) {
        this.yxStoreOrderStatusRepository = yxStoreOrderStatusRepository;
        this.yxStoreOrderStatusMapper = yxStoreOrderStatusMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreOrderStatusQueryCriteria criteria, Pageable pageable){
        Page<YxStoreOrderStatus> page = yxStoreOrderStatusRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreOrderStatusMapper::toDto));
    }

    @Override
    public List<YxStoreOrderStatusDTO> queryAll(YxStoreOrderStatusQueryCriteria criteria){
        return yxStoreOrderStatusMapper.toDto(yxStoreOrderStatusRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreOrderStatusDTO findById(Integer id) {
        Optional<YxStoreOrderStatus> yxStoreOrderStatus = yxStoreOrderStatusRepository.findById(id);
        ValidationUtil.isNull(yxStoreOrderStatus,"YxStoreOrderStatus","id",id);
        return yxStoreOrderStatusMapper.toDto(yxStoreOrderStatus.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreOrderStatusDTO create(YxStoreOrderStatus resources) {
        return yxStoreOrderStatusMapper.toDto(yxStoreOrderStatusRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreOrderStatus resources) {
        Optional<YxStoreOrderStatus> optionalYxStoreOrderStatus = yxStoreOrderStatusRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreOrderStatus,"YxStoreOrderStatus","id",resources.getId());
        YxStoreOrderStatus yxStoreOrderStatus = optionalYxStoreOrderStatus.get();
        yxStoreOrderStatus.copy(resources);
        yxStoreOrderStatusRepository.save(yxStoreOrderStatus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreOrderStatusRepository.deleteById(id);
    }
}