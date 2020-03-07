package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxMaterialGroup;
import co.yixiang.modules.shop.repository.YxMaterialGroupRepository;
import co.yixiang.modules.shop.service.YxMaterialGroupService;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupDto;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxMaterialGroupMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;


/**
* @author hupeng
* @date 2020-01-09
*/
@Service
//@CacheConfig(cacheNames = "yxMaterialGroup")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxMaterialGroupServiceImpl implements YxMaterialGroupService {

    private final YxMaterialGroupRepository yxMaterialGroupRepository;

    private final YxMaterialGroupMapper yxMaterialGroupMapper;

    public YxMaterialGroupServiceImpl(YxMaterialGroupRepository yxMaterialGroupRepository, YxMaterialGroupMapper yxMaterialGroupMapper) {
        this.yxMaterialGroupRepository = yxMaterialGroupRepository;
        this.yxMaterialGroupMapper = yxMaterialGroupMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(YxMaterialGroupQueryCriteria criteria, Pageable pageable){
        Page<YxMaterialGroup> page = yxMaterialGroupRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxMaterialGroupMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<YxMaterialGroupDto> queryAll(YxMaterialGroupQueryCriteria criteria){
        return yxMaterialGroupMapper.toDto(yxMaterialGroupRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public YxMaterialGroupDto findById(String id) {
        YxMaterialGroup yxMaterialGroup = yxMaterialGroupRepository.findById(id).orElseGet(YxMaterialGroup::new);
        ValidationUtil.isNull(yxMaterialGroup.getId(),"YxMaterialGroup","id",id);
        return yxMaterialGroupMapper.toDto(yxMaterialGroup);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public YxMaterialGroupDto create(YxMaterialGroup resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return yxMaterialGroupMapper.toDto(yxMaterialGroupRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(YxMaterialGroup resources) {
        YxMaterialGroup yxMaterialGroup = yxMaterialGroupRepository.findById(resources.getId()).orElseGet(YxMaterialGroup::new);
        ValidationUtil.isNull( yxMaterialGroup.getId(),"YxMaterialGroup","id",resources.getId());
        yxMaterialGroup.copy(resources);
        yxMaterialGroupRepository.save(yxMaterialGroup);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            yxMaterialGroupRepository.deleteById(id);
        }
    }

    @Override
    public void deleteById(String id) {
        yxMaterialGroupRepository.deleteById(id);
    }
}