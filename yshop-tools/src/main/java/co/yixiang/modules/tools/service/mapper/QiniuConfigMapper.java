/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.tools.domain.QiniuConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Repository
@Mapper
public interface QiniuConfigMapper extends CoreMapper<QiniuConfig> {


    @Update("update qiniu_config set type = #{type} ")
    void updateType(@Param("type") String type);
}
