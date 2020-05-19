/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.gen.service.mapper;

import co.yixiang.gen.domain.ColumnConfig;
import co.yixiang.common.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ColumnInfoMapper extends CoreMapper<ColumnConfig> {
}
