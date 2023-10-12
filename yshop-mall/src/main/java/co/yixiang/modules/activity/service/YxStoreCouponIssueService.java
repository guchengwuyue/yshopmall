/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.activity.vo.YxStoreCouponIssueQueryVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-13
*/
public interface YxStoreCouponIssueService  extends BaseService<YxStoreCouponIssue>{

    /**
     * 领取优惠券
     * @param id id 优惠券id
     * @param uid uid
     */
    void issueUserCoupon(Integer id, Long uid);

    /**
     * 优惠券列表
     * @param page page
     * @param limit limit
     * @param uid  用户id
     * @return list
     */
    List<YxStoreCouponIssueQueryVo> getCouponList(int page, int limit, Long uid,Long productId,Integer type);

    //int couponCount(int id, int uid);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxStoreCouponIssueQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxStoreCouponIssueDto>
    */
    List<YxStoreCouponIssue> queryAll(YxStoreCouponIssueQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxStoreCouponIssueDto> all, HttpServletResponse response) throws IOException;
}
