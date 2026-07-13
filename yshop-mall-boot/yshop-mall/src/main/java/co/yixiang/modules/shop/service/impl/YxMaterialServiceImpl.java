/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
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
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public List<YxMaterial> queryAll(YxMaterialQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxMaterial.class, criteria));
    }



}
