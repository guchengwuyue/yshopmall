/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.service.YxStoreVisitService;
import co.yixiang.modules.activity.service.dto.YxStoreVisitDto;
import co.yixiang.modules.activity.service.dto.YxStoreVisitQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreVisitMapper;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreVisitServiceImpl extends BaseServiceImpl<YxStoreVisitMapper, YxStoreVisit> implements YxStoreVisitService {

    private final IGenerator generator;
    private final YxStoreProductService yxStoreProductService;
    private final YxStoreVisitMapper yxStoreVisitMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreVisitQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreVisit> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreVisitDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreVisit> queryAll(YxStoreVisitQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreVisit.class, criteria));
    }

    @Override
    public void download(List<YxStoreVisitDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreVisitDto yxStoreVisit : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("产品ID", yxStoreVisit.getProductId());
            map.put("产品类型", yxStoreVisit.getProductType());
            map.put("产品分类ID", yxStoreVisit.getCateId());
            map.put("产品类型", yxStoreVisit.getType());
            map.put("用户ID", yxStoreVisit.getUid());
            map.put("访问次数", yxStoreVisit.getCount());
            map.put("备注描述", yxStoreVisit.getContent());
            map.put("添加时间", yxStoreVisit.getAddTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 添加用户访问拼团记录
     * @param uid 用户id
     * @param productId 产品id
     */
    @Override
    public void addStoreVisit(Long uid, Long productId) {

        LambdaQueryWrapper<YxStoreVisit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreVisit::getUid, uid).eq(YxStoreVisit::getProductId, productId);
        YxStoreVisit storeVisit = this.baseMapper.selectOne(wrapper);

        if (ObjectUtil.isNull(storeVisit)) {
            //查询产品分类
            YxStoreProduct yxStoreProduct = yxStoreProductService.getProductInfo(productId);

            YxStoreVisit yxStoreVisit = YxStoreVisit.builder()
                    .productId(productId)
                    .productType(ProductTypeEnum.COMBINATION.getValue())
                    .cateId(Integer.valueOf(yxStoreProduct.getCateId()))
                    .type(ProductTypeEnum.COMBINATION.getValue())
                    .uid(uid)
                    .count(1)
                    .build();
            this.save(yxStoreVisit);
        }

    }
}
