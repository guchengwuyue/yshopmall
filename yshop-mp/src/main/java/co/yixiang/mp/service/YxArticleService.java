package co.yixiang.mp.service;


import co.yixiang.mp.domain.YxArticle;
import co.yixiang.mp.service.dto.YxArticleDTO;
import co.yixiang.mp.service.dto.YxArticleQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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

    void uploadNews(YxArticleDTO yxArticleDTO) throws Exception;

}