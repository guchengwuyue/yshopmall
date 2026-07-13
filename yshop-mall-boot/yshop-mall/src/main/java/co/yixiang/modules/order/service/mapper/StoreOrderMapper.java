/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.order.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.service.dto.ChartDataDto;
import co.yixiang.modules.order.vo.OrderDataVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface StoreOrderMapper extends CoreMapper<YxStoreOrder> {



    @Select("SELECT sum(pay_price) as price,count(id) as count," +
            "DATE_FORMAT(create_time, '%m-%d') as time FROM yx_store_order" +
            " WHERE is_del = 0 AND paid = 1 AND refund_status = 0 " +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY create_time DESC")
    List<OrderDataVo> getOrderDataPriceList(Page page);


    @Select("SELECT IFNULL(sum(pay_price),0) " +
            " FROM yx_store_order ${ew.customSqlSegment}")
    Double todayPrice(@Param(Constants.WRAPPER) Wrapper<YxStoreOrder> wrapper);



    @Select("select IFNULL(sum(pay_price),0) from yx_store_order " +
            "where paid=1 and is_del=0 and refund_status=0 and uid=#{uid}")
    double sumPrice(@Param("uid") Long uid);


    @Select("SELECT COUNT(*) FROM yx_store_order WHERE pay_time >= ${today}")
    Integer countByPayTimeGreaterThanEqual(@Param("today")int today);

    @Select("SELECT COUNT(*) FROM yx_store_order WHERE pay_time < ${today}  and pay_time >= ${yesterday}")
    Integer countByPayTimeLessThanAndPayTimeGreaterThanEqual(@Param("today")int today, @Param("yesterday")int yesterday);

    @Select( "select IFNULL(sum(pay_price),0)  from yx_store_order " +
            "where refund_status=0 and is_del=0 and paid=1")
    Double sumTotalPrice();

    @Select("SELECT IFNULL(sum(pay_price),0) as num," +
            "DATE_FORMAT(ANY_VALUE(create_time), '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= #{time}" +
            " GROUP BY DATE_FORMAT(ANY_VALUE(create_time),'%Y-%m-%d') " +
            " ORDER BY ANY_VALUE(create_time) ASC")
    List<ChartDataDto> chartList(@Param("time") Date time);
    @Select("SELECT count(id) as num," +
            "DATE_FORMAT(ANY_VALUE(create_time), '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= #{time}" +
            " GROUP BY DATE_FORMAT(ANY_VALUE(create_time),'%Y-%m-%d') " +
            " ORDER BY ANY_VALUE(create_time) ASC")
    List<ChartDataDto> chartListT(@Param("time") Date time);
}
