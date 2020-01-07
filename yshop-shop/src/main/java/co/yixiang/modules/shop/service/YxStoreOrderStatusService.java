package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-02
*/
//@CacheConfig(cacheNames = "yxStoreOrderStatus")
public interface YxStoreOrderStatusService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreOrderStatusQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreOrderStatusDTO> queryAll(YxStoreOrderStatusQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreOrderStatusDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreOrderStatusDTO create(YxStoreOrderStatus resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreOrderStatus resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}