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
import co.yixiang.modules.user.domain.YxUserBill;
import co.yixiang.modules.user.service.dto.BillOrderRecordDto;
import co.yixiang.modules.user.service.dto.MUserBillDto;
import co.yixiang.modules.user.service.dto.YxUserBillDto;
import co.yixiang.modules.user.vo.BillVo;
import co.yixiang.modules.user.vo.SignVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface UserBillMapper extends CoreMapper<YxUserBill> {

    @Select("select IFNULL(sum(number),0) from yx_user_bill " +
            "where status=1 and type='sign' and pm=1 and category='integral' " +
            "and uid=#{uid}")
    double sumIntegral(@Param("uid") Long uid);

    @Select("SELECT DATE_FORMAT(a.create_time,'%Y-%m-%d') as addTime,a.title,a.number " +
            "FROM yx_user_bill a INNER JOIN yx_user u ON u.uid=a.uid WHERE a.category = 'integral'" +
            " AND a.type = 'sign' AND a.status = 1 AND a.uid = #{uid} " +
            "ORDER BY a.create_time DESC")
    List<SignVo> getSignList(@Param("uid") Long uid, Page page);

    @Select("SELECT o.order_id as orderId,DATE_FORMAT(b.create_time, '%Y-%m-%d %H:%i') as time," +
            "b.number,u.avatar,u.nickname FROM yx_user_bill b " +
            "INNER JOIN yx_store_order o ON o.id=b.link_id " +
            "RIGHT JOIN yx_user u ON u.uid=o.uid" +
            " WHERE b.uid = #{uid} AND ( DATE_FORMAT(b.create_time, '%Y-%m')= #{time} ) AND " +
            "b.category = 'now_money' AND b.type = 'brokerage' ORDER BY time DESC")
    List<BillOrderRecordDto> getBillOrderRList(@Param("time") String time, @Param("uid") Long uid);

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m') as time " +
            " FROM yx_user_bill ${ew.customSqlSegment}")
    List<String> getBillOrderList(@Param(Constants.WRAPPER) Wrapper<YxUserBill> userWrapper, Page page);

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m') as time,group_concat(id SEPARATOR ',') ids " +
            " FROM yx_user_bill ${ew.customSqlSegment}")
    List<BillVo> getBillList(@Param(Constants.WRAPPER) Wrapper<YxUserBill> userWrapper, Page page);

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m-%d %H:%i') as add_time,title,number,pm " +
            " FROM yx_user_bill ${ew.customSqlSegment}")
    List<MUserBillDto> getUserBillList(@Param(Constants.WRAPPER) Wrapper<YxUserBill> userWrapper);

    @Select("select IFNULL(sum(number),0) from yx_user_bill " +
            "where status=1 and type='brokerage' and pm=1 and category='now_money' " +
            "and uid=#{uid}")
    double sumPrice(@Param("uid") int uid);

    @Select("select IFNULL(sum(number),0) from yx_user_bill " +
            "where status=1 and type='recharge' and pm=1 and category='now_money' " +
            "and uid=#{uid}")
    double sumRechargePrice(@Param("uid") Long uid);


    @Select("select IFNULL(sum(number),0) from yx_user_bill " +
            "where status=1 and type='brokerage' and pm=1 and category='now_money' " +
            "and uid=#{uid} and TO_DAYS(NOW()) - TO_DAYS(create_time) <= 1")
    double sumYesterdayPrice(@Param("uid") Long uid);

    @Select("<script> select b.title,b.pm,b.category,b.type,b.number,b.create_time ,u.nickname " +
            "from yx_user_bill b left join yx_user u on u.uid=b.uid  where 1=1  "  +
            "<if test =\"category !=''\">and b.category=#{category}</if> " +
            "<if test =\"type !=''\">and b.type=#{type}</if> " +
            "<if test =\"title !=''\">and b.title=#{title}</if> " +
            "<if test =\"pm !=null\">and b.pm=#{pm}</if> " +
            "<if test =\"date !=null\">and b.create_time &gt;= STR_TO_DATE(#{date},'%Y-%m-%d %H:%i:%s')</if> " +
            "<if test =\"date1 !=null\">and b.create_time &lt;=STR_TO_DATE(#{date1},'%Y-%m-%d %H:%i:%s')</if> " +
            "<if test =\"nickname !=''\">and u.nickname LIKE CONCAT('%',#{nickname},'%')</if> order by b.create_time desc </script> ")
    List<YxUserBillDto> findAllByQueryCriteria(@Param("category") String category, @Param("type") String type, @Param("nickname") String nickname, @Param("pm") Integer pm, @Param("date")String date, @Param("date1")String date1,@Param("title")String title);
}
