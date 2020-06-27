/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package ${package}.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import ${package}.domain.${className};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author ${author}
* @date ${date}
*/
@Repository
@Mapper
public interface ${className}Mapper extends CoreMapper<${className}> {

}
