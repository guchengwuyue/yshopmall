package co.yixiang.mp.service;


import co.yixiang.mp.domain.YxWechatMenu;
import co.yixiang.mp.service.dto.YxWechatMenuDTO;
import co.yixiang.mp.service.dto.YxWechatMenuQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-06
*/
//@CacheConfig(cacheNames = "YxWechatMenu")
public interface YxWechatMenuService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxWechatMenuQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxWechatMenuDTO> queryAll(YxWechatMenuQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param key
     * @return
     */
    //@Cacheable(key = "#p0")
    YxWechatMenuDTO findById(String key);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxWechatMenuDTO create(YxWechatMenu resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxWechatMenu resources);

    /**
     * 删除
     * @param key
     */
    //@CacheEvict(allEntries = true)
    void delete(String key);

    boolean isExist(String key);
}