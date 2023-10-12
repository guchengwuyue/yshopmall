/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.domain.YxUserBill;
import co.yixiang.modules.user.domain.YxUserSign;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserLevelService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.YxUserSignService;
import co.yixiang.modules.user.service.mapper.UserBillMapper;
import co.yixiang.modules.user.service.mapper.YxUserSignMapper;
import co.yixiang.modules.user.vo.SignVo;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 签到记录表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YxUserSignServiceImpl extends BaseServiceImpl<YxUserSignMapper, YxUserSign> implements YxUserSignService {

    @Autowired
    private YxUserSignMapper yxUserSignMapper;
    @Autowired
    private UserBillMapper userBillMapper;

    @Autowired
    private IGenerator generator;

    @Autowired
    private  YxSystemGroupDataService systemGroupDataService;
    @Autowired
    private YxUserService yxUserService;
    @Autowired
    private YxUserBillService billService;
    @Autowired
    private YxUserLevelService userLevelService;


    /**
     *
     * @param yxUser 用户
     * @return 签到积分
     */
    @Override
    public int sign(YxUser yxUser) {
        List<JSONObject> list = systemGroupDataService.getDatas(ShopConstants.YSHOP_SIGN_DAY_NUM);
        if(ObjectUtil.isNull(list) || list.isEmpty()) {
            throw new YshopException("请先配置签到天数");
        }

        boolean isDaySign = this.getToDayIsSign(yxUser.getUid());
        if(isDaySign) {
            throw new YshopException("已签到");
        }
        //积分
        int signNumber = 0;
        //签到次数
        int userSignNum = yxUser.getSignNum();
        if(getYesterDayIsSign(yxUser.getUid())){
            if(yxUser.getSignNum() > (list.size() - 1)){
                userSignNum = 0;
            }
        }else{
            userSignNum = 0;
        }
        int index = 0;
        for (Map<String,Object> map : list) {
            if(index == userSignNum){
                signNumber = Integer.parseInt(map.get("sign_num").toString());
                break;
            }
            index++;
        }

        userSignNum += 1;

        YxUserSign userSign = new YxUserSign();
        userSign.setUid(yxUser.getUid());
        String title = "签到奖励";
        if(userSignNum == list.size()){
            title = "连续签到奖励";
        }
        userSign.setTitle(title);
        userSign.setNumber(signNumber);
        userSign.setBalance(yxUser.getIntegral().intValue());
        yxUserSignMapper.insert(userSign);

        //用户积分增加
        YxUser user = YxUser.builder()
                .integral(NumberUtil.add(yxUser.getIntegral(),signNumber))
                .uid(yxUser.getUid())
                .signNum(userSignNum)
                .build();
        boolean res = yxUserService.updateById(user);
        if(!res) {
            throw new YshopException("签到失败");
        }

        //插入流水
        billService.income(yxUser.getUid(),title, BillDetailEnum.CATEGORY_2.getValue(),
                BillDetailEnum.TYPE_10.getValue(),signNumber,yxUser.getIntegral().doubleValue(),
                "","");

        //检查是否符合会员升级条件
        userLevelService.setLevelComplete(yxUser.getUid());
        return signNumber;
    }

    /**
     * 分页获取用户签到数据
     * @param uid 用户id
     * @param page  page
     * @param limit limit
     * @return list
     */
    @Override
    public List<SignVo> getSignList(Long uid, int page, int limit) {
        Page<YxUserBill> pageModel = new Page<>(page, limit);
        return userBillMapper.getSignList(uid,pageModel);
    }


    /**
     * 获取签到用户信息
     * @param yxUser  yxUser
     * @return YxUserQueryVo
     */
    @Override
    public YxUserQueryVo userSignInfo(YxUser yxUser) {
        YxUserQueryVo userQueryVo = generator.convert(yxUser,YxUserQueryVo.class);
        Long uid = yxUser.getUid();
        Long sumSignDay = this.getSignSumDay(uid);
        boolean isDaySign = this.getToDayIsSign(uid);
        boolean isYesterDaySign = this.getYesterDayIsSign(uid);
        userQueryVo.setSumSignDay(sumSignDay);
        userQueryVo.setIsDaySign(isDaySign);
        userQueryVo.setIsYesterDaySign(isYesterDaySign);
        if(!isDaySign && !isYesterDaySign) {
            userQueryVo.setSignNum(0);
        }
        return userQueryVo;
    }

    /**
     * 获取用户今天是否签到
     * @param uid uid
     * @return boolean true=YES false=NO
     */
    private boolean getToDayIsSign(Long uid) {
        Date today = DateUtil.beginOfDay(new Date());
        Long count = this.lambdaQuery().eq(YxUserSign::getUid,uid)
                .ge(YxUserSign::getCreateTime,today)
                .count();
        if(count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户昨天是否签到
     * @param uid uid
     * @return boolean
     */
    private boolean getYesterDayIsSign(Long uid) {
        Date today = DateUtil.beginOfDay(new Date());
        Date yesterday = DateUtil.beginOfDay(DateUtil.yesterday());

        Long count = this.lambdaQuery().eq(YxUserSign::getUid,uid)
                .lt(YxUserSign::getCreateTime,today)
                .ge(YxUserSign::getCreateTime,yesterday)
                .count();
        if(count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户累计签到次数
     * @param uid 用户id
     * @return int
     */
    private Long getSignSumDay(Long uid) {
        return this.lambdaQuery().eq(YxUserSign::getUid,uid).count();
    }


}
