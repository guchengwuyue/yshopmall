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
import co.yixiang.modules.template.domain.YxShippingTemplatesFree;
import co.yixiang.modules.template.service.YxShippingTemplatesFreeService;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesFreeDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesFreeQueryCriteria;
import co.yixiang.modules.template.service.mapper.YxShippingTemplatesFreeMapper;
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
public class YxShippingTemplatesFreeServiceImpl extends BaseServiceImpl<YxShippingTemplatesFreeMapper, YxShippingTemplatesFree> implements YxShippingTemplatesFreeService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxShippingTemplatesFreeQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxShippingTemplatesFree> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxShippingTemplatesFreeDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxShippingTemplatesFree> queryAll(YxShippingTemplatesFreeQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxShippingTemplatesFree.class, criteria));
    }


    @Override
    public void download(List<YxShippingTemplatesFreeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxShippingTemplatesFreeDto yxShippingTemplatesFree : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("省ID", yxShippingTemplatesFree.getProvinceId());
            map.put("模板ID", yxShippingTemplatesFree.getTempId());
            map.put("城市ID", yxShippingTemplatesFree.getCityId());
            map.put("包邮件数", yxShippingTemplatesFree.getNumber());
            map.put("包邮金额", yxShippingTemplatesFree.getPrice());
            map.put("计费方式", yxShippingTemplatesFree.getType());
            map.put("分组唯一值", yxShippingTemplatesFree.getUniqid());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
