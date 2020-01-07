package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.service.dto.YxStoreBargainDTO;
import co.yixiang.modules.activity.service.dto.YxStoreBargainQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author xuwenbo
* @date 2019-12-22
*/
//@CacheConfig(cacheNames = "yxStoreBargain")
public interface YxStoreBargainService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreBargainQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreBargainDTO> queryAll(YxStoreBargainQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreBargainDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreBargainDTO create(YxStoreBargain resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreBargain resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}