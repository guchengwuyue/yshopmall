/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service;


import co.yixiang.common.service.BaseService;
import co.yixiang.modules.activity.domain.YxStoreBargainUser;
import co.yixiang.modules.activity.vo.YxStoreBargainUserQueryVo;

import java.util.List;

/**
 * <p>
 * 用户参与砍价表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-21
 */
public interface YxStoreBargainUserService extends BaseService<YxStoreBargainUser> {

    /**
     * 修改用户砍价状态
     * @param bargainId 砍价产品id
     * @param uid 用户id
     */
    void setBargainUserStatus(Long bargainId, Long uid);

    /**
     * 砍价取消
     * @param bargainId 砍价商品id
     * @param uid uid
     */
    void bargainCancel(Long bargainId,Long uid);

    /**
     * 获取用户的砍价产品
     * @param bargainUserUid 用户id
     * @param page page
     * @param limit limit
     * @return List
     */
    List<YxStoreBargainUserQueryVo> bargainUserList(Long bargainUserUid, int page, int limit);

    /**
     * 判断用户是否还可以砍价
     * @param bargainId 砍价产品id
     * @param bargainUserUid 开启砍价用户id
     * @param uid  当前用户id
     * @return false=NO true=YES
     */
    boolean isBargainUserHelp(Long bargainId,Long bargainUserUid,Long uid);

    /**
     * 添加砍价记录
     * @param bargainId 砍价商品id
     * @param uid 用户id
     */
    void setBargain(Long bargainId,Long uid);

    //double getBargainUserDiffPrice(int id);


    /**
     * 获取某个用户参与砍价信息
     * @param bargainId 砍价id
     * @param uid 用户id
     * @return  YxStoreBargainUser
     */
    YxStoreBargainUser getBargainUserInfo(Long bargainId, Long uid);

    //List<YxStoreBargainUserQueryVo> getBargainUserList(int bargainId,int status);

    /**
     * 获取参与砍价的用户数量
     *
     * @param bargainId 砍价id
     * @param status    状态  OrderInfoEnum 1 进行中  2 结束失败  3结束成功
     * @return int
     */
    Long getBargainUserCount(Long bargainId,Integer status);


}
