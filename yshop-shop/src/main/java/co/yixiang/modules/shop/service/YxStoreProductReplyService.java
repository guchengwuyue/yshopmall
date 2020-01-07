package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDTO;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-03
*/
//@CacheConfig(cacheNames = "yxStoreProductReply")
public interface YxStoreProductReplyService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreProductReplyQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreProductReplyDTO> queryAll(YxStoreProductReplyQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreProductReplyDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreProductReplyDTO create(YxStoreProductReply resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreProductReply resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}