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
import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.mp.domain.YxWechatReply;
import co.yixiang.modules.mp.service.YxWechatReplyService;
import co.yixiang.modules.mp.service.dto.YxWechatReplyDto;
import co.yixiang.modules.mp.service.dto.YxWechatReplyQueryCriteria;
import co.yixiang.modules.mp.service.mapper.WechatReplyMapper;
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
//@CacheConfig(cacheNames = "yxWechatReply")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatReplyServiceImpl extends BaseServiceImpl<WechatReplyMapper, YxWechatReply> implements YxWechatReplyService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxWechatReplyQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxWechatReply> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxWechatReplyDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxWechatReply> queryAll(YxWechatReplyQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxWechatReply.class, criteria));
    }


    @Override
    public void download(List<YxWechatReplyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxWechatReplyDto yxWechatReply : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("关键字", yxWechatReply.getKey());
            map.put("回复类型", yxWechatReply.getType());
            map.put("回复数据", yxWechatReply.getData());
            map.put("0=不可用  1 =可用", yxWechatReply.getStatus());
            map.put("是否隐藏", yxWechatReply.getHide());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public YxWechatReply isExist(String key) {
        YxWechatReply yxWechatReply = this.getOne(new LambdaQueryWrapper<YxWechatReply>().eq(YxWechatReply::getKey, key));
        return yxWechatReply;
    }

    @Override
    public void create(YxWechatReply yxWechatReply) {
        if (this.isExist(yxWechatReply.getKey()) != null) {
            throw new EntityExistException(YxWechatReply.class, "key", yxWechatReply.getKey());
        }
        this.save(yxWechatReply);
    }

    @Override
    public void upDate(YxWechatReply resources) {
        YxWechatReply yxWechatReply = this.getById(resources.getId());
        YxWechatReply yxWechatReply1 = null;
        yxWechatReply1 = this.isExist(resources.getKey());
        if (yxWechatReply1 != null && !yxWechatReply1.getId().equals(yxWechatReply.getId())) {
            throw new EntityExistException(YxWechatReply.class, "key", resources.getKey());
        }
        yxWechatReply.copy(resources);
        this.saveOrUpdate(yxWechatReply);
    }
}
