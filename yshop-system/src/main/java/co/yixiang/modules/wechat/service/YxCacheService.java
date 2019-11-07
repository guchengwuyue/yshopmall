package co.yixiang.modules.wechat.service;

import co.yixiang.modules.wechat.service.dto.YxCacheDTO;
import co.yixiang.modules.wechat.service.dto.YxCacheQueryCriteria;
import co.yixiang.modules.wechat.domain.YxCache;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;

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