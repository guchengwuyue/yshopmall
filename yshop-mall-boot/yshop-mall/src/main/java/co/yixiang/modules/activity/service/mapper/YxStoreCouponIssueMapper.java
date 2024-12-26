/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.modules.activity.vo.YxStoreCouponIssueQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
public interface YxStoreCouponIssueMapper extends CoreMapper<YxStoreCouponIssue> {
    @Select("<script>select A.cid,A.end_time as endTime,A.start_time as startTime,A.cname,A.ctype," +
            "A.is_permanent as isPermanent,A.remain_count as remainCount," +
            "A.total_count as totalCount,A.id,B.coupon_price as couponPrice," +
            "B.use_min_price as useMinPrice" +
            " from yx_store_coupon_issue A left join yx_store_coupon B " +
            "on A.cid = B.id " +
            "where A.status =1 <if test='type == 1'> AND B.type = #{type} AND FIND_IN_SET(#{productId},product_id)</if> " +
            "<if test='type == 0'> AND B.type in (0,1)</if>" +
            " AND (  A.start_time &lt; now()  AND A.end_time &gt; now() ) " +
            " AND A.is_del = 0  AND " +
            "( A.remain_count > 0 OR A.is_permanent = 1 ) ORDER BY B.sort DESC</script>")
    List<YxStoreCouponIssueQueryVo> selecCoupontList(Page page,@Param("type") Integer type,
    @Param("productId") Long productId);

    @Select("select A.cid,A.end_time as endTime,A.start_time as startTime," +
            "A.is_permanent as isPermanent,A.remain_count as remainCount," +
            "A.total_count as totalCount,A.id" +
            " from yx_store_coupon_issue A" +
            " where A.status =1 and A.id=#{id}" +
            " AND (  A.start_time < now()  AND A.end_time > now() ) " +
            " AND A.is_del = 0  AND " +
            "( A.remain_count > 0 OR A.is_permanent = 1 )")
    YxStoreCouponIssueQueryVo selectOne(Integer id);

    @Update("update yx_store_coupon_issue set remain_count=remain_count-1" +
            " where id=#{id}")
    int decCount(@Param("id") int id);
}
