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
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.dto.PromUserDto;
import co.yixiang.modules.user.service.dto.UserMoneyDto;
import co.yixiang.modules.user.service.dto.YxUserDto;
import co.yixiang.modules.user.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxUserService  extends BaseService<YxUser>{

    /**
     * 返回用户累计充值金额与消费金额
     * @param uid uid
     * @return Double[]
     */
    Double[] getUserMoney(Long uid);

    /**
     * 一级返佣
     * @param order 订单
     */
    void backOrderBrokerage(YxStoreOrderQueryVo order);



    /**
     * 统计分销人员
     *
     * @param uid uid
     * @return map
     */
    Map<String,Long> getSpreadCount(Long uid);

    /**
     * 获取我的分销下人员列表
     * @param uid uid
     * @param page page
     * @param limit limit
     * @param grade ShopCommonEnum.GRADE_0
     * @param keyword 关键字搜索
     * @param sort 排序
     * @return list
     */
    List<PromUserDto> getUserSpreadGrade(Long uid, int page, int limit, Integer grade, String keyword, String sort);

    /**
     * 减去用户积分
     * @param uid 用户id
     * @param integral 积分
     */
    void decIntegral(Long uid,double integral);

    /**
     * 增加购买次数
     * @param uid uid
     */
    void incPayCount(Long uid);

    /**
     * 减去用户余额
     * @param uid uid
     * @param payPrice 金额
     */
    void decPrice(Long uid, BigDecimal payPrice);

   // YxUser findByName(String name);

    /**
     * 更新用户余额
     * @param uid y用户id
     * @param price 金额
     */
    void incMoney(Long uid,BigDecimal price);

    /**
     * 增加积分
     * @param uid uid
     * @param integral 积分
     */
    void incIntegral(Long uid,double integral);

    /**
     * 获取用户信息
     * @param uid uid
     * @return YxUserQueryVo
     */
    YxUserQueryVo getYxUserById(Long uid);

    /**
     * 获取用户个人详细信息
     * @param yxUser yxUser
     * @return YxUserQueryVo
     */
    YxUserQueryVo getNewYxUserById(YxUser yxUser);

    /**
     * 转换用户信息
     * @param yxUser user
     * @return YxUserQueryVo
     */
    YxUserQueryVo handleUser(YxUser yxUser);

    /**
     * 返回会员价
     * @param price 原价
     * @param uid 用户id
     * @return vip 价格
     */
    double setLevelPrice(double price, long uid);

    /**
     * 设置推广关系
     * @param spread 上级人
     * @param uid 本人
     */
    void setSpread(String spread, long uid);


    /**
     * 查看下级
     * @param uid uid
     * @param grade 等级
     * @return list
     */
    List<PromUserDto> querySpread(Long uid, Integer grade);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxUserDto>
    */
    List<YxUser> queryAll(YxUserQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxUserDto> all, HttpServletResponse response) throws IOException;

    /**
     * 更新用户状态
     * @param uid uid
     * @param status ShopCommonEnum
     */
    void onStatus(Long uid, Integer status);

    /**
     * 修改余额
     * @param param UserMoneyDto
     */
    void updateMoney(UserMoneyDto param);

    /**
     * 增加佣金
     * @param price 金额
     * @param uid 用户id
     */
    void incBrokeragePrice(BigDecimal price, Long uid);


}
