/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.service;


import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxSystemAttachment;

/**
 * <p>
 * 附件管理表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-11-11
 */
public interface YxSystemAttachmentService extends BaseService<YxSystemAttachment> {

    /**
     *  根据名称获取
     * @param name name
     * @return YxSystemAttachment
     */
    YxSystemAttachment getInfo(String name);

    /**
     *  根据code获取
     * @param code code
     * @return YxSystemAttachment
     */
    YxSystemAttachment getByCode(String code);

    /**
     * 添加附件记录
     * @param name 名称
     * @param attSize 附件大小
     * @param attDir 路径
     * @param sattDir 路径
     */
    void attachmentAdd(String name,String attSize,String attDir,String sattDir);

    /**
     * 添加附件记录
     * @param name 名称
     * @param attSize 附件大小
     * @param attDir 路径
     * @param sattDir 路径
     * @param uid 用户id
     * @param code 邀请码
     */
    void newAttachmentAdd(String name,String attSize,String attDir,String sattDir,Long uid,String code);


}
