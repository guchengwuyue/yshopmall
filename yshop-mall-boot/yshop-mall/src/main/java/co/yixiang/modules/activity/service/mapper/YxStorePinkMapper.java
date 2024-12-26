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
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.dto.PinkDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface YxStorePinkMapper extends CoreMapper<YxStorePink> {
    @Select("SELECT p.id,p.uid,p.people,p.price,p.stop_time as stopTime,u.nickname,u.avatar" +
            " FROM yx_store_pink p INNER JOIN yx_user u ON u.uid=p.uid" +
            " WHERE stop_time > now() AND p.cid = #{cid} AND p.k_id = 0 " +
            "AND p.is_refund = 0 ORDER BY p.create_time DESC")
    List<PinkDto> getPinks(Long cid);

    //<![CDATA[ >= ]]>
    @Select("SELECT p.id,u.nickname,u.avatar" +
            " FROM yx_store_pink p RIGHT  JOIN yx_user u ON u.uid=p.uid" +
            " where p.status= 2 AND p.uid <> ${uid} " +
            "AND p.is_refund = 0")
    List<PinkDto> getPinkOkList(Long uid);

    @Select("SELECT p.id,p.uid,p.people,p.price,p.stop_time as stopTime,u.nickname,u.avatar" +
            " FROM yx_store_pink p LEFT JOIN yx_user u ON u.uid=p.uid" +
            " where p.k_id= ${kid} " +
            "AND p.is_refund = 0")
    List<PinkDto> getPinkMember(int kid);

    @Select("SELECT p.id,p.uid,p.people,p.price,p.stop_time as stopTime,u.nickname,u.avatar" +
            " FROM yx_store_pink p LEFT JOIN yx_user u ON u.uid=p.uid" +
            " where p.id= ${id} ")
    PinkDto getPinkUserOne(int id);

    @Select("select IFNULL(sum(total_num),0) from yx_store_pink " +
            "where status=2 and is_refund=0")
    int sumNum();
}
