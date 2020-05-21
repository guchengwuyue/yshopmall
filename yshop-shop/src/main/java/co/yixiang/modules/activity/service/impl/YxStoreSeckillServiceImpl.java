/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.utils.*;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillDto;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreSeckillMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreSeckill")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreSeckillServiceImpl extends BaseServiceImpl<YxStoreSeckillMapper, YxStoreSeckill> implements YxStoreSeckillService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreSeckillQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreSeckill> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreSeckillDto> storeSeckillDTOS = generator.convert(page.getList(),YxStoreSeckillDto.class);
        int nowTime = OrderUtil.getSecondTimestampTwo();
        for (YxStoreSeckillDto storeSeckillDTO : storeSeckillDTOS){
            if(storeSeckillDTO.getStatus() > 0){
                if(storeSeckillDTO.getStartTime() > nowTime){
                    storeSeckillDTO.setStatusStr("活动未开始");
                }else if(storeSeckillDTO.getStopTime() < nowTime){
                    storeSeckillDTO.setStatusStr("活动已结束");
                }else if(storeSeckillDTO.getStopTime() > nowTime && storeSeckillDTO.getStartTime() < nowTime){
                    storeSeckillDTO.setStatusStr("正在进行中");
                }
            }else {
                storeSeckillDTO.setStatusStr("关闭");
            }

        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeSeckillDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreSeckill> queryAll(YxStoreSeckillQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreSeckill.class, criteria));
    }


    @Override
    public void download(List<YxStoreSeckillDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreSeckillDto yxStoreSeckill : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商品id", yxStoreSeckill.getProductId());
            map.put("推荐图", yxStoreSeckill.getImage());
            map.put("轮播图", yxStoreSeckill.getImages());
            map.put("活动标题", yxStoreSeckill.getTitle());
            map.put("简介", yxStoreSeckill.getInfo());
            map.put("价格", yxStoreSeckill.getPrice());
            map.put("成本", yxStoreSeckill.getCost());
            map.put("原价", yxStoreSeckill.getOtPrice());
            map.put("返多少积分", yxStoreSeckill.getGiveIntegral());
            map.put("排序", yxStoreSeckill.getSort());
            map.put("库存", yxStoreSeckill.getStock());
            map.put("销量", yxStoreSeckill.getSales());
            map.put("单位名", yxStoreSeckill.getUnitName());
            map.put("邮费", yxStoreSeckill.getPostage());
            map.put("内容", yxStoreSeckill.getDescription());
            map.put("开始时间", yxStoreSeckill.getStartTime());
            map.put("结束时间", yxStoreSeckill.getStopTime());
            map.put("添加时间", yxStoreSeckill.getAddTime());
            map.put("产品状态", yxStoreSeckill.getStatus());
            map.put("是否包邮", yxStoreSeckill.getIsPostage());
            map.put("热门推荐", yxStoreSeckill.getIsHot());
            map.put("删除 0未删除1已删除", yxStoreSeckill.getIsDel());
            map.put("最多秒杀几个", yxStoreSeckill.getNum());
            map.put("显示", yxStoreSeckill.getIsShow());
            map.put(" endTimeDate",  yxStoreSeckill.getEndTimeDate());
            map.put(" startTimeDate",  yxStoreSeckill.getStartTimeDate());
            map.put("时间段id", yxStoreSeckill.getTimeId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
