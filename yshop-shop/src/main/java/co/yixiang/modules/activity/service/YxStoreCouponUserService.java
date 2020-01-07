package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-10
*/
//@CacheConfig(cacheNames = "yxStoreCouponUser")
public interface YxStoreCouponUserService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreCouponUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreCouponUserDTO> queryAll(YxStoreCouponUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreCouponUserDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreCouponUserDTO create(YxStoreCouponUser resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreCouponUser resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}