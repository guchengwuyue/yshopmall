package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataDTO;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-18
*/
//@CacheConfig(cacheNames = "yxSystemGroupData")
public interface YxSystemGroupDataService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxSystemGroupDataQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxSystemGroupDataDTO> queryAll(YxSystemGroupDataQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxSystemGroupDataDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxSystemGroupDataDTO create(YxSystemGroupData resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxSystemGroupData resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}