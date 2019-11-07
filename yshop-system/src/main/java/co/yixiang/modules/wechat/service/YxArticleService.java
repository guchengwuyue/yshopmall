package co.yixiang.modules.wechat.service;

import co.yixiang.modules.wechat.service.dto.YxArticleQueryCriteria;
import co.yixiang.modules.wechat.domain.YxArticle;
import co.yixiang.modules.wechat.service.dto.YxArticleDTO;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;

/**
* @author hupeng
* @date 2019-10-07
*/
//@CacheConfig(cacheNames = "yxArticle")
public interface YxArticleService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxArticleQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxArticleDTO> queryAll(YxArticleQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxArticleDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxArticleDTO create(YxArticle resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxArticle resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}