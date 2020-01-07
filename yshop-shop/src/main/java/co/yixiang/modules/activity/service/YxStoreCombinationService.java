package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-18
*/
//@CacheConfig(cacheNames = "yxStoreCombination")
public interface YxStoreCombinationService {

    void onSale(Integer id, Integer status);

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreCombinationQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreCombinationDTO> queryAll(YxStoreCombinationQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreCombinationDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreCombinationDTO create(YxStoreCombination resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreCombination resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}