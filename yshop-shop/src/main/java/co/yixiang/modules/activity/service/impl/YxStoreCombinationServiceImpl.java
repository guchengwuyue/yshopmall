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
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDto;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
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
 * @date 2020-05-13
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreCombination")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCombinationServiceImpl extends BaseServiceImpl<YxStoreCombinationMapper, YxStoreCombination> implements YxStoreCombinationService {

    private final IGenerator generator;
    private final YxStorePinkMapper yxStorePinkMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCombinationQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCombination> page = new PageInfo<>(queryAll(criteria));

        List<YxStoreCombinationDto> combinationDTOS = generator.convert(page.getList(), YxStoreCombinationDto.class);
        for (YxStoreCombinationDto combinationDTO : combinationDTOS) {
            //参与人数
            combinationDTO.setCountPeopleAll(yxStorePinkMapper.selectCount(new LambdaQueryWrapper<YxStorePink>().eq(YxStorePink::getCid, combinationDTO.getId())));

            //成团人数
            combinationDTO.setCountPeoplePink(yxStorePinkMapper.selectCount(new LambdaQueryWrapper<YxStorePink>().eq(YxStorePink::getCid, combinationDTO.getId()).eq(YxStorePink::getKId, 0)));

        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", combinationDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCombination> queryAll(YxStoreCombinationQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCombination.class, criteria));
    }


    @Override
    public void download(List<YxStoreCombinationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCombinationDto yxStoreCombination : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("商品id", yxStoreCombination.getProductId());
            map.put("推荐图", yxStoreCombination.getImage());
            map.put("轮播图", yxStoreCombination.getImages());
            map.put("活动标题", yxStoreCombination.getTitle());
            map.put("活动属性", yxStoreCombination.getAttr());
            map.put("参团人数", yxStoreCombination.getPeople());
            map.put("简介", yxStoreCombination.getInfo());
            map.put("价格", yxStoreCombination.getPrice());
            map.put("排序", yxStoreCombination.getSort());
            map.put("销量", yxStoreCombination.getSales());
            map.put("库存", yxStoreCombination.getStock());
            map.put("产品状态", yxStoreCombination.getIsShow());
            map.put(" combination", yxStoreCombination.getCombination());
            map.put("拼团内容", yxStoreCombination.getDescription());
            map.put("拼团开始时间", yxStoreCombination.getStartTime());
            map.put("拼团结束时间", yxStoreCombination.getStopTime());
            map.put("拼团订单有效时间", yxStoreCombination.getEffectiveTime());
            map.put("拼团产品成本", yxStoreCombination.getCost());
            map.put("浏览量", yxStoreCombination.getBrowse());
            map.put("单位名", yxStoreCombination.getUnitName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void onSale(Long id, int status) {
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        YxStoreCombination yxStoreCombination = new YxStoreCombination();
        yxStoreCombination.setIsShow(status);
        yxStoreCombination.setId(id);
        this.saveOrUpdate(yxStoreCombination);
    }
}
