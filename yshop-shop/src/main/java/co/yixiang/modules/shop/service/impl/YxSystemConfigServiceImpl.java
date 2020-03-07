package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.repository.YxSystemConfigRepository;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.dto.YxSystemConfigDTO;
import co.yixiang.modules.shop.service.dto.YxSystemConfigQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxSystemConfigMapper;
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
* @date 2019-10-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemConfigServiceImpl implements YxSystemConfigService {

    private final YxSystemConfigRepository yxSystemConfigRepository;

    private final YxSystemConfigMapper yxSystemConfigMapper;

    public YxSystemConfigServiceImpl(YxSystemConfigRepository yxSystemConfigRepository, YxSystemConfigMapper yxSystemConfigMapper) {
        this.yxSystemConfigRepository = yxSystemConfigRepository;
        this.yxSystemConfigMapper = yxSystemConfigMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxSystemConfigQueryCriteria criteria, Pageable pageable){
        Page<YxSystemConfig> page = yxSystemConfigRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxSystemConfigMapper::toDto));
    }

    @Override
    public List<YxSystemConfigDTO> queryAll(YxSystemConfigQueryCriteria criteria){
        return yxSystemConfigMapper.toDto(yxSystemConfigRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxSystemConfigDTO findById(Integer id) {
        Optional<YxSystemConfig> yxSystemConfig = yxSystemConfigRepository.findById(id);
        ValidationUtil.isNull(yxSystemConfig,"YxSystemConfig","id",id);
        return yxSystemConfigMapper.toDto(yxSystemConfig.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxSystemConfigDTO create(YxSystemConfig resources) {
        return yxSystemConfigMapper.toDto(yxSystemConfigRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxSystemConfig resources) {
        Optional<YxSystemConfig> optionalYxSystemConfig = yxSystemConfigRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxSystemConfig,"YxSystemConfig","id",resources.getId());
        YxSystemConfig yxSystemConfig = optionalYxSystemConfig.get();
        yxSystemConfig.copy(resources);
        yxSystemConfigRepository.save(yxSystemConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxSystemConfigRepository.deleteById(id);
    }

    @Override
    public YxSystemConfig findByKey(String str) {
        return yxSystemConfigRepository.findByMenuName(str);
    }
}