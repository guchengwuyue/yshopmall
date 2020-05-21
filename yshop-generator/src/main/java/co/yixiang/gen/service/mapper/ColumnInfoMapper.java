/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.gen.service.mapper;

import co.yixiang.gen.domain.ColumnConfig;
import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.gen.domain.vo.TableInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ColumnInfoMapper extends CoreMapper<ColumnConfig> {

    @Select("<script>select table_name ,create_time , engine, table_collation as coding, table_comment as remark from information_schema.tables " +
            "where table_schema = (select database()) <if test=\"name!=null\"> and table_name like CONCAT('%',#{name},'%') </if> order by create_time desc</script>")
    IPage<TableInfo> selectTablePage(@Param("page") Page page, @Param("name") String name);
    @Select("<script>select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
            "where table_schema = (select database()) order by create_time desc</script>")
    List<TableInfo> selectTables();
    @Select("select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " +
            "where table_name = #{name} and table_schema = (select database()) order by ordinal_position")
    List<Map<String,Object>> queryByTableName(@Param("name") String name);

}
