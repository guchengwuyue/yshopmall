/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxExpress;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxExpressService;
import co.yixiang.modules.shop.service.dto.YxExpressDto;
import co.yixiang.modules.shop.service.dto.YxExpressQueryCriteria;
import co.yixiang.modules.shop.service.mapper.ExpressMapper;
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
//@CacheConfig(cacheNames = "yxExpress")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxExpressServiceImpl extends BaseServiceImpl<ExpressMapper, YxExpress> implements YxExpressService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxExpressQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxExpress> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxExpressDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxExpress> queryAll(YxExpressQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxExpress.class, criteria));
    }


    @Override
    public void download(List<YxExpressDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxExpressDto yxExpress : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("快递公司简称", yxExpress.getCode());
            map.put("快递公司全称", yxExpress.getName());
            map.put("排序", yxExpress.getSort());
            map.put("是否显示", yxExpress.getIsShow());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
