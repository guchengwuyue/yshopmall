package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.dto.YxUserExtractDTO;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-14
*/
//@CacheConfig(cacheNames = "yxUserExtract")
public interface YxUserExtractService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxUserExtractQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxUserExtractDTO> queryAll(YxUserExtractQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxUserExtractDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxUserExtractDTO create(YxUserExtract resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxUserExtract resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}