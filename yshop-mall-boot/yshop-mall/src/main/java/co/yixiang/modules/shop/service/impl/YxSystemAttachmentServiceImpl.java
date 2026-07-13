/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.service.impl;


import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.shop.domain.YxSystemAttachment;
import co.yixiang.modules.shop.service.YxSystemAttachmentService;
import co.yixiang.modules.shop.service.mapper.YxSystemAttachmentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 附件管理表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-11-11
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class YxSystemAttachmentServiceImpl extends BaseServiceImpl<YxSystemAttachmentMapper, YxSystemAttachment> implements YxSystemAttachmentService {

    private final YxSystemAttachmentMapper yxSystemAttachmentMapper;

    /**
     *  根据名称获取
     * @param name name
     * @return YxSystemAttachment
     */
    @Override
    public YxSystemAttachment getInfo(String name) {
       LambdaQueryWrapper<YxSystemAttachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxSystemAttachment::getName,name)
                .last("limit 1");
        return yxSystemAttachmentMapper.selectOne(wrapper);
    }

    /**
     *  根据code获取
     * @param code code
     * @return YxSystemAttachment
     */
    @Override
    public YxSystemAttachment getByCode(String code) {
       LambdaQueryWrapper<YxSystemAttachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxSystemAttachment::getInviteCode,code)
                .last("limit 1");
        return yxSystemAttachmentMapper.selectOne(wrapper);
    }

    /**
     * 添加附件记录
     * @param name 名称
     * @param attSize 附件大小
     * @param attDir 路径
     * @param sattDir 路径
     */
    @Override
    public void attachmentAdd(String name, String attSize, String attDir,String sattDir) {
        YxSystemAttachment attachment =  YxSystemAttachment.builder()
                .name(name)
                .attSize(attSize)
                .attDir(attDir)
                .attType("image/jpeg")
                .sattDir(sattDir)
                .build();
        yxSystemAttachmentMapper.insert(attachment);
    }

    /**
     * 添加附件记录
     * @param name 名称
     * @param attSize 附件大小
     * @param attDir 路径
     * @param sattDir 路径
     * @param uid 用户id
     * @param code 邀请码
     */
    @Override
    public void newAttachmentAdd(String name, String attSize, String attDir, String sattDir,
                                 Long uid, String code) {

        YxSystemAttachment attachment =  YxSystemAttachment.builder()
                .name(name)
                .attSize(attSize)
                .attDir(attDir)
                .attType("image/jpeg")
                .sattDir(sattDir)
                .uid(uid)
                .inviteCode(code)
                .build();
        yxSystemAttachmentMapper.insert(attachment);
    }



}
