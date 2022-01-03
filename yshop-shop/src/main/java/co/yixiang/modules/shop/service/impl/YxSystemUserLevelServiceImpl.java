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
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.YxSystemUserLevelService;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelDto;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelQueryCriteria;
import co.yixiang.modules.shop.service.mapper.SystemUserLevelMapper;
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
//@CacheConfig(cacheNames = "yxSystemUserLevel")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemUserLevelServiceImpl extends BaseServiceImpl<SystemUserLevelMapper, YxSystemUserLevel> implements YxSystemUserLevelService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemUserLevelQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemUserLevel> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxSystemUserLevelDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemUserLevel> queryAll(YxSystemUserLevelQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemUserLevel.class, criteria));
    }


    @Override
    public void download(List<YxSystemUserLevelDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemUserLevelDto yxSystemUserLevel : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("商户id", yxSystemUserLevel.getMerId());
            map.put("会员名称", yxSystemUserLevel.getName());
            map.put("购买金额", yxSystemUserLevel.getMoney());
            map.put("有效时间", yxSystemUserLevel.getValidDate());
            map.put("是否为永久会员", yxSystemUserLevel.getIsForever());
            map.put("是否购买,1=购买,0=不购买", yxSystemUserLevel.getIsPay());
            map.put("是否显示 1=显示,0=隐藏", yxSystemUserLevel.getIsShow());
            map.put("会员等级", yxSystemUserLevel.getGrade());
            map.put("享受折扣", yxSystemUserLevel.getDiscount());
            map.put("会员卡背景", yxSystemUserLevel.getImage());
            map.put("会员图标", yxSystemUserLevel.getIcon());
            map.put("说明", yxSystemUserLevel.getExplain());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
