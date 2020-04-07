package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.repository.YxStoreCategoryRepository;
import co.yixiang.modules.shop.repository.YxStoreProductRepository;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxStoreCategoryMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author hupeng
* @date 2019-10-03
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCategoryServiceImpl implements YxStoreCategoryService {

    private final YxStoreCategoryRepository yxStoreCategoryRepository;
    private final YxStoreProductRepository yxStoreProductRepository;

    private final YxStoreCategoryMapper yxStoreCategoryMapper;

    public YxStoreCategoryServiceImpl(YxStoreCategoryRepository yxStoreCategoryRepository,
                                      YxStoreProductRepository yxStoreProductRepository,
                                      YxStoreCategoryMapper yxStoreCategoryMapper) {
        this.yxStoreCategoryRepository = yxStoreCategoryRepository;
        this.yxStoreProductRepository = yxStoreProductRepository;
        this.yxStoreCategoryMapper = yxStoreCategoryMapper;
    }

    @Override
    public void download(List<YxStoreCategoryDTO> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCategoryDTO storeCategoryDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("分类名称", storeCategoryDTO.getCateName());
            map.put("分类状态", storeCategoryDTO.getIsShow() == 1 ? "启用" : "停用");
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

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
    public YxStoreCategoryDTO findByName(String name) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCategoryDTO create(YxStoreCategory resources) {
        if(ObjectUtil.isNull(resources.getPid())) resources.setPid(0);
        if(ObjectUtil.isNull(resources.getSort())) resources.setSort(1);
        return yxStoreCategoryMapper.toDto(yxStoreCategoryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCategory resources) {
        if(resources.getId().equals(resources.getPid())){
            throw new BadRequestException("自己不能选择自己哦");
        }
        Optional<YxStoreCategory> optionalYxStoreCategory = yxStoreCategoryRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCategory,"YxStoreCategory","id",resources.getId());
        YxStoreCategory yxStoreCategory = optionalYxStoreCategory.get();
        yxStoreCategory.copy(resources);
        yxStoreCategoryRepository.save(yxStoreCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        YxStoreCategory storeCategory = yxStoreCategoryRepository.findByPid(id);

        if(storeCategory != null) throw new BadRequestException("请先删除子类");
        YxStoreCategory category = new YxStoreCategory();
        category.setId(id);
        List<YxStoreProduct> storeProduct = yxStoreProductRepository.findByStoreCategoryAndIsDel(category,0);

        if(!storeProduct.isEmpty()) throw new BadRequestException("此分类下有商品,不能删除");

        yxStoreCategoryRepository.delCategory(id);
    }


    @Override
    public Object buildTree(List<YxStoreCategoryDTO> categoryDTOS) {
        Set<YxStoreCategoryDTO> trees = new LinkedHashSet<>();
        Set<YxStoreCategoryDTO> cates= new LinkedHashSet<>();
        List<String> deptNames = categoryDTOS.stream().map(YxStoreCategoryDTO::getCateName)
                .collect(Collectors.toList());

        YxStoreCategoryDTO categoryDTO = new YxStoreCategoryDTO();
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