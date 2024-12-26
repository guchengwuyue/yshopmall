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
import co.yixiang.domain.PageResult;
import co.yixiang.modules.product.domain.YxStoreProductRelation;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationDto;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationQueryCriteria;
import co.yixiang.modules.product.vo.YxStoreProductRelationQueryVo;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品点赞和收藏表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-23
 */
public interface YxStoreProductRelationService extends BaseService<YxStoreProductRelation> {

    /**
     * 是否收藏
     * @param productId 商品ID
     * @param uid 用户ID
     * @return Boolean
     */
    Boolean isProductRelation(long productId, long uid,String category);

    /**
     *添加收藏
     * @param productId 商品id
     * @param uid 用户id
     */
    void addRroductRelation(long productId,long uid,String category,String type);

    /**
     * 取消收藏
     * @param productId 商品id
     * @param uid 用户id
     */
    void delRroductRelation(long productId,long uid,String category,String type);

    /**
     * 获取用户收藏列表
     * @param page page
     * @param limit limit
     * @param uid 用户id
     * @return list
     */
    List<YxStoreProductRelationQueryVo> userCollectProduct(int page, int limit, Long uid,String type);

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    PageResult<YxStoreProductRelationDto> queryAll(YxStoreProductRelationQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreProductRelationDto>
     */
    List<YxStoreProductRelation> queryAll(YxStoreProductRelationQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreProductRelationDto> all, HttpServletResponse response) throws IOException;


    /**
     * 批量删除
     * @param ids /
     */
    void collectDelFoot(List<Long> ids);
}
