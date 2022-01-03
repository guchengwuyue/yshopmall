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
import co.yixiang.modules.shop.domain.YxMaterialGroup;
import co.yixiang.modules.shop.service.YxMaterialGroupService;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupDto;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupQueryCriteria;
import co.yixiang.modules.shop.service.mapper.MaterialGroupMapper;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
//@CacheConfig(cacheNames = "yxMaterialGroup")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxMaterialGroupServiceImpl extends BaseServiceImpl<MaterialGroupMapper, YxMaterialGroup> implements YxMaterialGroupService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxMaterialGroupQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxMaterialGroup> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxMaterialGroupDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxMaterialGroup> queryAll(YxMaterialGroupQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxMaterialGroup.class, criteria));
    }


    @Override
    public void download(List<YxMaterialGroupDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxMaterialGroupDto yxMaterialGroup : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("创建者ID", yxMaterialGroup.getCreateId());
            map.put("分组名", yxMaterialGroup.getName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
