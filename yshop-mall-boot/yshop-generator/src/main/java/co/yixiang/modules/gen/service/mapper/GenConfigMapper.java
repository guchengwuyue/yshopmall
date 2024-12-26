/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.gen.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.gen.domain.GenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GenConfigMapper extends CoreMapper<GenConfig> {
}
