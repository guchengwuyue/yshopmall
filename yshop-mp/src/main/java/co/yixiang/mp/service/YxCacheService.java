package co.yixiang.mp.service;


import co.yixiang.mp.domain.YxCache;
import co.yixiang.mp.service.dto.YxCacheDTO;
import co.yixiang.mp.service.dto.YxCacheQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-06
*/
//@CacheConfig(cacheNames = "yxCache")
public interface YxCacheService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxCacheQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxCacheDTO> queryAll(YxCacheQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param key
     * @return
     */
    //@Cacheable(key = "#p0")
    YxCacheDTO findById(String key);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxCacheDTO create(YxCache resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxCache resources);

    /**
     * 删除
     * @param key
     */
    //@CacheEvict(allEntries = true)
    void delete(String key);

    boolean isExist(String key);
}