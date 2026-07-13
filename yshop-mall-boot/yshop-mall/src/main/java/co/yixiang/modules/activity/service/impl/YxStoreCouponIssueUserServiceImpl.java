/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponIssueUserMapper;
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



/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponIssueUserServiceImpl extends BaseServiceImpl<YxStoreCouponIssueUserMapper, YxStoreCouponIssueUser> implements YxStoreCouponIssueUserService {

    private final IGenerator generator;

    /**
     * 添加优惠券领取记录
     * @param uid 用户id
     * @param id 前台优惠券id
     */
    @Override
    public void addUserIssue(Long uid, Integer id) {
        YxStoreCouponIssueUser couponIssueUser = new YxStoreCouponIssueUser();
        couponIssueUser.setIssueCouponId(id);
        couponIssueUser.setUid(uid);
        this.save(couponIssueUser);
    }


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCouponIssueUser> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreCouponIssueUserDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCouponIssueUser> queryAll(YxStoreCouponIssueUserQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCouponIssueUser.class, criteria));
    }


    @Override
    public void download(List<YxStoreCouponIssueUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCouponIssueUserDto yxStoreCouponIssueUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("领取优惠券用户ID", yxStoreCouponIssueUser.getUid());
            map.put("优惠券前台领取ID", yxStoreCouponIssueUser.getIssueCouponId());
            map.put("领取时间", yxStoreCouponIssueUser.getAddTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
