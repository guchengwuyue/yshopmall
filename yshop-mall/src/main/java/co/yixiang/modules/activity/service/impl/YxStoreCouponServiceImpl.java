/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.StrUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponMapper;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponServiceImpl extends BaseServiceImpl<YxStoreCouponMapper, YxStoreCoupon> implements YxStoreCouponService {

    private final IGenerator generator;
    private final YxStoreProductService storeProductService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCouponQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCoupon> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<YxStoreCouponDto> storeCouponDtos = generator.convert(page.getList(), YxStoreCouponDto.class);
        for (YxStoreCouponDto storeCouponDto : storeCouponDtos) {
            if(StrUtil.isNotBlank(storeCouponDto.getProductId())){
                List<YxStoreProduct> storeProducts = storeProductService.lambdaQuery()
                        .in(YxStoreProduct::getId, Arrays.asList(storeCouponDto.getProductId().split(",")))
                        .list();
                storeCouponDto.setProduct(storeProducts);
            }
        }
        map.put("content", storeCouponDtos);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCoupon> queryAll(YxStoreCouponQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCoupon.class, criteria));
    }


    @Override
    public void download(List<YxStoreCouponDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCouponDto yxStoreCoupon : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("优惠券名称", yxStoreCoupon.getTitle());
            map.put("兑换消耗积分值", yxStoreCoupon.getIntegral());
            map.put("兑换的优惠券面值", yxStoreCoupon.getCouponPrice());
            map.put("最低消费多少金额可用优惠券", yxStoreCoupon.getUseMinPrice());
            map.put("优惠券有效期限（单位：天）", yxStoreCoupon.getCouponTime());
            map.put("排序", yxStoreCoupon.getSort());
            map.put("状态（0：关闭，1：开启）", yxStoreCoupon.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
