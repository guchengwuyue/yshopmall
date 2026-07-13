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
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.vo.StoreCouponUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
public interface YxStoreCouponUserMapper extends CoreMapper<YxStoreCouponUser> {
    @Select("select A.id,A.coupon_title as couponTitle,A.coupon_price as couponPrice," +
            "A.end_time as endTime,B.use_min_price as useMinPrice,B.type," +
            "B.product_id as productId" +
            " from yx_store_coupon_user A left join yx_store_coupon B " +
            "on A.cid = B.id " +
            "where A.status = 0" +
            " AND A.end_time > #{now}  " +
            " AND A.uid = #{uid}  AND A.use_min_price <= #{price} " +
            " ORDER BY B.id DESC")
    List<StoreCouponUserVo> selectCouponList(@Param("now") Date now, @Param("price") double price,
                                             @Param("uid") Long uid);
}
