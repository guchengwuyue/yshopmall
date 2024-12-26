/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface StoreProductAttrValueMapper extends CoreMapper<YxStoreProductAttrValue> {


    /**
     * 正常商品 加库存 减销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */
    @Update("update yx_store_product_attr_value set stock=stock+#{num}, sales=sales-#{num}" +
            " where product_id=#{productId} and `unique`=#{unique}")
    int incStockDecSales(@Param("num") Integer num,@Param("productId") Long productId,
                                    @Param("unique")  String unique);


    /**
     * 拼团商品 加库存 减销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */

    @Update("update yx_store_product_attr_value set stock=stock+#{num}, pink_stock=pink_stock+#{num}, sales=sales-#{num}" +
            " where product_id=#{productId} and `unique`=#{unique}")
    int incCombinationStockDecSales(@Param("num") Integer num,@Param("productId") Long productId,
                         @Param("unique")  String unique);


    /**
     * 秒杀 加库存 减销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */
    @Update("update yx_store_product_attr_value set stock=stock+#{num},seckill_stock=seckill_stock+#{num}, sales=sales-#{num}" +
            " where product_id=#{productId} and `unique`=#{unique}")
    int incSeckillStockDecSales(@Param("num") Integer num,@Param("productId") Long productId,
                                    @Param("unique")  String unique);


    /**
     * 普通商品 减库存 加销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */
    @Update("update yx_store_product_attr_value set stock=stock-#{num}, sales=sales+#{num}" +
            " where product_id=#{productId} and `unique`=#{unique} and stock >= #{num}")
    int decStockIncSales(@Param("num") Integer num, @Param("productId") Long productId,
                         @Param("unique")  String unique);

    /**
     * 拼团产品 减库存 加销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */
    @Update("update yx_store_product_attr_value set stock=stock-#{num}, pink_stock=pink_stock-#{num} ,sales=sales+#{num}" +
            " where product_id=#{productId} and `unique`=#{unique} and stock >= #{num} and pink_stock>=#{num}")
    int decCombinationStockIncSales(@Param("num") Integer num, @Param("productId") Long productId,
                                    @Param("unique") String unique);

    /**
     * 秒杀产品 减库存 加销量
     * @param num
     * @param productId
     * @param unique
     * @return
     */
    @Update("update yx_store_product_attr_value set stock=stock-#{num}, seckill_stock=seckill_stock-#{num},sales=sales+#{num}" +
            " where product_id=#{productId} and `unique`=#{unique} and stock >= #{num} and seckill_stock>=#{num}")
    int decSeckillStockIncSales(@Param("num") Integer num, @Param("productId") Long productId,
                                @Param("unique") String unique);
}
