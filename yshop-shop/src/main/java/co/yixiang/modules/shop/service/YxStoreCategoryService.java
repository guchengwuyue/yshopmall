package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-03
*/
//@CacheConfig(cacheNames = "yxStoreCategory")
public interface YxStoreCategoryService {

    /**
     * 导出数据
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreCategoryDTO> queryAll, HttpServletResponse response) throws IOException;


    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreCategoryQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreCategoryDTO> queryAll(YxStoreCategoryQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreCategoryDTO findById(Integer id);

    YxStoreCategoryDTO findByName(String name);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreCategoryDTO create(YxStoreCategory resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreCategory resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);

    Object buildTree(List<YxStoreCategoryDTO> categoryDTOS);

}