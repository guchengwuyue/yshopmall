/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.dto.ChartDataDto;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Repository
@Mapper
public interface StoreOrderMapper extends CoreMapper<YxStoreOrder> {

    @Select("SELECT COUNT(*) FROM yx_store_order WHERE pay_time >= ${today}")
    Integer countByPayTimeGreaterThanEqual(@Param("today") int today);

    @Select("SELECT COUNT(*) FROM yx_store_order WHERE pay_time < ${today}  and pay_time >= ${yesterday}")
    Integer countByPayTimeLessThanAndPayTimeGreaterThanEqual(@Param("today") int today, @Param("yesterday") int yesterday);

    @Select("select IFNULL(sum(pay_price),0)  from yx_store_order " +
            "where refund_status=0 and is_del=0 and paid=1")
    Double sumTotalPrice();

    //修复了“错误代码1055与sql_mode = only_full_group_by不兼容”问题
    //修复方式create_time外面包裹ANY_VALUE关键字
    @Select("SELECT IFNULL(sum(pay_price),0) as num," +
            "FROM_UNIXTIME(ANY_VALUE(create_time), '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= ${time}" +
            " GROUP BY FROM_UNIXTIME(ANY_VALUE(create_time),'%Y-%m-%d') " +
            " ORDER BY ANY_VALUE(create_time) ASC")
    List<ChartDataDto> chartList(@Param("time") int time);

    @Select("SELECT count(id) as num," +
            "FROM_UNIXTIME(ANY_VALUE(create_time), '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= ${time}" +
            " GROUP BY FROM_UNIXTIME(ANY_VALUE(create_time),'%Y-%m-%d') " +
            " ORDER BY ANY_VALUE(create_time) ASC")
    List<ChartDataDto> chartListT(@Param("time") int time);
}
