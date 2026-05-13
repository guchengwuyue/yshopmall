/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.api.ApiCode;
import co.yixiang.api.UnAuthenticatedException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.order.service.YxStoreOrderCartInfoService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.mapper.StoreOrderMapper;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.UserMoneyDto;
import co.yixiang.modules.user.service.dto.YxUserDto;
import co.yixiang.modules.user.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.user.service.mapper.UserBillMapper;
import co.yixiang.modules.user.service.mapper.UserMapper;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserServiceImpl extends BaseServiceImpl<UserMapper, YxUser> implements YxUserService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private UserMapper yxUserMapper;
    @Autowired
    private StoreOrderMapper storeOrderMapper;
    @Autowired
    private UserBillMapper userBillMapper;


    @Lazy
    @Autowired
    private YxStoreOrderService orderService;
    @Autowired
    private YxSystemConfigService systemConfigService;
    @Autowired
    private YxUserBillService billService;
    @Autowired
    private YxStoreCouponUserService storeCouponUserService;
    @Autowired
    private YxSystemStoreStaffService systemStoreStaffService;
    @Autowired
    private YxStoreOrderCartInfoService storeOrderCartInfoService;


    /**
     * 返回用户累计充值金额与消费金额
     * @param uid uid
     * @return Double[]
     */
    @Override
    public Double[] getUserMoney(Long uid){
        double sumPrice = storeOrderMapper.sumPrice(uid);
        double sumRechargePrice = userBillMapper.sumRechargePrice(uid);
        return new Double[]{sumPrice,sumRechargePrice};
    }


    /**
     * 增加购买次数
     * @param uid uid
     */
    @Override
    public void incPayCount(Long uid) {
        yxUserMapper.incPayCount(uid);
    }

    /**
     * 减去用户余额
     * @param uid uid
     * @param payPrice 金额
     */
    @Override
    public void decPrice(Long uid, BigDecimal payPrice) {
        yxUserMapper.decPrice(payPrice,uid);
    }

    /**
     * 减去用户积分
     * @param uid 用户id
     * @param integral 积分
     */
    @Override
    public void decIntegral(Long uid, double integral) {
        yxUserMapper.decIntegral(integral,uid);
    }






    /**
     * 更新用户余额
     * @param uid y用户id
     * @param price 金额
     */
    @Override
    public void incMoney(Long uid, BigDecimal price) {
        if(price!=null&&price.doubleValue()>0){
            yxUserMapper.incMoney(uid,price);
        }
    }

    /**
     * 增加积分
     * @param uid uid
     * @param integral 积分
     */
    @Override
    public void incIntegral(Long uid, double integral) {
        yxUserMapper.incIntegral(integral,uid);
    }


    /**
     * 获取用户信息
     * @param uid uid
     * @return YxUserQueryVo
     */
    @Override
    public YxUserQueryVo getYxUserById(Long uid) {
        return generator.convert(this.getById(uid),YxUserQueryVo.class);
    }


    /**
     * 转换用户信息
     * @param yxUser user
     * @return YxUserQueryVo
     */
    @Override
    public YxUserQueryVo handleUser(YxUser yxUser) {
        return generator.convert(yxUser,YxUserQueryVo.class);
    }

    /**
     * 获取用户个人详细信息
     * @param yxUser yxUser
     * @return YxUserQueryVo
     */
    @Override
    public YxUserQueryVo getNewYxUserById(YxUser yxUser) {
        YxUserQueryVo userQueryVo = generator.convert(yxUser,YxUserQueryVo.class);
        if(userQueryVo == null){
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }
        userQueryVo.setOrderStatusNum(orderService.orderData(yxUser.getUid()));
        userQueryVo.setCouponCount(storeCouponUserService.getUserValidCouponCount(yxUser.getUid()));
        //判断分销类型,指定分销废弃掉，只有一种分销方式
        /**
            String statu = systemConfigService.getData(SystemConfigConstants.STORE_BROKERAGE_STATU);
            if(StrUtil.isNotEmpty(statu)){
                userQueryVo.setStatu(Integer.valueOf(statu));
            }else{
                userQueryVo.setStatu(0);
            }
         **/

        //获取核销权限
        userQueryVo.setCheckStatus(systemStoreStaffService.checkStatus(yxUser.getUid(),null));


        return userQueryVo;
    }



    /**
     * 返回会员价
     * @param price 原价
     * @param uid 用户id
     * @return vip 价格
     */
    @Override
    public double setLevelPrice(double price, long uid) {

        int discount = 100;
        return NumberUtil.mul(NumberUtil.div(discount,100),price);
    }








    //===========后面管理后台部分=====================//





    @Override
    public Map<String, Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxUser> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxUserDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxUser> queryAll(YxUserQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUser.class, criteria));
    }


    @Override
    public void download(List<YxUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserDto yxUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户账户(跟accout一样)", yxUser.getUsername());
            map.put("用户密码（跟pwd）", yxUser.getPassword());
            map.put("真实姓名", yxUser.getRealName());
            map.put("生日", yxUser.getBirthday());
            map.put("身份证号码", yxUser.getCardId());
            map.put("用户备注", yxUser.getMark());
            map.put("合伙人id", yxUser.getPartnerId());
            map.put("用户分组id", yxUser.getGroupId());
            map.put("用户昵称", yxUser.getNickname());
            map.put("用户头像", yxUser.getAvatar());
            map.put("手机号码", yxUser.getPhone());
            map.put("添加时间", yxUser.getCreateTime());
            map.put("添加ip", yxUser.getAddIp());
            map.put("用户余额", yxUser.getNowMoney());
            map.put("佣金金额", yxUser.getBrokeragePrice());
            map.put("用户剩余积分", yxUser.getIntegral());
            map.put("连续签到天数", yxUser.getSignNum());
            map.put("1为正常，0为禁止", yxUser.getStatus());
            map.put("等级", yxUser.getLevel());
            map.put("推广元id", yxUser.getSpreadUid());
            map.put("推广员关联时间", yxUser.getSpreadTime());
            map.put("用户类型", yxUser.getUserType());
            map.put("是否为推广员", yxUser.getIsPromoter());
            map.put("用户购买次数", yxUser.getPayCount());
            map.put("下级人数", yxUser.getSpreadCount());
            map.put("详细地址", yxUser.getAddres());
            map.put("管理员编号 ", yxUser.getAdminid());
            map.put("用户登陆类型，h5,wechat,routine", yxUser.getLoginType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 更新用户状态
     * @param uid uid
     * @param status ShopCommonEnum
     */
    @Override
    public void onStatus(Long uid, Integer status) {
        if(ShopCommonEnum.IS_STATUS_1.getValue().equals(status)){
            status = ShopCommonEnum.IS_STATUS_0.getValue();
        }else{
            status = ShopCommonEnum.IS_STATUS_1.getValue();
        }
        yxUserMapper.updateOnstatus(status,uid);
    }

    /**
     * 修改余额
     * @param param UserMoneyDto
     */
    @Override
    public void updateMoney(UserMoneyDto param) {
        YxUser yxUser = this.getById(param.getUid());
        double newMoney = 0d;
        String mark = "";
        if(param.getPtype() == 1){
            mark = "系统增加了"+param.getMoney()+"余额";
            newMoney = NumberUtil.add(yxUser.getNowMoney(),param.getMoney()).doubleValue();
            billService.income(yxUser.getUid(),"系统增加余额", BillDetailEnum.CATEGORY_1.getValue(),
                    BillDetailEnum.TYPE_6.getValue(),param.getMoney(),newMoney, mark,"");
        }else{
            mark = "系统扣除了"+param.getMoney()+"余额";
            newMoney = NumberUtil.sub(yxUser.getNowMoney(),param.getMoney()).doubleValue();
            if(newMoney < 0) {
                newMoney = 0d;
            }
            billService.expend(yxUser.getUid(), "系统减少余额",
                    BillDetailEnum.CATEGORY_1.getValue(),
                    BillDetailEnum.TYPE_7.getValue(),
                    param.getMoney(), newMoney, mark);
        }
//        YxUser user = new YxUser();
//        user.setUid(yxUser.getUid());
        yxUser.setNowMoney(BigDecimal.valueOf(newMoney));
        saveOrUpdate(yxUser);
    }

}
