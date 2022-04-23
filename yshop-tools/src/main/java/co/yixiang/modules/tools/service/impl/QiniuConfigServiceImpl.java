/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.tools.service.QiniuConfigService;
import co.yixiang.modules.tools.service.mapper.QiniuConfigMapper;
import co.yixiang.modules.tools.domain.QiniuConfig;
import co.yixiang.modules.tools.service.dto.QiniuConfigDto;
import co.yixiang.modules.tools.service.dto.QiniuQueryCriteria;
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
 * @date 2020-05-13
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "qiniuConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QiniuConfigServiceImpl extends BaseServiceImpl<QiniuConfigMapper, QiniuConfig> implements QiniuConfigService {

    private final IGenerator generator;

    private final QiniuConfigMapper qiniuConfigMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(QiniuQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<QiniuConfig> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), QiniuConfigDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<QiniuConfig> queryAll(QiniuQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(QiniuConfig.class, criteria));
    }


    @Override
    public void download(List<QiniuConfigDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QiniuConfigDto qiniuConfig : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("accessKey", qiniuConfig.getAccessKey());
            map.put("Bucket 识别符", qiniuConfig.getBucket());
            map.put("外链域名", qiniuConfig.getHost());
            map.put("secretKey", qiniuConfig.getSecretKey());
            map.put("空间类型", qiniuConfig.getType());
            map.put("机房", qiniuConfig.getZone());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void update(String type) {
        qiniuConfigMapper.updateType(type);
    }
}
