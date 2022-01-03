/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.service.dto.ProductFormatDto;
import co.yixiang.modules.shop.service.dto.YxStoreProductDto;
import co.yixiang.modules.shop.service.dto.YxStoreProductQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hupeng
 * @date 2020-05-12
 */
public interface YxStoreProductService extends BaseService<YxStoreProduct> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxStoreProductQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreProductDto>
     */
    List<YxStoreProduct> queryAll(YxStoreProductQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreProductDto> all, HttpServletResponse response) throws IOException;

    YxStoreProduct saveProduct(YxStoreProduct storeProduct);

    void recovery(Integer id);

    void onSale(Integer id, int status);

    List<ProductFormatDto> isFormatAttr(Integer id, String jsonStr);

    void createProductAttr(Long id, String jsonStr);

    void clearProductAttr(Long id, boolean isActice);

    void setResult(Map<String, Object> map, Long id);

    String getStoreProductAttrResult(Integer id);

    void updateProduct(YxStoreProduct resources);

    void delete(Integer id);
}
