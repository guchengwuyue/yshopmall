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
import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffQueryCriteria;
import co.yixiang.modules.shop.service.mapper.SystemStoreStaffMapper;
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
//@CacheConfig(cacheNames = "yxSystemStoreStaff")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemStoreStaffServiceImpl extends BaseServiceImpl<SystemStoreStaffMapper, YxSystemStoreStaff> implements YxSystemStoreStaffService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemStoreStaffQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemStoreStaff> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxSystemStoreStaffDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemStoreStaff> queryAll(YxSystemStoreStaffQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemStoreStaff.class, criteria));
    }


    @Override
    public void download(List<YxSystemStoreStaffDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemStoreStaffDto yxSystemStoreStaff : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("微信用户id", yxSystemStoreStaff.getUid());
            map.put("店员头像", yxSystemStoreStaff.getAvatar());
            map.put("门店id", yxSystemStoreStaff.getStoreId());
            map.put("店员名称", yxSystemStoreStaff.getStaffName());
            map.put("手机号码", yxSystemStoreStaff.getPhone());
            map.put("核销开关", yxSystemStoreStaff.getVerifyStatus());
            map.put("状态", yxSystemStoreStaff.getStatus());
            map.put("微信昵称", yxSystemStoreStaff.getNickname());
            map.put("所属门店", yxSystemStoreStaff.getStoreName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
