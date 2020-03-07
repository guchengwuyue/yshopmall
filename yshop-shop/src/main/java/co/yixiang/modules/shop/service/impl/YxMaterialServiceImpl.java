package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxMaterial;
import co.yixiang.modules.shop.repository.YxMaterialRepository;
import co.yixiang.modules.shop.service.YxMaterialService;
import co.yixiang.modules.shop.service.dto.YxMaterialDto;
import co.yixiang.modules.shop.service.dto.YxMaterialQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxMaterialMapper;
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
//@CacheConfig(cacheNames = "yxMaterial")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxMaterialServiceImpl implements YxMaterialService {

    private final YxMaterialRepository yxMaterialRepository;

    private final YxMaterialMapper yxMaterialMapper;

    public YxMaterialServiceImpl(YxMaterialRepository yxMaterialRepository, YxMaterialMapper yxMaterialMapper) {
        this.yxMaterialRepository = yxMaterialRepository;
        this.yxMaterialMapper = yxMaterialMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(YxMaterialQueryCriteria criteria, Pageable pageable){
        Page<YxMaterial> page = yxMaterialRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxMaterialMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<YxMaterialDto> queryAll(YxMaterialQueryCriteria criteria){
        return yxMaterialMapper.toDto(yxMaterialRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public YxMaterialDto findById(String id) {
        YxMaterial yxMaterial = yxMaterialRepository.findById(id).orElseGet(YxMaterial::new);
        ValidationUtil.isNull(yxMaterial.getId(),"YxMaterial","id",id);
        return yxMaterialMapper.toDto(yxMaterial);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public YxMaterialDto create(YxMaterial resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return yxMaterialMapper.toDto(yxMaterialRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(YxMaterial resources) {
        YxMaterial yxMaterial = yxMaterialRepository.findById(resources.getId()).orElseGet(YxMaterial::new);
        ValidationUtil.isNull( yxMaterial.getId(),"YxMaterial","id",resources.getId());
        yxMaterial.copy(resources);
        yxMaterialRepository.save(yxMaterial);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            yxMaterialRepository.deleteById(id);
        }
    }

    @Override
    public void deleteById(String id) {
        yxMaterialRepository.deleteById(id);
    }
}