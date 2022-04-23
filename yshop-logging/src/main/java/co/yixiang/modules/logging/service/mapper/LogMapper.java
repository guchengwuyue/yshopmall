/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.logging.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.logging.domain.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hupeng
 * @date 2019-5-22
 */
@Repository
@Mapper
public interface LogMapper extends CoreMapper<Log> {

    @Delete("delete from log where log_type = #{logType}")
    void deleteByLogType(@Param("logType") String logType);

    @Select("<script>select l.id,l.create_time as createTime,l.description, l.request_ip as requestIp,l.address,u.nickname from log l  " +
            " left join yx_user u on u.uid=l.uid where l.type=1 " +
            " <if test = \"nickname !=null\"> and u.nickname LIKE CONCAT('%',#{nickname},'%')</if> order by l.id desc</script>")
    List<Log> findAllByPageable(@Param("nickname") String nickname);

    @Select("select count(*) FROM (select request_ip FROM log where create_time between #{date1} and #{date2} GROUP BY request_ip) as s")
    long findIp(@Param("date1") String date1, @Param("date2") String date2);
}
