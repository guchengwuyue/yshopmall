package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.service.dto.YxStoreVisitDTO;
import co.yixiang.modules.activity.service.dto.YxStoreVisitQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-18
*/
//@CacheConfig(cacheNames = "yxStoreVisit")
public interface YxStoreVisitService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreVisitQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreVisitDTO> queryAll(YxStoreVisitQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreVisitDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreVisitDTO create(YxStoreVisit resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreVisit resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}