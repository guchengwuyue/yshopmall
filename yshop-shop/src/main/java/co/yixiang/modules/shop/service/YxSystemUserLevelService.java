package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelDTO;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-12-04
*/
//@CacheConfig(cacheNames = "yxSystemUserLevel")
public interface YxSystemUserLevelService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxSystemUserLevelQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxSystemUserLevelDTO> queryAll(YxSystemUserLevelQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxSystemUserLevelDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxSystemUserLevelDTO create(YxSystemUserLevel resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxSystemUserLevel resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}