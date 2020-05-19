/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.tools.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.tools.domain.AlipayConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
@Mapper
public interface AlipayConfigMapper extends CoreMapper<AlipayConfig> {

}
