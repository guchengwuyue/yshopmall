/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
import co.yixiang.modules.activity.service.mapper.YxStoreVisitMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDto;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
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
//@CacheConfig(cacheNames = "yxStoreCombination")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCombinationServiceImpl extends BaseServiceImpl<YxStoreCombinationMapper, YxStoreCombination> implements YxStoreCombinationService {

    private final IGenerator generator;
    private final YxStorePinkMapper yxStorePinkMapper;
    private final YxStoreVisitMapper yxStoreVisitMapper;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCombinationQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCombination> page = new PageInfo<>(queryAll(criteria));

        List<YxStoreCombinationDto> combinationDTOS = generator.convert(page.getList(),YxStoreCombinationDto.class);
        for (YxStoreCombinationDto combinationDTO : combinationDTOS) {
            //参与人数
            combinationDTO.setCountPeopleAll(yxStorePinkMapper.selectCount(new QueryWrapper<YxStorePink>().eq("cid",combinationDTO.getId())));

            //成团人数
            combinationDTO.setCountPeoplePink(yxStorePinkMapper.selectCount(new QueryWrapper<YxStorePink>().eq("cid",combinationDTO.getId()).eq("k_id",0)));
            //获取查看拼团产品人数
            combinationDTO.setCountPeopleBrowse(yxStoreVisitMapper.selectCount(new QueryWrapper<YxStoreVisit>().eq("product_id",combinationDTO.getId())
                    .eq("product_type","combination")));
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content",combinationDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCombination> queryAll(YxStoreCombinationQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCombination.class, criteria));
    }


    @Override
    public void download(List<YxStoreCombinationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCombinationDto yxStoreCombination : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商品id", yxStoreCombination.getProductId());
            map.put("商户id", yxStoreCombination.getMerId());
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
            map.put("添加时间", yxStoreCombination.getAddTime());
            map.put("推荐", yxStoreCombination.getIsHost());
            map.put("产品状态", yxStoreCombination.getIsShow());
            map.put(" isDel",  yxStoreCombination.getIsDel());
            map.put(" combination",  yxStoreCombination.getCombination());
            map.put("商户是否可用1可用0不可用", yxStoreCombination.getMerUse());
            map.put("是否包邮1是0否", yxStoreCombination.getIsPostage());
            map.put("邮费", yxStoreCombination.getPostage());
            map.put("拼团内容", yxStoreCombination.getDescription());
            map.put("拼团开始时间", yxStoreCombination.getStartTime());
            map.put("拼团结束时间", yxStoreCombination.getStopTime());
            map.put("拼团订单有效时间", yxStoreCombination.getEffectiveTime());
            map.put("拼图产品成本", yxStoreCombination.getCost());
            map.put("浏览量", yxStoreCombination.getBrowse());
            map.put("单位名", yxStoreCombination.getUnitName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void onSale(Integer id, int status) {
        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }
        YxStoreCombination yxStoreCombination = new YxStoreCombination();
        yxStoreCombination.setIsShow(status);
        yxStoreCombination.setId(id);
        this.saveOrUpdate(yxStoreCombination);
    }
}
