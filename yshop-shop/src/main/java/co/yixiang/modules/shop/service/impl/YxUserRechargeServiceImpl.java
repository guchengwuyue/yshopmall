/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxUserRecharge;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxUserRechargeService;
import co.yixiang.modules.shop.service.dto.YxUserRechargeDto;
import co.yixiang.modules.shop.service.dto.YxUserRechargeQueryCriteria;
import co.yixiang.modules.shop.service.mapper.UserRechargeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
    public List<YxUserRecharge> queryAll(YxUserRechargeQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUserRecharge.class, criteria));
    }


    @Override
    public void download(List<YxUserRechargeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserRechargeDto yxUserRecharge : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("充值用户UID", yxUserRecharge.getUid());
            map.put("订单号", yxUserRecharge.getOrderId());
            map.put("充值金额", yxUserRecharge.getPrice());
            map.put("充值类型", yxUserRecharge.getRechargeType());
            map.put("是否充值", yxUserRecharge.getPaid());
            map.put("充值支付时间", yxUserRecharge.getPayTime());
            map.put("充值时间", yxUserRecharge.getAddTime());
            map.put("退款金额", yxUserRecharge.getRefundPrice());
            map.put("昵称", yxUserRecharge.getNickname());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
