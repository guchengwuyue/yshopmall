/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.cart.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.cart.domain.YxStoreCart;
import co.yixiang.modules.cart.service.dto.YxStoreCartDto;
import co.yixiang.modules.cart.service.dto.YxStoreCartQueryCriteria;
import co.yixiang.modules.order.service.dto.CountDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxStoreCartService  extends BaseService<YxStoreCart>{

    /**
     * 删除购物车
     * @param uid uid
     * @param ids 购物车id集合
     */
    void removeUserCart(Long uid, List<String> ids);

    /**
     * 改购物车数量
     * @param cartId 购物车id
     * @param cartNum 数量
     * @param uid uid
     */
    void changeUserCartNum(Long cartId,int cartNum,Long uid);

    /**
     * 购物车列表
     * @param uid 用户id
     * @param cartIds 购物车id，多个逗号隔开
     * @param status 0-购购物车列表
     * @return map valid-有效购物车 invalid-失效购物车
     */
    Map<String,Object> getUserProductCartList(Long uid,String cartIds,Integer status);

    /**
     * 返回当前用户购物车总数量
     * @param uid 用户id
     * @return int
     */
    int getUserCartNum(Long uid);

    /**
     * 添加购物车
     * @param uid  用户id
     * @param productId 普通产品编号
     * @param cartNum  购物车数量
     * @param productAttrUnique 属性唯一值
     * @param isNew 1 加入购物车直接购买  0 加入购物车
     * @param combinationId 拼团id
     * @param seckillId  秒杀id
     * @param bargainId  砍价id
     * @return 购物车id
     */
    long addCart(Long uid,Long productId,Integer cartNum, String productAttrUnique,
                Integer isNew,Long combinationId,Long seckillId,Long bargainId);

    /**
     * 检测商品/秒杀/砍价/拼团库存
     * @param uid  用户ID
     * @param productId  产品ID
     * @param cartNum 购买数量
     * @param productAttrUnique  商品属性Unique
     * @param combinationId  拼团产品ID
     * @param seckillId      秒杀产品ID
     * @param bargainId   砍价产品ID
     */
    void checkProductStock(Long uid, Long productId, Integer cartNum, String productAttrUnique,
                           Long combinationId, Long seckillId, Long bargainId);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxStoreCartQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxStoreCartDto>
    */
    List<YxStoreCart> queryAll(YxStoreCartQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxStoreCartDto> all, HttpServletResponse response) throws IOException;

    List<CountDto> findCateName();
}
