package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemUserTask;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskDTO;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-12-04
*/
//@CacheConfig(cacheNames = "yxSystemUserTask")
public interface YxSystemUserTaskService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxSystemUserTaskQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxSystemUserTaskDTO> queryAll(YxSystemUserTaskQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxSystemUserTaskDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxSystemUserTaskDTO create(YxSystemUserTask resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxSystemUserTask resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);

    List<Map<String,Object>> getTaskType();
}