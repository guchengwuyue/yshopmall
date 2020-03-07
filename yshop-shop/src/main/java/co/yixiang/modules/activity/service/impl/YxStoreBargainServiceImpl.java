package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.repository.YxStoreBargainRepository;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.dto.YxStoreBargainDTO;
import co.yixiang.modules.activity.service.dto.YxStoreBargainQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreBargainMapper;
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
* @author xuwenbo
* @date 2019-12-22
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreBargainServiceImpl implements YxStoreBargainService {

    private final YxStoreBargainRepository yxStoreBargainRepository;

    private final YxStoreBargainMapper yxStoreBargainMapper;

    public YxStoreBargainServiceImpl(YxStoreBargainRepository yxStoreBargainRepository, YxStoreBargainMapper yxStoreBargainMapper) {
        this.yxStoreBargainRepository = yxStoreBargainRepository;
        this.yxStoreBargainMapper = yxStoreBargainMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreBargainQueryCriteria criteria, Pageable pageable){
        Page<YxStoreBargain> page = yxStoreBargainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreBargainMapper::toDto));
    }

    @Override
    public List<YxStoreBargainDTO> queryAll(YxStoreBargainQueryCriteria criteria){
        return yxStoreBargainMapper.toDto(yxStoreBargainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreBargainDTO findById(Integer id) {
        Optional<YxStoreBargain> yxStoreBargain = yxStoreBargainRepository.findById(id);
        ValidationUtil.isNull(yxStoreBargain,"YxStoreBargain","id",id);
        return yxStoreBargainMapper.toDto(yxStoreBargain.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreBargainDTO create(YxStoreBargain resources) {
        return yxStoreBargainMapper.toDto(yxStoreBargainRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreBargain resources) {
        Optional<YxStoreBargain> optionalYxStoreBargain = yxStoreBargainRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreBargain,"YxStoreBargain","id",resources.getId());
        YxStoreBargain yxStoreBargain = optionalYxStoreBargain.get();
        yxStoreBargain.copy(resources);
        yxStoreBargainRepository.save(yxStoreBargain);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreBargainRepository.deleteById(id);
    }
}