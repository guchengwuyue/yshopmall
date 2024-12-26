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
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.domain.YxUserSign;
import co.yixiang.modules.user.vo.SignVo;
import co.yixiang.modules.user.vo.YxUserQueryVo;

import java.util.List;

/**
 * <p>
 * 签到记录表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-05
 */
public interface YxUserSignService extends BaseService<YxUserSign> {

    /**
     *
     * @param yxUser 用户
     * @return 签到积分
     */
    int sign(YxUser yxUser);

    /**
     * 分页获取用户签到数据
     * @param uid 用户id
     * @param page  page
     * @param limit limit
     * @return list
     */
    List<SignVo> getSignList(Long uid, int page, int limit);

    //boolean getYesterDayIsSign(int uid);

    //boolean getToDayIsSign(int uid);

    //int getSignSumDay(int uid);

    /**
     * 获取签到用户信息
     * @param yxUser  yxUser
     * @return YxUserQueryVo
     */
    YxUserQueryVo userSignInfo(YxUser yxUser);


}
