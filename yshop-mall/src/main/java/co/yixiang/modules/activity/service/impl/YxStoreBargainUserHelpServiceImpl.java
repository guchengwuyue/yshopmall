/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.activity.domain.YxStoreBargainUser;
import co.yixiang.modules.activity.domain.YxStoreBargainUserHelp;
import co.yixiang.modules.activity.service.YxStoreBargainUserHelpService;
import co.yixiang.modules.activity.service.YxStoreBargainUserService;
import co.yixiang.modules.activity.service.mapper.YxStoreBargainUserHelpMapper;
import co.yixiang.modules.activity.vo.YxStoreBargainUserHelpQueryVo;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 砍价用户帮助表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YxStoreBargainUserHelpServiceImpl extends BaseServiceImpl<YxStoreBargainUserHelpMapper, YxStoreBargainUserHelp> implements YxStoreBargainUserHelpService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private YxStoreBargainUserHelpMapper yxStoreBargainUserHelpMapper;

    @Autowired
    private YxStoreBargainUserService storeBargainUserService;
    @Autowired
    private YxUserService userService;




    /**
     * 获取砍价帮
     * @param bargainId 砍价商品id
     * @param bargainUserUid 砍价用户id
     * @param page page
     * @param limit limit
     * @return list
     */
    @Override
    public List<YxStoreBargainUserHelpQueryVo> getList(Long bargainId, Long bargainUserUid,
                                                       int page, int limit) {
        YxStoreBargainUser storeBargainUser = storeBargainUserService
                .getBargainUserInfo(bargainId,bargainUserUid);
        if(ObjectUtil.isNull(storeBargainUser)) {
            return Collections.emptyList();
        }
        Page<YxStoreBargainUserHelp> pageModel = new Page<>(page, limit);
        LambdaQueryWrapper<YxStoreBargainUserHelp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreBargainUserHelp::getBargainUserId,storeBargainUser.getId())
                .orderByDesc(YxStoreBargainUserHelp::getId);
        List<YxStoreBargainUserHelpQueryVo> storeBargainUserHelpQueryVos = generator
                .convert(yxStoreBargainUserHelpMapper.selectPage(pageModel,wrapper).getRecords(),
                        YxStoreBargainUserHelpQueryVo.class);

        storeBargainUserHelpQueryVos.forEach(item->{
            YxUser yxUser = userService.getById(item.getUid());
            item.setAvatar(yxUser.getAvatar());
            item.setNickname(yxUser.getNickname());
        });

        return storeBargainUserHelpQueryVos;
    }

    /**
     * 获取砍价帮总人数
     *
     * @param bargainId      砍价产品ID
     * @param bargainUserUid 用户参与砍价表id
     * @return int
     */
    @Override
    public Long getBargainUserHelpPeopleCount(Long bargainId, Long bargainUserUid) {
        return this.lambdaQuery()
                .eq(YxStoreBargainUserHelp::getBargainUserId,bargainUserUid)
                .eq(YxStoreBargainUserHelp::getBargainId,bargainId)
                .count();
    }





}
