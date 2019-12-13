package co.yixiang.mp.service;

import co.yixiang.mp.domain.YxWechatTemplate;
import co.yixiang.mp.service.dto.YxWechatTemplateDTO;
import co.yixiang.mp.service.dto.YxWechatTemplateQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;

/**
* @author xuwenbo
* @date 2019-12-10
*/
//@CacheConfig(cacheNames = "yxWechatTemplate")
public interface YxWechatTemplateService {

    YxWechatTemplate findByTempkey(String key);

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxWechatTemplateQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxWechatTemplateDTO> queryAll(YxWechatTemplateQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxWechatTemplateDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxWechatTemplateDTO create(YxWechatTemplate resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxWechatTemplate resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}