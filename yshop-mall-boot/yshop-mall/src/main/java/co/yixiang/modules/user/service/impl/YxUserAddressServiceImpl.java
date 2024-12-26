/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.user.domain.YxUserAddress;
import co.yixiang.modules.user.param.AddressParam;
import co.yixiang.modules.user.service.YxUserAddressService;
import co.yixiang.modules.user.service.mapper.YxUserAddressMapper;
import co.yixiang.modules.user.vo.YxUserAddressQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-28
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class YxUserAddressServiceImpl extends BaseServiceImpl<YxUserAddressMapper, YxUserAddress> implements YxUserAddressService {

    private final YxUserAddressMapper yxUserAddressMapper;
    private final IGenerator generator;

    /**
     * 设置默认地址
     * @param uid uid
     * @param addressId 地址id
     */
    @Override
    public void setDefault(Long uid,Long addressId){
        YxUserAddress address = new YxUserAddress();
        address.setIsDefault(ShopCommonEnum.DEFAULT_0.getValue());
        yxUserAddressMapper.update(address,
                new LambdaQueryWrapper<YxUserAddress>().eq(YxUserAddress::getUid,uid));

        YxUserAddress userAddress = new YxUserAddress();
        userAddress.setIsDefault(ShopCommonEnum.DEFAULT_1.getValue());
        userAddress.setId(addressId);
        yxUserAddressMapper.updateById(userAddress);
    }


    /**
     * 添加或者修改地址
     * @param uid uid
     * @param param AddressParam
     */
    @Override
    public Long addAndEdit(Long uid, AddressParam param){
        YxUserAddress userAddress = YxUserAddress.builder()
                .city(param.getAddress().getCity())
                .cityId(param.getAddress().getCityId())
                .district(param.getAddress().getDistrict())
                .province(param.getAddress().getProvince())
                .detail(param.getDetail())
                .uid(uid)
                .phone(param.getPhone())
                .postCode(param.getPost_code())
                .realName(param.getReal_name())
                .build();
        if("true".equals(param.getIs_default())){
            userAddress.setIsDefault(ShopCommonEnum.DEFAULT_1.getValue());
            //新增地址如果是默认，把之前的状态改掉
            YxUserAddress address = new YxUserAddress();
            address.setIsDefault(ShopCommonEnum.DEFAULT_0.getValue());
            baseMapper.update(address,new LambdaQueryWrapper<YxUserAddress>().eq(YxUserAddress::getUid,uid));
        }else{
            userAddress.setIsDefault(ShopCommonEnum.DEFAULT_0.getValue());
        }
        if(StrUtil.isBlank(param.getId())){
            this.save(userAddress);
        }else{
            userAddress.setId(Long.valueOf(param.getId()));
            this.updateById(userAddress);
        }

        return userAddress.getId();
    }

    /**
     * 地址详情
     * @param id 地址id
     * @return YxUserAddressQueryVo
     */
    @Override
    public YxUserAddressQueryVo getDetail(Long id){
        return generator.convert(this.getById(id),YxUserAddressQueryVo.class);
    }


    /**
     * 获取用户地址
     * @param uid uid
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<YxUserAddressQueryVo> getList(Long uid,int page,int limit){
        Page<YxUserAddress> pageModel = new Page<>(page, limit);
        IPage<YxUserAddress> pageList = this.lambdaQuery().eq(YxUserAddress::getUid,uid).page(pageModel);
        return generator.convert(pageList.getRecords(),YxUserAddressQueryVo.class);
    }

    /**
     * 获取默认地址
     * @param uid uid
     * @return YxUserAddress
     */
    @Override
    public YxUserAddress getUserDefaultAddress(Long uid) {
        LambdaQueryWrapper<YxUserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxUserAddress::getIsDefault,1).
                eq(YxUserAddress::getUid,uid)
                .last("limit 1");
        return getOne(wrapper);
    }



}
