/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDto;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.modules.shop.service.mapper.StoreCategoryMapper;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreCategory")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCategoryServiceImpl extends BaseServiceImpl<StoreCategoryMapper, YxStoreCategory> implements YxStoreCategoryService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCategoryQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCategoryDto> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCategoryDto> queryAll(YxStoreCategoryQueryCriteria criteria) {
        return generator.convert(baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCategory.class, criteria)), YxStoreCategoryDto.class);
    }


    @Override
    public void download(List<YxStoreCategoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCategoryDto yxStoreCategory : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("父id", yxStoreCategory.getPid());
            map.put("分类名称", yxStoreCategory.getCateName());
            map.put("排序", yxStoreCategory.getSort());
            map.put("图标", yxStoreCategory.getPic());
            map.put("是否推荐", yxStoreCategory.getIsShow());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Object buildTree(List<YxStoreCategoryDto> categoryDTOS) {
        Set<YxStoreCategoryDto> trees = new LinkedHashSet<>();
        Set<YxStoreCategoryDto> cates = new LinkedHashSet<>();
        List<String> deptNames = categoryDTOS.stream().map(YxStoreCategoryDto::getCateName)
                .collect(Collectors.toList());

        YxStoreCategoryDto categoryDTO = new YxStoreCategoryDto();
        Boolean isChild;
        List<YxStoreCategory> categories = this.list();
        for (YxStoreCategoryDto deptDTO : categoryDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (YxStoreCategoryDto it : categoryDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<YxStoreCategoryDto>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if (isChild) {
                cates.add(deptDTO);
            }
            for (YxStoreCategory category : categories) {
                if (category.getId().equals(deptDTO.getPid()) && !deptNames.contains(category.getCateName())) {
                    cates.add(deptDTO);
                }
            }
        }


        if (CollectionUtils.isEmpty(trees)) {
            trees = cates;
        }


        Integer totalElements = categoryDTOS != null ? categoryDTOS.size() : 0;

        Map map = new HashMap();
        map.put("totalElements", totalElements);
        map.put("content", CollectionUtils.isEmpty(trees) ? categoryDTOS : trees);
        return map;
    }


    /**
     * 检测分类是否操过二级
     * @param pid 父级id
     * @return boolean
     */
    @Override
    public boolean checkCategory(int pid) {
        if (pid == 0) {
            return true;
        }
        YxStoreCategory yxStoreCategory = this.getOne(Wrappers.<YxStoreCategory>lambdaQuery()
                .eq(YxStoreCategory::getId, pid));
        if (yxStoreCategory.getPid() > 0) {
            return false;
        }

        return true;
    }

    /**
     * 检测商品分类必选选择二级
     * @param id 分类id
     * @return boolean
     */
    @Override
    public boolean checkProductCategory(int id) {
        YxStoreCategory yxStoreCategory = this.getOne(Wrappers.<YxStoreCategory>lambdaQuery()
                .eq(YxStoreCategory::getId, id));

        if (yxStoreCategory.getPid() == 0) {
            return false;
        }

        return true;
    }

}
