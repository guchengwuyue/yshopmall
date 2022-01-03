/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.dto.YxUserBillDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Repository
@Mapper
public interface UserBillMapper extends CoreMapper<YxUserBill> {

    @Select("<script> select b.title,b.pm,b.category,b.type,b.number,b.create_time ,u.nickname " +
            "from yx_user_bill b left join yx_user u on u.uid=b.uid  where 1=1  " +
            "<if test =\"category !=''\">and b.category=#{category}</if> " +
            "<if test =\"type !=''\">and b.type=#{type}</if> " +
            "<if test =\"nickname !=''\">and u.nickname LIKE CONCAT('%',#{nickname},'%')</if> </script> ")
    List<YxUserBillDto> findAllByQueryCriteria(@Param("category") String category, @Param("type") String type, @Param("nickname") String nickname);
}
