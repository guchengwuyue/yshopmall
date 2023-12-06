/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.dto.PromUserDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface UserMapper extends CoreMapper<YxUser> {


    @Select("<script>SELECT u.uid,u.nickname,u.avatar,DATE_FORMAT(u.create_time,'%Y/%m/%d') as time," +
            "u.spread_count as childCount,COUNT(o.id) as orderCount," +
            "IFNULL(SUM(o.pay_price),0) as numberCount FROM yx_user u " +
            "LEFT JOIN yx_store_order o ON u.uid=o.uid " +
            "WHERE u.uid in <foreach item='id' index='index' collection='uids' " +
            " open='(' separator=',' close=')'>" +
            "   #{id}" +
            " </foreach> <if test='keyword != null'>" +
            " AND ( u.nickname LIKE CONCAT(CONCAT('%',#{keyword}),'%') OR u.phone LIKE CONCAT(CONCAT('%',#{keyword}),'%'))</if>" +
            " GROUP BY u.uid ORDER BY ${orderByStr} " +
            "</script>")
    List<PromUserDto> getUserSpreadCountList(Page page,
                                             @Param("uids") List uids,
                                             @Param("keyword") String keyword,
                                             @Param("orderByStr") String orderBy);

    @Update("update yx_user set now_money=now_money-#{payPrice}" +
            " where uid=#{uid}")
    int decPrice(@Param("payPrice") BigDecimal payPrice, @Param("uid") Long uid);
//
//    @Update("update yx_user set brokerage_price=brokerage_price+#{brokeragePrice}" +
//            " where uid=#{uid}")
//    int incBrokeragePrice(@Param("brokeragePrice") double brokeragePrice,@Param("uid") int uid);

    @Update("update yx_user set pay_count=pay_count+1" +
            " where uid=#{uid}")
    int incPayCount(@Param("uid") Long uid);

    @Update("update yx_user set now_money=now_money+#{price}" +
            " where uid=#{uid}")
    int incMoney(@Param("uid") Long uid,BigDecimal price);

    @Update("update yx_user set integral=integral-#{integral}" +
            " where uid=#{uid}")
    int decIntegral(@Param("integral") double integral,@Param("uid") Long uid);

    @Update("update yx_user set integral=integral+#{integral}" +
            " where uid=#{uid}")
    int incIntegral(@Param("integral") double integral,@Param("uid") Long uid);

    @Update( "update yx_user set status = #{status} where uid = #{id}")
    void updateOnstatus(@Param("status") Integer status, @Param("id") Long id);

    @Update( "update yx_user set now_money = now_money + ${money} where uid = #{id}")
    void updateMoney(@Param("money") double money, @Param("id") long id);

    @Update("update yx_user set brokerage_price = brokerage_price+ ${price} where uid = #{id}")
    void incBrokeragePrice(@Param("price") BigDecimal price,@Param("id") Long id);

}
