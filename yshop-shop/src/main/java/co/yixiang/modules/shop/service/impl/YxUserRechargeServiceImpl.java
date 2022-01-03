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
import co.yixiang.modules.shop.domain.YxUserRecharge;
import co.yixiang.modules.shop.service.YxUserRechargeService;
import co.yixiang.modules.shop.service.dto.YxUserRechargeDto;
import co.yixiang.modules.shop.service.dto.YxUserRechargeQueryCriteria;
import co.yixiang.modules.shop.service.mapper.UserRechargeMapper;
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
//@CacheConfig(cacheNames = "yxUserRecharge")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserRechargeServiceImpl extends BaseServiceImpl<UserRechargeMapper, YxUserRecharge> implements YxUserRechargeService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxUserRechargeQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxUserRecharge> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxUserRechargeDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxUserRecharge> queryAll(YxUserRechargeQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUserRecharge.class, criteria));
    }


    @Override
    public void download(List<YxUserRechargeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserRechargeDto yxUserRecharge : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("充值用户UID", yxUserRecharge.getUid());
            map.put("订单号", yxUserRecharge.getOrderId());
            map.put("充值金额", yxUserRecharge.getPrice());
            map.put("充值类型", yxUserRecharge.getRechargeType());
            map.put("是否充值", yxUserRecharge.getPaid());
            map.put("充值支付时间", yxUserRecharge.getPayTime());
            map.put("退款金额", yxUserRecharge.getRefundPrice());
            map.put("昵称", yxUserRecharge.getNickname());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
