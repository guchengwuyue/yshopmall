/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.mp.domain.YxArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Repository
@Mapper
public interface ArticleMapper extends CoreMapper<YxArticle> {

}
