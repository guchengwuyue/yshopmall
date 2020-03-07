package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.repository.YxStoreVisitRepository;
import co.yixiang.modules.activity.service.YxStoreVisitService;
import co.yixiang.modules.activity.service.dto.YxStoreVisitDTO;
import co.yixiang.modules.activity.service.dto.YxStoreVisitQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreVisitMapper;
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
* @date 2019-11-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreVisitServiceImpl implements YxStoreVisitService {

    private final YxStoreVisitRepository yxStoreVisitRepository;

    private final YxStoreVisitMapper yxStoreVisitMapper;

    public YxStoreVisitServiceImpl(YxStoreVisitRepository yxStoreVisitRepository, YxStoreVisitMapper yxStoreVisitMapper) {
        this.yxStoreVisitRepository = yxStoreVisitRepository;
        this.yxStoreVisitMapper = yxStoreVisitMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreVisitQueryCriteria criteria, Pageable pageable){
        Page<YxStoreVisit> page = yxStoreVisitRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreVisitMapper::toDto));
    }

    @Override
    public List<YxStoreVisitDTO> queryAll(YxStoreVisitQueryCriteria criteria){
        return yxStoreVisitMapper.toDto(yxStoreVisitRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreVisitDTO findById(Integer id) {
        Optional<YxStoreVisit> yxStoreVisit = yxStoreVisitRepository.findById(id);
        ValidationUtil.isNull(yxStoreVisit,"YxStoreVisit","id",id);
        return yxStoreVisitMapper.toDto(yxStoreVisit.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreVisitDTO create(YxStoreVisit resources) {
        return yxStoreVisitMapper.toDto(yxStoreVisitRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreVisit resources) {
        Optional<YxStoreVisit> optionalYxStoreVisit = yxStoreVisitRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreVisit,"YxStoreVisit","id",resources.getId());
        YxStoreVisit yxStoreVisit = optionalYxStoreVisit.get();
        yxStoreVisit.copy(resources);
        yxStoreVisitRepository.save(yxStoreVisit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreVisitRepository.deleteById(id);
    }
}