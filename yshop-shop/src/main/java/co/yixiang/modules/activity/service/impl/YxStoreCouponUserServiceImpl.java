/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.YxUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponUserMapper;
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
//@CacheConfig(cacheNames = "yxStoreCouponUser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponUserServiceImpl extends BaseServiceImpl<YxStoreCouponUserMapper, YxStoreCouponUser> implements YxStoreCouponUserService {

    private final IGenerator generator;
    private final YxUserService userService;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCouponUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCouponUser> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreCouponUserDto> storeOrderDTOS = generator.convert(page.getList(),YxStoreCouponUserDto.class);
        for (YxStoreCouponUserDto couponUserDTO : storeOrderDTOS) {
            couponUserDTO.setNickname(userService.getOne(new QueryWrapper<YxUser>().eq("uid",couponUserDTO.getUid())).getNickname());
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeOrderDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCouponUser> queryAll(YxStoreCouponUserQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCouponUser.class, criteria));
    }
    @Override
    public void download(List<YxStoreCouponUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCouponUserDto yxStoreCouponUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("兑换的项目id", yxStoreCouponUser.getCid());
            map.put("优惠券所属用户", yxStoreCouponUser.getUid());
            map.put("优惠券名称", yxStoreCouponUser.getCouponTitle());
            map.put("优惠券的面值", yxStoreCouponUser.getCouponPrice());
            map.put("最低消费多少金额可用优惠券", yxStoreCouponUser.getUseMinPrice());
            map.put("优惠券创建时间", yxStoreCouponUser.getAddTime());
            map.put("优惠券结束时间", yxStoreCouponUser.getEndTime());
            map.put("使用时间", yxStoreCouponUser.getUseTime());
            map.put("获取方式", yxStoreCouponUser.getType());
            map.put("状态（0：未使用，1：已使用, 2:已过期）", yxStoreCouponUser.getStatus());
            map.put("是否有效", yxStoreCouponUser.getIsFail());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
