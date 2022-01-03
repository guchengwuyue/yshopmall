/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.system.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.system.domain.UserAvatar;
import co.yixiang.modules.system.service.UserAvatarService;
import co.yixiang.modules.system.service.dto.UserAvatarDto;
import co.yixiang.modules.system.service.dto.UserAvatarQueryCriteria;
import co.yixiang.modules.system.service.mapper.UserAvatarMapper;
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
 * @date 2020-05-14
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "userAvatar")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserAvatarServiceImpl extends BaseServiceImpl<UserAvatarMapper, UserAvatar> implements UserAvatarService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(UserAvatarQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<UserAvatar> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), UserAvatarDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<UserAvatar> queryAll(UserAvatarQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(UserAvatar.class, criteria));
    }


    @Override
    public void download(List<UserAvatarDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserAvatarDto userAvatar : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("真实文件名", userAvatar.getRealName());
            map.put("路径", userAvatar.getPath());
            map.put("大小", userAvatar.getSize());
            map.put("创建时间", userAvatar.getCreateTime());
            map.put("真实文件名", userAvatar.getRealName());
            map.put("路径", userAvatar.getPath());
            map.put("大小", userAvatar.getSize());
            map.put("创建时间", userAvatar.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public UserAvatar saveFile(UserAvatar userAvatar) {
        //todo
        return null;
    }
}
