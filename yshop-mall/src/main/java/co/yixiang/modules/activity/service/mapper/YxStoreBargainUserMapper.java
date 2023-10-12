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
import co.yixiang.modules.activity.domain.YxStoreBargainUser;
import co.yixiang.modules.activity.vo.YxStoreBargainUserQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户参与砍价表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-12-21
 */
@Repository
public interface YxStoreBargainUserMapper extends CoreMapper<YxStoreBargainUser> {


    @Select("SELECT u.uid,u.is_del as isDel,u.bargain_price - u.price as residuePrice,u.id," +
            "u.bargain_id as bargainId,u.bargain_price as bargainPrice," +
            "u.bargain_price_min as bargainPriceMin,u.price,u.status,b.title," +
            "b.image,b.stop_time as datatime FROM yx_store_bargain_user u INNER JOIN " +
            "yx_store_bargain b ON b.id=u.bargain_id WHERE u.uid = #{uid} AND u.is_del = 0 " +
            "ORDER BY u.id DESC ")
    List<YxStoreBargainUserQueryVo> getBargainUserList(@Param("uid") Long uid, Page page);


}
