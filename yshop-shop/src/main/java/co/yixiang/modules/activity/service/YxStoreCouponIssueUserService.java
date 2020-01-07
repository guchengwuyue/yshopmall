package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-09
*/
//@CacheConfig(cacheNames = "yxStoreCouponIssueUser")
public interface YxStoreCouponIssueUserService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreCouponIssueUserDTO> queryAll(YxStoreCouponIssueUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreCouponIssueUserDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreCouponIssueUserDTO create(YxStoreCouponIssueUser resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreCouponIssueUser resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}