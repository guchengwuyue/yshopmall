/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.domain.YxUserLevel;
import co.yixiang.modules.user.service.YxSystemUserLevelService;
import co.yixiang.modules.user.service.YxSystemUserTaskService;
import co.yixiang.modules.user.service.YxUserLevelService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.mapper.SystemUserTaskMapper;
import co.yixiang.modules.user.service.mapper.YxUserLevelMapper;
import co.yixiang.utils.OrderUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 用户等级记录表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YxUserLevelServiceImpl extends BaseServiceImpl<YxUserLevelMapper, YxUserLevel> implements YxUserLevelService {

    @Autowired
    private YxUserLevelMapper yxUserLevelMapper;
    @Autowired
    private SystemUserTaskMapper yxSystemUserTaskMapper;

    @Autowired
    private YxUserService userService;
    @Autowired
    private YxSystemUserLevelService systemUserLevelService;
    @Autowired
    private YxSystemUserTaskService systemUserTaskService;


    /**
     * 检查是否能成为会员
     * @param uid 用户id
     */
    @Override
    public boolean setLevelComplete(Long uid) {
        //获取当前用户级别
        int levelId = 0;
        YxUserLevel yxUserLevel = this.getUserLevel(uid,null);
        if(yxUserLevel != null ){
            levelId =  yxUserLevel.getLevelId();
        }
        int nextLevelId = systemUserLevelService.getNextLevelId(levelId);
        if(nextLevelId == 0) {
            return false;
        }

        Long finishCount = systemUserTaskService.getTaskComplete(nextLevelId,uid);

        //目前任务固定，如果增加任务需要自己增加逻辑，目前每个会员任务固定3
        if(finishCount == ShopConstants.TASK_FINISH_COUNT){
            this.setUserLevel(uid,nextLevelId);
            return true;
        }
        return false;
    }



    /**
     * 获取当前用户会员等级返回当前用户等级
     * @param uid uid
     * @param grade 用户级别
     * @return YxUserLevel
     */
    @Override
    public YxUserLevel getUserLevel(Long uid, Integer grade) {
       LambdaQueryWrapper<YxUserLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(YxUserLevel::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxUserLevel::getUid,uid)
                .orderByDesc(YxUserLevel::getGrade);
        if(grade != null) {
            wrapper.lt(YxUserLevel::getGrade,grade);
        }
        YxUserLevel userLevel = this.getOne(wrapper,false);
        if(ObjectUtil.isNull(userLevel)) {
            return null;
        }
        if(ShopCommonEnum.IS_FOREVER_1.getValue().equals(userLevel.getIsForever())) {
            return userLevel;
        }
        int nowTime = OrderUtil.getSecondTimestampTwo();
        if(nowTime > userLevel.getValidTime()){
            if(ShopCommonEnum.IS_STATUS_1.getValue().equals(userLevel.getStatus())){
                userLevel.setStatus(ShopCommonEnum.IS_STATUS_0.getValue());
                yxUserLevelMapper.updateById(userLevel);
            }
            return this.getUserLevel(uid,userLevel.getGrade());
        }
        return userLevel;
    }


    /**
     * 设置会员等级
     * @param uid 用户id
     * @param levelId 等级id
     */
    private void setUserLevel(Long uid, int levelId){
        YxSystemUserLevel systemUserLevelQueryVo = systemUserLevelService
                .getById(levelId);
        if(ObjectUtil.isNull(systemUserLevelQueryVo)) {
            return;
        }

        int validTime = systemUserLevelQueryVo.getValidDate() * 86400;

       LambdaQueryWrapper<YxUserLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxUserLevel::getStatus,ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxUserLevel::getUid,uid)
                .eq(YxUserLevel::getLevelId,levelId)
                .last("limit 1");

        YxUserLevel yxUserLevel = new YxUserLevel();
        yxUserLevel.setIsForever(systemUserLevelQueryVo.getIsForever());
        yxUserLevel.setStatus(ShopCommonEnum.IS_STATUS_1.getValue());
        yxUserLevel.setGrade(systemUserLevelQueryVo.getGrade());
        yxUserLevel.setUid(uid);
        yxUserLevel.setLevelId(levelId);
        yxUserLevel.setDiscount(systemUserLevelQueryVo.getDiscount().intValue());

        if(ShopCommonEnum.IS_FOREVER_1.getValue().equals(systemUserLevelQueryVo.getIsForever())){
            yxUserLevel.setValidTime(0); //永久
        }else{
            yxUserLevel.setValidTime(validTime+OrderUtil.getSecondTimestampTwo());
        }

        yxUserLevel.setMark("恭喜你成为了"+systemUserLevelQueryVo.getName());
        yxUserLevelMapper.insert(yxUserLevel);

        //更新用户等级
        YxUser yxUser = new YxUser();
        yxUser.setLevel(levelId);
        yxUser.setUid(uid);
        userService.updateById(yxUser);

    }



    //    @Override
//    public UserLevelInfoDto getUserLevelInfo(int id) {
//        return yxUserLevelMapper.getUserLevelInfo(id);
//    }



}
