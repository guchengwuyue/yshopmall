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
import co.yixiang.modules.template.domain.YxSystemCity;
import co.yixiang.modules.template.service.YxSystemCityService;
import co.yixiang.modules.template.service.dto.YxSystemCityDto;
import co.yixiang.modules.template.service.dto.YxSystemCityQueryCriteria;
import co.yixiang.modules.template.service.mapper.YxSystemCityMapper;
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


/**
* @author hupeng
* @date 2020-06-29
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemCityServiceImpl extends BaseServiceImpl<YxSystemCityMapper, YxSystemCity> implements YxSystemCityService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemCityQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemCity> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxSystemCityDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemCity> queryAll(YxSystemCityQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemCity.class, criteria));
    }


    @Override
    public void download(List<YxSystemCityDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemCityDto yxSystemCity : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("城市id", yxSystemCity.getCityId());
            map.put("省市级别", yxSystemCity.getLevel());
            map.put("父级id", yxSystemCity.getParentId());
            map.put("区号", yxSystemCity.getAreaCode());
            map.put("名称", yxSystemCity.getName());
            map.put("合并名称", yxSystemCity.getMergerName());
            map.put("经度", yxSystemCity.getLng());
            map.put("纬度", yxSystemCity.getLat());
            map.put("是否展示", yxSystemCity.getIsShow());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
