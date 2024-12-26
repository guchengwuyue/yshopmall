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
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiCode;
import co.yixiang.api.UnAuthenticatedException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.Brokerage;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.order.service.YxStoreOrderCartInfoService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.mapper.StoreOrderMapper;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.domain.YxUserLevel;
import co.yixiang.modules.user.service.YxSystemUserLevelService;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserLevelService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.PromUserDto;
import co.yixiang.modules.user.service.dto.UserMoneyDto;
import co.yixiang.modules.user.service.dto.YxUserDto;
import co.yixiang.modules.user.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.user.service.mapper.UserBillMapper;
import co.yixiang.modules.user.service.mapper.UserMapper;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import co.yixiang.utils.FileUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @Autowired
    private YxSystemUserLevelService systemUserLevelService;
    @Autowired
    private YxUserLevelService userLevelService;
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
     * 获取我的分销下人员列表
     * @param uid uid
     * @param page page
     * @param limit limit
     * @param grade ShopCommonEnum.GRADE_0
     * @param keyword 关键字搜索
     * @param sort 排序
     * @return list
     */
    @Override
    public List<PromUserDto> getUserSpreadGrade(Long uid, int page, int limit, Integer grade,
                                                String keyword, String sort) {
        List<YxUser> userList = yxUserMapper.selectList(Wrappers.<YxUser>lambdaQuery()
                .eq(YxUser::getSpreadUid, uid));
        List<Long> userIds = userList.stream()
                .map(YxUser::getUid)
                .collect(Collectors.toList());

        List<PromUserDto> list = new ArrayList<>();
        if (userIds.isEmpty()) {
            return list;
        }

        if (StrUtil.isBlank(sort)) {
            sort = "u.uid desc";
        }

        Page<YxUser> pageModel = new Page<>(page, limit);
        if (ShopCommonEnum.GRADE_0.getValue().equals(grade)) {//-级
            list = yxUserMapper.getUserSpreadCountList(pageModel, userIds,
                    keyword, sort);
        } else {//二级
            List<YxUser> userListT = yxUserMapper.selectList(Wrappers.<YxUser>lambdaQuery()
                    .in(YxUser::getSpreadUid, userIds));
            List<Long> userIdsT = userListT.stream()
                    .map(YxUser::getUid)
                    .collect(Collectors.toList());
            if (userIdsT.isEmpty()) {
                return list;
            }
            list = yxUserMapper.getUserSpreadCountList(pageModel, userIdsT,
                    keyword, sort);

        }
        return list;
    }

    /**
     * 统计分销人员
     *
     * @param uid uid
     * @return map
     */
    @Override
    public Map<String, Long> getSpreadCount(Long uid) {
        Long countOne = yxUserMapper.selectCount(Wrappers.<YxUser>lambdaQuery()
                .eq(YxUser::getSpreadUid,uid));

        Long countTwo = 0L;
        List<YxUser> userList = yxUserMapper.selectList((Wrappers.<YxUser>lambdaQuery()
                .eq(YxUser::getSpreadUid,uid)));
        List<Long> userIds = userList.stream().map(YxUser::getUid)
                .collect(Collectors.toList());
        if(!userIds.isEmpty()){
            countTwo = yxUserMapper.selectCount(Wrappers.<YxUser>lambdaQuery()
                    .in(YxUser::getSpreadUid,userIds));
        }

        Map<String,Long> map = new LinkedHashMap<>(2);
        map.put("first",countOne); //一级
        map.put("second",countTwo);//二级

        return map;
    }

    /**
     * 一级返佣
     * @param order 订单
     */
    @Override
    public void backOrderBrokerage(YxStoreOrderQueryVo order) {
        //如果分销没开启直接返回
        String open = systemConfigService.getData(SystemConfigConstants.STORE_BROKERAGE_OPEN);
        if(StrUtil.isBlank(open) || ShopCommonEnum.ENABLE_2.getValue().toString().equals(open)) {
            return;
        }


        //获取购买商品的用户
        YxUser userInfo =  this.getById(order.getUid());
        System.out.println("userInfo:"+userInfo);
        //当前用户不存在 没有上级  直接返回
        if(ObjectUtil.isNull(userInfo) || userInfo.getSpreadUid() == 0) {
            return;
        }


        YxUser preUser = this.getById(userInfo.getSpreadUid());

        //一级返佣金额
        BigDecimal brokeragePrice = this.computeProductBrokerage(order, Brokerage.LEVEL_1);

        //返佣金额小于等于0 直接返回不返佣金

        if(brokeragePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        //计算上级推广员返佣之后的金额
        double balance = NumberUtil.add(preUser.getBrokeragePrice(),brokeragePrice).doubleValue();
        String mark = userInfo.getNickname()+"成功消费"+order.getPayPrice()+"元,奖励推广佣金"+
                brokeragePrice;
        //增加流水
        billService.income(userInfo.getSpreadUid(),"获得推广佣金",BillDetailEnum.CATEGORY_1.getValue(),
                BillDetailEnum.TYPE_2.getValue(),brokeragePrice.doubleValue(),balance, mark,order.getId().toString());

        //添加用户余额
        yxUserMapper.incBrokeragePrice(brokeragePrice, userInfo.getSpreadUid());

        //一级返佣成功 跳转二级返佣
        this.backOrderBrokerageTwo(order);

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

        this.setUserSpreadCount(yxUser);
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
       LambdaQueryWrapper<YxUserLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxUserLevel::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxUserLevel::getUid,uid)
                .orderByDesc(YxUserLevel::getGrade)
                .last("limit 1");
        YxUserLevel userLevel = userLevelService.getOne(wrapper);
        YxSystemUserLevel systemUserLevel = new YxSystemUserLevel();
        if(ObjectUtil.isNotNull(userLevel)) {
            systemUserLevel=  systemUserLevelService.getById(userLevel.getLevelId());
        }
        int discount = 100;
        if(ObjectUtil.isNotNull(userLevel)) {
            discount = systemUserLevel.getDiscount().intValue();
        }
        return NumberUtil.mul(NumberUtil.div(discount,100),price);
    }


    /**
     * 设置推广关系
     * @param spread 上级人
     * @param uid 本人
     */
    @Override
    public void setSpread(String spread, long uid) {
        if(StrUtil.isBlank(spread) || !NumberUtil.isNumber(spread)) {
            return;
        }

        //如果分销没开启直接返回
        String open = systemConfigService.getData(SystemConfigConstants.STORE_BROKERAGE_OPEN);
        if(StrUtil.isBlank(open) || ShopCommonEnum.ENABLE_2.getValue().toString().equals(open)) {
            return;
        }
        //当前用户信息
        YxUser userInfo = this.getById(uid);
        if(ObjectUtil.isNull(userInfo)) {
            return;
        }

        //当前用户有上级直接返回
        if(userInfo.getSpreadUid() != null && userInfo.getSpreadUid() > 0) {
            return;
        }
        //没有推广编号直接返回
        long spreadInt = Long.valueOf(spread);
        if(spreadInt == 0) {
            return;
        }
        if(spreadInt == uid) {
            return;
        }

        //不能互相成为上下级
        YxUser userInfoT = this.getById(spreadInt);
        if(ObjectUtil.isNull(userInfoT)) {
            return;
        }

        if(userInfoT.getSpreadUid() == uid) {
            return;
        }

        YxUser yxUser = YxUser.builder()
                .spreadUid(spreadInt)
                .spreadTime(new Date())
                .uid(uid)
                .build();

        yxUserMapper.updateById(yxUser);

    }


    /**
     * 二级返佣
     * @param order 订单
     */
    private void backOrderBrokerageTwo(YxStoreOrderQueryVo order) {

        YxUser userInfo =  this.getById(order.getUid());

        //获取上推广人
        YxUser userInfoTwo = this.getById(userInfo.getSpreadUid());

        //上推广人不存在 或者 上推广人没有上级    直接返回
        if(ObjectUtil.isNull(userInfoTwo) || userInfoTwo.getSpreadUid() == 0) {
            return;
        }


        //指定分销 判断 上上级是否时推广员  如果不是推广员直接返回
        YxUser preUser = this.getById(userInfoTwo.getSpreadUid());


        //二级返佣金额
        BigDecimal brokeragePrice = this.computeProductBrokerage(order,Brokerage.LEVEL_2);

        //返佣金额小于等于0 直接返回不返佣金
        if(brokeragePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        //获取上上级推广员信息
        double balance = NumberUtil.add(preUser.getBrokeragePrice(),brokeragePrice).doubleValue();
        String mark = "二级推广人"+userInfo.getNickname()+"成功消费"+order.getPayPrice()+"元,奖励推广佣金"+
                brokeragePrice;

        //增加流水
        billService.income(userInfoTwo.getSpreadUid(),"获得推广佣金",BillDetailEnum.CATEGORY_1.getValue(),
                BillDetailEnum.TYPE_2.getValue(),brokeragePrice.doubleValue(),balance, mark,order.getId().toString());
        //添加用户余额
        yxUserMapper.incBrokeragePrice(brokeragePrice,
                userInfoTwo.getSpreadUid());
    }


    /**
     * 计算获取返佣金额
     * @param order 订单信息
     * @param level 分销级别
     * @return double
     */
    private BigDecimal computeProductBrokerage(YxStoreOrderQueryVo order , Brokerage level){
        List<YxStoreOrderCartInfo> storeOrderCartInfoList = storeOrderCartInfoService
                .list(Wrappers.<YxStoreOrderCartInfo>lambdaQuery()
                        .in(YxStoreOrderCartInfo::getCartId, Arrays.asList(order.getCartId().split(","))));
        BigDecimal oneBrokerage = BigDecimal.ZERO;//一级返佣金额
        BigDecimal twoBrokerage = BigDecimal.ZERO;//二级返佣金额

        List<String> cartInfos = storeOrderCartInfoList.stream()
                .map(YxStoreOrderCartInfo::getCartInfo)
                .collect(Collectors.toList());

        for (String cartInfo : cartInfos){
            YxStoreCartQueryVo cart = JSON.parseObject(cartInfo,YxStoreCartQueryVo.class);

            YxStoreProductQueryVo storeProductVO = cart.getProductInfo();
            //产品是否单独分销
            if(ShopCommonEnum.IS_SUB_1.getValue().equals(storeProductVO.getIsSub())){
                oneBrokerage = NumberUtil.round(NumberUtil.add(oneBrokerage,
                        NumberUtil.mul(cart.getCartNum(),storeProductVO.getAttrInfo().getBrokerage()))
                        ,2);

                twoBrokerage = NumberUtil.round(NumberUtil.add(twoBrokerage,
                        NumberUtil.mul(cart.getCartNum(),storeProductVO.getAttrInfo().getBrokerageTwo()))
                        ,2);
            }

        }

        //获取后台一级返佣比例
        String storeBrokerageRatioStr = systemConfigService.getData(SystemConfigConstants.STORE_BROKERAGE_RATIO);
        String storeBrokerageTwoStr = systemConfigService.getData(SystemConfigConstants.STORE_BROKERAGE_TWO);


        //一级返佣比例未设置直接返回
        if(StrUtil.isBlank(storeBrokerageRatioStr)
                || !NumberUtil.isNumber(storeBrokerageRatioStr)){
            return oneBrokerage;
        }
        //二级返佣比例未设置直接返回
        if(StrUtil.isBlank(storeBrokerageTwoStr)
                || !NumberUtil.isNumber(storeBrokerageTwoStr)){
            return twoBrokerage;
        }


        switch (level){
            case LEVEL_1:
                //根据订单获取一级返佣比例
                BigDecimal storeBrokerageRatio = NumberUtil.round(NumberUtil.div(storeBrokerageRatioStr,"100"),2);
                BigDecimal brokeragePrice = NumberUtil
                        .round(NumberUtil.mul(order.getTotalPrice(),storeBrokerageRatio),2);
                //固定返佣 + 比例返佣 = 总返佣金额
                return NumberUtil.add(oneBrokerage,brokeragePrice);
            case LEVEL_2:
                //根据订单获取一级返佣比例
                BigDecimal storeBrokerageTwo = NumberUtil.round(NumberUtil.div(storeBrokerageTwoStr,"100"),2);
                BigDecimal storeBrokerageTwoPrice = NumberUtil
                        .round(NumberUtil.mul(order.getTotalPrice(),storeBrokerageTwo),2);
                //固定返佣 + 比例返佣 = 总返佣金额
                return NumberUtil.add(twoBrokerage,storeBrokerageTwoPrice);
            default:
        }


        return BigDecimal.ZERO;

    }



    /**
     * 更新下级人数
     * @param yxUser user
     */
    private void setUserSpreadCount(YxUser yxUser) {
        Long count = yxUserMapper.selectCount(Wrappers.<YxUser>lambdaQuery()
                .eq(YxUser::getSpreadUid,yxUser.getUid()));
        yxUser.setSpreadCount(count);
        yxUserMapper.updateById(yxUser);
    }


    //===========后面管理后台部分=====================//


    /**
     * 查看下级
     * @param uid uid
     * @param grade 等级
     * @return list
     */
    @Override
    public List<PromUserDto> querySpread(Long uid, Integer grade) {
        return this.getUserSpreadGrade(uid,1, 999,grade,"","");
    }


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

    /**
     * 增加佣金
     * @param price 金额
     * @param uid 用户id
     */
    @Override
    public void incBrokeragePrice(BigDecimal price, Long uid) {
        yxUserMapper.incBrokeragePrice(price,uid);
    }
}
