/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.CouponEnum;
import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponIssueMapper;
import co.yixiang.modules.activity.vo.YxStoreCouponIssueQueryVo;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class YxStoreCouponIssueServiceImpl extends BaseServiceImpl<YxStoreCouponIssueMapper, YxStoreCouponIssue> implements YxStoreCouponIssueService {

    private final IGenerator generator;

    private final YxStoreCouponIssueMapper yxStoreCouponIssueMapper;


    private final YxStoreCouponUserService storeCouponUserService;
    private final YxStoreCouponIssueUserService storeCouponIssueUserService;


    /**
     * 领取优惠券
     * @param id id 优惠券id
     * @param uid uid
     */
    @Override
    public void issueUserCoupon(Integer id, Long uid) {
        YxStoreCouponIssueQueryVo couponIssueQueryVo = yxStoreCouponIssueMapper
                .selectOne(id);
        if(ObjectUtil.isNull(couponIssueQueryVo)) {
            throw new YshopException("领取的优惠劵已领完或已过期");
        }

        Long count = this.couponCount(id,uid);
        if(count > 0) {
            throw new YshopException("已领取过该优惠劵");
        }

        if(couponIssueQueryVo.getRemainCount() <= 0
                && CouponEnum.PERMANENT_0.getValue().equals(couponIssueQueryVo.getIsPermanent())){
            throw new YshopException("抱歉优惠卷已经领取完了");
        }

        storeCouponUserService.addUserCoupon(uid,couponIssueQueryVo.getCid());

        storeCouponIssueUserService.addUserIssue(uid,id);

        if(couponIssueQueryVo.getTotalCount() > 0){
            yxStoreCouponIssueMapper.decCount(id);
        }

    }


    /**
     * 优惠券列表
     * @param page page
     * @param limit limit
     * @param uid  用户id
     * @return list
     */
    @Override
    public List<YxStoreCouponIssueQueryVo> getCouponList(int page, int limit, Long uid,Long productId,Integer type) {
        Page<YxStoreCouponIssue> pageModel = new Page<>(page, limit);

        if(type == null) {
            type = CouponEnum.TYPE_0.getValue();
        }
        List<YxStoreCouponIssueQueryVo> list = yxStoreCouponIssueMapper
                .selecCoupontList(pageModel,type,productId);
        for (YxStoreCouponIssueQueryVo couponIssue : list) {
            Long count = this.couponCount(couponIssue.getId(),uid);
            if(count > 0){
                couponIssue.setIsUse(true);
            }else{
                couponIssue.setIsUse(false);
            }

        }
        return list;
    }


    /**
     * 获取用户领取优惠券数量
     * @param id 前台优惠券id
     * @param uid 用户id
     * @return int
     */
    private Long couponCount(Integer id, Long uid) {
        return storeCouponIssueUserService.lambdaQuery()
                .eq(YxStoreCouponIssueUser::getUid,uid)
                .eq(YxStoreCouponIssueUser::getIssueCouponId,id)
                .count();
    }

   //============================================================//


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
        return this.list(QueryHelpPlus.getPredicate(YxStoreCouponIssue.class, criteria));
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
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
