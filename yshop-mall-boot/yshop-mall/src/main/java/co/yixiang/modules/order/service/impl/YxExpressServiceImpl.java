/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.order.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.order.domain.YxExpress;
import co.yixiang.modules.order.service.YxExpressService;
import co.yixiang.modules.order.service.dto.YxExpressDto;
import co.yixiang.modules.order.service.dto.YxExpressQueryCriteria;
import co.yixiang.modules.order.service.mapper.ExpressMapper;
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
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
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
