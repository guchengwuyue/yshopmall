package co.yixiang.mp.service.impl;

import co.yixiang.mp.domain.YxWechatTemplate;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.mp.repository.YxWechatTemplateRepository;
import co.yixiang.mp.service.YxWechatTemplateService;
import co.yixiang.mp.service.dto.YxWechatTemplateDTO;
import co.yixiang.mp.service.dto.YxWechatTemplateQueryCriteria;
import co.yixiang.mp.service.mapper.YxWechatTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import java.util.List;
import java.util.Map;

/**
* @author xuwenbo
* @date 2019-12-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatTemplateServiceImpl implements YxWechatTemplateService {

    private final YxWechatTemplateRepository yxWechatTemplateRepository;

    private final YxWechatTemplateMapper yxWechatTemplateMapper;

    public YxWechatTemplateServiceImpl(YxWechatTemplateRepository yxWechatTemplateRepository, YxWechatTemplateMapper yxWechatTemplateMapper) {
        this.yxWechatTemplateRepository = yxWechatTemplateRepository;
        this.yxWechatTemplateMapper = yxWechatTemplateMapper;
    }

    @Override
    public YxWechatTemplate findByTempkey(String key) {
        return yxWechatTemplateRepository.findByTempkey(key);
    }

    @Override
    public Map<String,Object> queryAll(YxWechatTemplateQueryCriteria criteria, Pageable pageable){
        Page<YxWechatTemplate> page = yxWechatTemplateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxWechatTemplateMapper::toDto));
    }

    @Override
    public List<YxWechatTemplateDTO> queryAll(YxWechatTemplateQueryCriteria criteria){
        return yxWechatTemplateMapper.toDto(yxWechatTemplateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxWechatTemplateDTO findById(Integer id) {
        Optional<YxWechatTemplate> yxWechatTemplate = yxWechatTemplateRepository.findById(id);
        ValidationUtil.isNull(yxWechatTemplate,"YxWechatTemplate","id",id);
        return yxWechatTemplateMapper.toDto(yxWechatTemplate.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxWechatTemplateDTO create(YxWechatTemplate resources) {
        return yxWechatTemplateMapper.toDto(yxWechatTemplateRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxWechatTemplate resources) {
        Optional<YxWechatTemplate> optionalYxWechatTemplate = yxWechatTemplateRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxWechatTemplate,"YxWechatTemplate","id",resources.getId());
        YxWechatTemplate yxWechatTemplate = optionalYxWechatTemplate.get();
        yxWechatTemplate.copy(resources);
        yxWechatTemplateRepository.save(yxWechatTemplate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxWechatTemplateRepository.deleteById(id);
    }
}