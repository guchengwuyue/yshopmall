/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.mp.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.mp.domain.YxArticle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface ArticleMapper extends CoreMapper<YxArticle> {
    @Update("update yx_article set visit=visit+1 " +
            "where id=#{id}")
    int incVisitNum(@Param("id") int id);

}
