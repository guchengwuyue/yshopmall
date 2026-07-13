/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.template.service.impl;


import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.template.domain.YxShippingTemplatesRegion;
import co.yixiang.modules.template.service.YxShippingTemplatesRegionService;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesRegionDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesRegionQueryCriteria;
import co.yixiang.modules.template.service.mapper.YxShippingTemplatesRegionMapper;
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
* @date 2020-06-29
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxShippingTemplatesRegion")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxShippingTemplatesRegionServiceImpl extends BaseServiceImpl<YxShippingTemplatesRegionMapper, YxShippingTemplatesRegion> implements YxShippingTemplatesRegionService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxShippingTemplatesRegionQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxShippingTemplatesRegion> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxShippingTemplatesRegionDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxShippingTemplatesRegion> queryAll(YxShippingTemplatesRegionQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxShippingTemplatesRegion.class, criteria));
    }


    @Override
    public void download(List<YxShippingTemplatesRegionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxShippingTemplatesRegionDto yxShippingTemplatesRegion : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("省ID", yxShippingTemplatesRegion.getProvinceId());
            map.put("模板ID", yxShippingTemplatesRegion.getTempId());
            map.put("城市ID", yxShippingTemplatesRegion.getCityId());
            map.put("首件", yxShippingTemplatesRegion.getFirst());
            map.put("首件运费", yxShippingTemplatesRegion.getFirstPrice());
            map.put("续件", yxShippingTemplatesRegion.getContinues());
            map.put("续件运费", yxShippingTemplatesRegion.getContinuePrice());
            map.put("计费方式", yxShippingTemplatesRegion.getType());
            map.put("分组唯一值", yxShippingTemplatesRegion.getUniqid());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
