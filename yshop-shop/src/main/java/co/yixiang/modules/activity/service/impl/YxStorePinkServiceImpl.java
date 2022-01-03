/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.dto.YxStorePinkDto;
import co.yixiang.modules.activity.service.dto.YxStorePinkQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
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
//@CacheConfig(cacheNames = "yxStorePink")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStorePinkServiceImpl extends BaseServiceImpl<YxStorePinkMapper, YxStorePink> implements YxStorePinkService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStorePinkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStorePink> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStorePinkDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStorePink> queryAll(YxStorePinkQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStorePink.class, criteria));
    }


    @Override
    public void download(List<YxStorePinkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStorePinkDto yxStorePink : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户id", yxStorePink.getUid());
            map.put("订单id 生成", yxStorePink.getOrderId());
            map.put("订单id  数据库", yxStorePink.getOrderIdKey());
            map.put("购买商品个数", yxStorePink.getTotalNum());
            map.put("购买总金额", yxStorePink.getTotalPrice());
            map.put("拼团产品id", yxStorePink.getCid());
            map.put("产品id", yxStorePink.getPid());
            map.put("拼团总人数", yxStorePink.getPeople());
            map.put("拼团产品单价", yxStorePink.getPrice());
            map.put("结束时间", yxStorePink.getStopTime());
            map.put("团长id 0为团长", yxStorePink.getKId());
            map.put("是否退款 0未退款 1已退款", yxStorePink.getIsRefund());
            map.put("状态1进行中2已完成3未完成", yxStorePink.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
