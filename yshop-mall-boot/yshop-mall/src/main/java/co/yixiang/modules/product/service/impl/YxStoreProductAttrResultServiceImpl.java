/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.product.domain.YxStoreProductAttrResult;
import co.yixiang.modules.product.service.YxStoreProductAttrResultService;
import co.yixiang.modules.product.service.mapper.StoreProductAttrResultMapper;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreProductAttrResultServiceImpl extends BaseServiceImpl<StoreProductAttrResultMapper, YxStoreProductAttrResult> implements YxStoreProductAttrResultService {

    /**
     * 新增商品属性详情
     * @param map map
     * @param productId 商品id
     */
    @Override
    public void insertYxStoreProductAttrResult(Map<String, Object> map, Long productId)
    {
        YxStoreProductAttrResult yxStoreProductAttrResult = new YxStoreProductAttrResult();
        yxStoreProductAttrResult.setProductId(productId);
        yxStoreProductAttrResult.setResult(JSON.toJSONString(map));
        yxStoreProductAttrResult.setChangeTime(new Date());

        long count = this.count(Wrappers.<YxStoreProductAttrResult>lambdaQuery()
                .eq(YxStoreProductAttrResult::getProductId,productId));
        if(count > 0) {
            this.remove(Wrappers.<YxStoreProductAttrResult>lambdaQuery()
                    .eq(YxStoreProductAttrResult::getProductId,productId));
        }

        this.save(yxStoreProductAttrResult);
    }


}
