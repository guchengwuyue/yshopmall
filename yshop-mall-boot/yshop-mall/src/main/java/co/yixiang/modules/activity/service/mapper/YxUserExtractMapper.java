/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.activity.domain.YxUserExtract;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
public interface YxUserExtractMapper extends CoreMapper<YxUserExtract> {
    @Select("select IFNULL(sum(extract_price),0) from yx_user_extract " +
            "where status=1 " +
            "and uid=#{uid}")
    double sumPrice(@Param("uid") Long uid);
}
