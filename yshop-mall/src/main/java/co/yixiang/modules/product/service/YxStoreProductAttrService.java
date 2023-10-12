/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.product.domain.YxStoreProductAttr;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;

import java.util.List;
import java.util.Map;


/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxStoreProductAttrService  extends BaseService<YxStoreProductAttr>{

    /**
     * 增加库存减去销量
     * @param num 数量
     * @param productId 商品id
     * @param unique sku唯一值
     */
    void incProductAttrStock(Integer num, Long productId, String unique,String type);

    /**
     * 减少库存增加销量
     * @param num 数量
     * @param productId 商品id
     * @param unique sku唯一值
     */
    void decProductAttrStock(int num, Long productId, String unique,String type);


    /**
     * 更加sku 唯一值获取sku对象
     * @param unique 唯一值
     * @return YxStoreProductAttrValue
     */
    YxStoreProductAttrValue uniqueByAttrInfo(String unique);

    /**
     * 获取商品sku属性
     * @param productId 商品id
     * @return map
     */
    Map<String, Object> getProductAttrDetail(long productId);

    /**
     * 新增商品属性
     * @param items attr
     * @param attrs value
     * @param productId 商品id
     */
    void insertYxStoreProductAttr(List<FromatDetailDto> items, List<ProductFormatDto> attrs,
                                  Long productId);
}
