/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponIssueMapper;
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
//@CacheConfig(cacheNames = "yxStoreCouponIssue")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponIssueServiceImpl extends BaseServiceImpl<YxStoreCouponIssueMapper, YxStoreCouponIssue> implements YxStoreCouponIssueService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCouponIssueQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCouponIssue> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreCouponIssueDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCouponIssue> queryAll(YxStoreCouponIssueQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCouponIssue.class, criteria));
    }


    @Override
    public void download(List<YxStoreCouponIssueDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCouponIssueDto yxStoreCouponIssue : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" cname",  yxStoreCouponIssue.getCname());
            map.put("优惠券ID", yxStoreCouponIssue.getCid());
            map.put("优惠券领取开启时间", yxStoreCouponIssue.getStartTime());
            map.put("优惠券领取结束时间", yxStoreCouponIssue.getEndTime());
            map.put("优惠券领取数量", yxStoreCouponIssue.getTotalCount());
            map.put("优惠券剩余领取数量", yxStoreCouponIssue.getRemainCount());
            map.put("是否无限张数", yxStoreCouponIssue.getIsPermanent());
            map.put("1 正常 0 未开启 -1 已无效", yxStoreCouponIssue.getStatus());
            map.put(" isDel",  yxStoreCouponIssue.getIsDel());
            map.put("优惠券添加时间", yxStoreCouponIssue.getAddTime());
            map.put(" endTimeDate",  yxStoreCouponIssue.getEndTimeDate());
            map.put(" startTimeDate",  yxStoreCouponIssue.getStartTimeDate());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
