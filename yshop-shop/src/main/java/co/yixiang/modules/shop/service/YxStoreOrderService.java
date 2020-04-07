package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.dto.OrderCountDto;
import co.yixiang.modules.shop.service.dto.OrderTimeDataDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-14
*/
//@CacheConfig(cacheNames = "yxStoreOrder")
public interface YxStoreOrderService {

    OrderCountDto getOrderCount();

    OrderTimeDataDTO getOrderTimeData();

    Map<String,Object> chartCount();

    String orderType(int id, int pinkId, int combinationId, int seckillId,int bargainId,int shippingType);

    void refund(YxStoreOrder resources);

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreOrderDTO> queryAll(YxStoreOrderQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreOrderDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreOrderDTO create(YxStoreOrder resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreOrder resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}