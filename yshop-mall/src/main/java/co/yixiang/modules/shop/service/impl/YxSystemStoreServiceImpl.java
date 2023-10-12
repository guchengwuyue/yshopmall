/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.product.vo.YxSystemStoreQueryVo;
import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import co.yixiang.modules.shop.service.mapper.SystemStoreMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.ShopKeyUtils;
import co.yixiang.utils.location.LocationUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemStoreServiceImpl extends BaseServiceImpl<SystemStoreMapper, YxSystemStore> implements YxSystemStoreService {

    private final IGenerator generator;
    private final SystemStoreMapper systemStoreMapper;

    @Override
    public YxSystemStoreQueryVo getYxSystemStoreById(int id){
        return generator.convert(this.getById(id),YxSystemStoreQueryVo.class);
    }

    /**
     * 获取门店列表
     * @param latitude 纬度
     * @param longitude 经度
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<YxSystemStoreQueryVo> getStoreList(String latitude, String longitude, int page, int limit) {
        Page<YxSystemStore> pageModel = new Page<>(page, limit);
        if(StrUtil.isBlank(latitude) || StrUtil.isBlank(longitude)){
            return generator.convert(this.page(pageModel).getRecords(),YxSystemStoreQueryVo.class);
        }
        List<YxSystemStoreQueryVo> list = systemStoreMapper.getStoreList(pageModel,Double.valueOf(longitude),Double.valueOf(latitude));
        list.forEach(item->{
            String newDis = NumberUtil.round(Double.valueOf(item.getDistance()) / 1000,2).toString();
            item.setDistance(newDis);
        });
        return list;
    }

    /**
     * 获取最新单个门店
     * @param latitude 纬度
     * @param longitude 经度
     * @return YxSystemStoreQueryVo
     */
    @Override
    public YxSystemStoreQueryVo getStoreInfo(String latitude,String longitude) {
        YxSystemStore yxSystemStore = systemStoreMapper.selectOne(
                Wrappers.<YxSystemStore>lambdaQuery()
                        .eq(YxSystemStore::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                        .orderByDesc(YxSystemStore::getId)
                        .last("limit 1"));
        if(yxSystemStore == null) {
            return null;
        }
        String mention = RedisUtil.get(ShopKeyUtils.getStoreSelfMention());
        if(StrUtil.isBlank(mention) || ShopCommonEnum.ENABLE_2.getValue().toString().equals(mention)) {
            return null;
        }
        YxSystemStoreQueryVo systemStoreQueryVo = generator.convert(yxSystemStore,YxSystemStoreQueryVo.class);
        if(StrUtil.isNotEmpty(latitude) && StrUtil.isNotEmpty(longitude)){
            double distance = LocationUtils.getDistance(Double.valueOf(latitude),Double.valueOf(longitude),
                    Double.valueOf(yxSystemStore.getLatitude()),Double.valueOf(yxSystemStore.getLongitude()));
            systemStoreQueryVo.setDistance(String.valueOf(distance));
        }
        return systemStoreQueryVo;
    }



    //===================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemStoreQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemStore> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxSystemStoreDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemStore> queryAll(YxSystemStoreQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemStore.class, criteria));
    }


    @Override
    public void download(List<YxSystemStoreDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemStoreDto yxSystemStore : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("门店名称", yxSystemStore.getName());
            map.put("简介", yxSystemStore.getIntroduction());
            map.put("手机号码", yxSystemStore.getPhone());
            map.put("省市区", yxSystemStore.getAddress());
            map.put("详细地址", yxSystemStore.getDetailedAddress());
            map.put("门店logo", yxSystemStore.getImage());
            map.put("纬度", yxSystemStore.getLatitude());
            map.put("经度", yxSystemStore.getLongitude());
            map.put("核销有效日期", yxSystemStore.getValidTime());
            map.put("每日营业开关时间", yxSystemStore.getDayTime());
            map.put("是否显示", yxSystemStore.getIsShow());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
