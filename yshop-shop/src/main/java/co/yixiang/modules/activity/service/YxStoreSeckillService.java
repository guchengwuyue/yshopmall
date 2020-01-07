package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillDTO;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author xuwenbo
* @date 2019-12-14
*/
//@CacheConfig(cacheNames = "yxStoreSeckill")
public interface YxStoreSeckillService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreSeckillQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreSeckillDTO> queryAll(YxStoreSeckillQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreSeckillDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreSeckillDTO create(YxStoreSeckill resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreSeckill resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}