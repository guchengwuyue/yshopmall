/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.domain.YxStoreBargainUser;
import co.yixiang.modules.activity.domain.YxStoreBargainUserHelp;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.YxStoreBargainUserHelpService;
import co.yixiang.modules.activity.service.YxStoreBargainUserService;
import co.yixiang.modules.activity.service.mapper.YxStoreBargainUserMapper;
import co.yixiang.modules.activity.vo.YxStoreBargainUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 用户参与砍价表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-21
 */
@Slf4j
@Service
public class YxStoreBargainUserServiceImpl extends BaseServiceImpl<YxStoreBargainUserMapper, YxStoreBargainUser> implements YxStoreBargainUserService {


    @Autowired
    private IGenerator generator;

    @Autowired
    private YxStoreBargainUserMapper yxStoreBargainUserMapper;

    @Autowired
    private YxStoreBargainService storeBargainService;
    @Autowired
    private YxStoreBargainUserHelpService storeBargainUserHelpService;


    /**
     * 修改用户砍价状态
     * @param bargainId 砍价产品id
     * @param uid 用户id
     */
    @Override
    public void setBargainUserStatus(Long bargainId, Long uid) {
        YxStoreBargainUser storeBargainUser = getBargainUserInfo(bargainId.longValue(),uid);
        if(ObjectUtil.isNull(storeBargainUser)) {
            return;
        }

        if(storeBargainUser.getStatus() != 1) {
            return;
        }
        double price = NumberUtil.sub(NumberUtil.sub(storeBargainUser.getBargainPrice(),
                storeBargainUser.getBargainPriceMin()),storeBargainUser.getPrice()).doubleValue();
        if(price > 0) {
            return;
        }

        storeBargainUser.setStatus(3);

        yxStoreBargainUserMapper.updateById(storeBargainUser);
    }

    /**
     * 砍价取消
     * @param bargainId 砍价商品id
     * @param uid uid
     */
    @Override
    public void bargainCancel(Long bargainId, Long uid) {
        YxStoreBargainUser storeBargainUser = this.getBargainUserInfo(bargainId,uid);
        if(ObjectUtil.isNull(storeBargainUser)) {
            throw new YshopException("数据不存在");
        }
        if(!OrderInfoEnum.BARGAIN_STATUS_1.getValue().equals(storeBargainUser.getStatus())){
            throw new YshopException("状态错误");
        }
        yxStoreBargainUserMapper.deleteById(storeBargainUser.getId());
    }

    /**
     * 获取用户的砍价产品
     * @param bargainUserUid 用户id
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<YxStoreBargainUserQueryVo> bargainUserList(Long bargainUserUid, int page, int limit) {
        Page<YxStoreBargainUser> pageModel = new Page<>(page, limit);
        return yxStoreBargainUserMapper.getBargainUserList(bargainUserUid,pageModel);
    }

    /**
     * 判断用户是否还可以砍价
     * @param bargainId 砍价产品id
     * @param bargainUserUid 开启砍价用户id
     * @param uid  当前用户id
     * @return false=NO true=YES
     */
    @Override
    public  boolean isBargainUserHelp(Long bargainId, Long bargainUserUid, Long uid) {
        YxStoreBargainUser storeBargainUser = this.getBargainUserInfo(bargainId, bargainUserUid);
        YxStoreBargain storeBargain = storeBargainService
                .getById(bargainId);
        if(ObjectUtil.isNull(storeBargainUser) || ObjectUtil.isNull(storeBargain)){
            return false;
        }
        Long count = storeBargainUserHelpService.lambdaQuery()
                .eq(YxStoreBargainUserHelp::getBargainId,bargainId)
                .eq(YxStoreBargainUserHelp::getBargainUserId,storeBargainUser.getId())
                .eq(YxStoreBargainUserHelp::getUid,uid)
                .count();
        if(count == 0) {
            return true;
        }
        return false;
    }

    /**
     * 添加砍价记录
     * @param bargainId 砍价商品id
     * @param uid 用户id
     */
    @Override
    public void setBargain(Long bargainId, Long uid) {
        YxStoreBargainUser storeBargainUser = this.getBargainUserInfo(bargainId,uid);
        if(storeBargainUser != null) {
            throw new YshopException("你已经参与了");
        }
        YxStoreBargain storeBargain = storeBargainService.getById(bargainId);
        if(storeBargain == null) {
            throw new YshopException("砍价商品不存在");
        }
        YxStoreBargainUser yxStoreBargainUser = YxStoreBargainUser
                .builder()
                .bargainId(bargainId)
                .uid(uid)
                .bargainPrice(storeBargain.getPrice())
                .bargainPriceMin(storeBargain.getMinPrice())
                .price(BigDecimal.ZERO)
                .status(OrderInfoEnum.BARGAIN_STATUS_1.getValue())
                .build();
        yxStoreBargainUserMapper.insert(yxStoreBargainUser);
    }

//    /**
//     * 获取用户可以砍掉的价格
//     * @param id
//     * @return
//     */
//    @Override
//    public double getBargainUserDiffPrice(int id) {
//        YxStoreBargainUser storeBargainUserQueryVo = this.getById(id);
//        return NumberUtil.sub(storeBargainUserQueryVo.getBargainPrice()
//                ,storeBargainUserQueryVo.getBargainPriceMin()).doubleValue();
//    }



    /**
     * 获取某个用户参与砍价信息
     * @param bargainId 砍价id
     * @param uid 用户id
     * @return  YxStoreBargainUser
     */
    @Override
    public YxStoreBargainUser getBargainUserInfo(Long bargainId, Long uid) {
       LambdaQueryWrapper<YxStoreBargainUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreBargainUser::getBargainId,bargainId)
                .eq(YxStoreBargainUser::getUid,uid)
                .last("limit 1");
        return yxStoreBargainUserMapper.selectOne(wrapper);
    }

    /**
     * 获取参与砍价的用户数量
     *
     * @param bargainId 砍价id
     * @param status    状态  OrderInfoEnum 1 进行中  2 结束失败  3结束成功
     * @return int
     */
    @Override
    public Long getBargainUserCount(Long bargainId, Integer status) {
        return this.lambdaQuery().eq(YxStoreBargainUser::getBargainId,bargainId)
                .eq(YxStoreBargainUser::getStatus,status).count();
    }


//
//    /**
//     * 获取参与砍价的用户列表
//     * @param bargainId 砍价id
//     * @param status  状态  1 进行中  2 结束失败  3结束成功
//     * @return
//     */
//    @Override
//    public List<YxStoreBargainUserQueryVo> getBargainUserList(int bargainId, int status) {
//       LambdaQueryWrapper<YxStoreBargainUser> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq("bargain_id",bargainId).eq("status",status);
//        return generator.convert(yxStoreBargainUserMapper.selectList(wrapper),
//                YxStoreBargainUserQueryVo.class);
//    }




}
