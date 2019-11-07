package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.modules.shop.repository.YxStoreCategoryRepository;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxStoreCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import org.springframework.util.CollectionUtils;

/**
* @author hupeng
* @date 2019-10-03
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCategoryServiceImpl implements YxStoreCategoryService {

    @Autowired
    private YxStoreCategoryRepository yxStoreCategoryRepository;

    @Autowired
    private YxStoreCategoryMapper yxStoreCategoryMapper;

    @Override
    public Map<String,Object> queryAll(YxStoreCategoryQueryCriteria criteria, Pageable pageable){
        Page<YxStoreCategory> page = yxStoreCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreCategoryMapper::toDto));
    }

    @Override
    public List<YxStoreCategoryDTO> queryAll(YxStoreCategoryQueryCriteria criteria){
        return yxStoreCategoryMapper.toDto(yxStoreCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCategoryDTO findById(Integer id) {
        Optional<YxStoreCategory> yxStoreCategory = yxStoreCategoryRepository.findById(id);
        ValidationUtil.isNull(yxStoreCategory,"YxStoreCategory","id",id);
        return yxStoreCategoryMapper.toDto(yxStoreCategory.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCategoryDTO create(YxStoreCategory resources) {
        return yxStoreCategoryMapper.toDto(yxStoreCategoryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCategory resources) {
        Optional<YxStoreCategory> optionalYxStoreCategory = yxStoreCategoryRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCategory,"YxStoreCategory","id",resources.getId());
        YxStoreCategory yxStoreCategory = optionalYxStoreCategory.get();
        yxStoreCategory.copy(resources);
        yxStoreCategoryRepository.save(yxStoreCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCategoryRepository.deleteById(id);
    }


    @Override
    public Object buildTree(List<YxStoreCategoryDTO> categoryDTOS) {
        Set<YxStoreCategoryDTO> trees = new LinkedHashSet<>();
        Set<YxStoreCategoryDTO> cates= new LinkedHashSet<>();
        List<String> deptNames = categoryDTOS.stream().map(YxStoreCategoryDTO::getCateName)
                .collect(Collectors.toList());
        Boolean isChild;
        for (YxStoreCategoryDTO deptDTO : categoryDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (YxStoreCategoryDTO it : categoryDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<YxStoreCategoryDTO>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if(isChild)
                cates.add(deptDTO);
            else if(!deptNames.contains(yxStoreCategoryRepository.findNameById(deptDTO.getPid())))
                cates.add(deptDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = cates;
        }

        Integer totalElements = categoryDTOS!=null?categoryDTOS.size():0;

        Map map = new HashMap();
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)?categoryDTOS:trees);
        return map;
        //return null;
    }
}