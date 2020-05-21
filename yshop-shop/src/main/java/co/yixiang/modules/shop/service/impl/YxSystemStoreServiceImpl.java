/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import co.yixiang.modules.shop.service.mapper.SystemStoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxSystemStore")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemStoreServiceImpl extends BaseServiceImpl<SystemStoreMapper, YxSystemStore> implements YxSystemStoreService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemStoreQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemStore> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxSystemStoreDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemStore> queryAll(YxSystemStoreQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemStore.class, criteria));
    }


    @Override
    public void download(List<YxSystemStoreDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemStoreDto yxSystemStore : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("门店名称", yxSystemStore.getName());
            map.put("简介", yxSystemStore.getIntroduction());
            map.put("手机号码", yxSystemStore.getPhone());
            map.put("省市区", yxSystemStore.getAddress());
            map.put("详细地址", yxSystemStore.getDetailedAddress());
            map.put("门店logo", yxSystemStore.getImage());
            map.put("纬度", yxSystemStore.getLatitude());
            map.put("经度", yxSystemStore.getLongitude());
            map.put("核销有效日期", yxSystemStore.getValidTime());
            map.put("每日营业开关时间", yxSystemStore.getDayTime());
            map.put("添加时间", yxSystemStore.getAddTime());
            map.put("是否显示", yxSystemStore.getIsShow());
            map.put("是否删除", yxSystemStore.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
