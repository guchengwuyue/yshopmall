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
import co.yixiang.modules.tools.domain.QiniuContent;
import co.yixiang.modules.tools.service.QiniuContentService;
import co.yixiang.modules.tools.service.dto.QiniuContentDto;
import co.yixiang.modules.tools.service.dto.QiniuQueryCriteria;
import co.yixiang.modules.tools.service.mapper.QiniuContentMapper;
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
//@CacheConfig(cacheNames = "qiniuContent")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QiniuContentServiceImpl extends BaseServiceImpl<QiniuContentMapper, QiniuContent> implements QiniuContentService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(QiniuQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<QiniuContent> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), QiniuContentDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<QiniuContent> queryAll(QiniuQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(QiniuContent.class, criteria));
    }


    @Override
    public void download(List<QiniuContentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QiniuContentDto qiniuContent : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("Bucket 识别符", qiniuContent.getBucket());
            map.put("文件名称", qiniuContent.getKey());
            map.put("文件大小", qiniuContent.getSize());
            map.put("文件类型：私有或公开", qiniuContent.getType());
            map.put("上传或同步的时间", qiniuContent.getUpdateTime());
            map.put("文件url", qiniuContent.getUrl());
            map.put(" suffix", qiniuContent.getSuffix());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
