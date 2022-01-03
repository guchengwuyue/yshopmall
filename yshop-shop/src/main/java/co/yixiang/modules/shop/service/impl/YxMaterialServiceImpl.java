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
import co.yixiang.modules.shop.domain.YxMaterial;
import co.yixiang.modules.shop.service.YxMaterialService;
import co.yixiang.modules.shop.service.dto.YxMaterialDto;
import co.yixiang.modules.shop.service.dto.YxMaterialQueryCriteria;
import co.yixiang.modules.shop.service.mapper.MaterialMapper;
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
//@CacheConfig(cacheNames = "yxMaterial")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxMaterialServiceImpl extends BaseServiceImpl<MaterialMapper, YxMaterial> implements YxMaterialService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxMaterialQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxMaterial> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxMaterialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxMaterial> queryAll(YxMaterialQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxMaterial.class, criteria));
    }


    @Override
    public void download(List<YxMaterialDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxMaterialDto yxMaterial : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("所属租户", yxMaterial.getUserId());
            map.put("创建者ID", yxMaterial.getCreateId());
            map.put("类型1、图片；2、视频", yxMaterial.getType());
            map.put("分组ID", yxMaterial.getGroupId());
            map.put("素材名", yxMaterial.getName());
            map.put("素材链接", yxMaterial.getUrl());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
