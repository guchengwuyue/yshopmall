/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.tools.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.tools.domain.QiniuContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
@Mapper
public interface QiniuContentMapper extends CoreMapper<QiniuContent> {

}
