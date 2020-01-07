package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.service.dto.ProductFormatDTO;
import co.yixiang.modules.shop.service.dto.YxStoreProductDTO;
import co.yixiang.modules.shop.service.dto.YxStoreProductQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-04
*/
//@CacheConfig(cacheNames = "yxStoreProduct")
public interface YxStoreProductService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreProductQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreProductDTO> queryAll(YxStoreProductQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreProductDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreProductDTO create(YxStoreProduct resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreProduct resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);

    void recovery(Integer id);

    void onSale(Integer id, Integer status);

    List<ProductFormatDTO> isFormatAttr(Integer id, String jsonStr);

    void createProductAttr(Integer id, String jsonStr);

    void setResult(Map<String, Object> map, Integer id);

    void clearProductAttr(Integer id, boolean isActice);

    String getStoreProductAttrResult(Integer id);
}