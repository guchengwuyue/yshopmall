/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.mp.domain.YxWechatTemplate;
import co.yixiang.modules.mp.service.YxWechatTemplateService;
import co.yixiang.modules.mp.service.dto.YxWechatTemplateDto;
import co.yixiang.modules.mp.service.dto.YxWechatTemplateQueryCriteria;
import co.yixiang.modules.mp.service.mapper.WechatTemplateMapper;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
//@CacheConfig(cacheNames = "yxWechatTemplate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatTemplateServiceImpl extends BaseServiceImpl<WechatTemplateMapper, YxWechatTemplate> implements YxWechatTemplateService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxWechatTemplateQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxWechatTemplate> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxWechatTemplateDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxWechatTemplate> queryAll(YxWechatTemplateQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxWechatTemplate.class, criteria));
    }


    @Override
    public void download(List<YxWechatTemplateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxWechatTemplateDto yxWechatTemplate : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("模板编号", yxWechatTemplate.getTempkey());
            map.put("模板名", yxWechatTemplate.getName());
            map.put("回复内容", yxWechatTemplate.getContent());
            map.put("模板ID", yxWechatTemplate.getTempid());
            map.put("添加时间", yxWechatTemplate.getAddTime());
            map.put("状态", yxWechatTemplate.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public YxWechatTemplate findByTempkey(String recharge_success_key) {
        return this.getOne(new LambdaQueryWrapper<YxWechatTemplate>().eq(YxWechatTemplate::getTempkey, recharge_success_key));
    }
}
