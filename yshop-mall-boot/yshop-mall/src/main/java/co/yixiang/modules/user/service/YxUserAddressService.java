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
import co.yixiang.modules.user.domain.YxUserAddress;
import co.yixiang.modules.user.param.AddressParam;
import co.yixiang.modules.user.vo.YxUserAddressQueryVo;

import java.util.List;

/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-28
 */
public interface YxUserAddressService extends BaseService<YxUserAddress> {

    /**
     * 设置默认地址
     * @param uid uid
     * @param addressId 地址id
     */
    void setDefault(Long uid,Long addressId);

    /**
     * 添加或者修改地址
     * @param uid uid
     * @param param AddressParam
     */
    Long addAndEdit(Long uid, AddressParam param);

    /**
     * 地址详情
     * @param id 地址id
     * @return YxUserAddressQueryVo
     */
    YxUserAddressQueryVo getDetail(Long id);

    /**
     * 获取用户地址
     * @param uid uid
     * @param page page
     * @param limit limit
     * @return List
     */
    List<YxUserAddressQueryVo> getList(Long uid,int page,int limit);

    /**
     * 获取默认地址
     * @param uid uid
     * @return YxUserAddress
     */
    YxUserAddress getUserDefaultAddress(Long uid);

}
