/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.user.domain.YxUserLevel;


/**
 * <p>
 * 用户等级记录表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-06
 */
public interface YxUserLevelService extends BaseService<YxUserLevel> {


    /**
     * 检查是否能成为会员
     * @param uid 用户id
     */
    boolean setLevelComplete(Long uid);

    //UserLevelInfoDto getUserLevelInfo(int id);

    /**
     * 获取当前用户会员等级返回当前用户等级
     * @param uid uid
     * @param grade 用户级别
     * @return YxUserLevel
     */
    YxUserLevel getUserLevel(Long uid, Integer grade);


}
