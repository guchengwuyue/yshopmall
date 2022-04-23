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
import co.yixiang.modules.mp.domain.YxWechatMenu;
import co.yixiang.modules.mp.service.YxWechatMenuService;
import co.yixiang.modules.mp.service.dto.YxWechatMenuDto;
import co.yixiang.modules.mp.service.dto.YxWechatMenuQueryCriteria;
import co.yixiang.modules.mp.service.mapper.WechatMenuMapper;
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
//@CacheConfig(cacheNames = "yxWechatMenu")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatMenuServiceImpl extends BaseServiceImpl<WechatMenuMapper, YxWechatMenu> implements YxWechatMenuService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxWechatMenuQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxWechatMenu> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxWechatMenuDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxWechatMenu> queryAll(YxWechatMenuQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxWechatMenu.class, criteria));
    }


    @Override
    public void download(List<YxWechatMenuDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxWechatMenuDto yxWechatMenu : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("缓存数据", yxWechatMenu.getResult());
            map.put("缓存时间", yxWechatMenu.getAddTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Boolean isExist(String wechat_menus) {
        YxWechatMenu yxWechatMenu = this.getOne(new LambdaQueryWrapper<YxWechatMenu>().eq(YxWechatMenu::getKey, wechat_menus));
        if (yxWechatMenu == null) {
            return false;
        }
        return true;
    }
}
