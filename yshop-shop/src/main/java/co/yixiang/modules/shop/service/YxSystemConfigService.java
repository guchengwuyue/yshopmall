package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.service.dto.YxSystemConfigDTO;
import co.yixiang.modules.shop.service.dto.YxSystemConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-10
*/
//@CacheConfig(cacheNames = "yxSystemConfig")
public interface YxSystemConfigService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxSystemConfigQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxSystemConfigDTO> queryAll(YxSystemConfigQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxSystemConfigDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxSystemConfigDTO create(YxSystemConfig resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxSystemConfig resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);

    YxSystemConfig findByKey(String str);
}