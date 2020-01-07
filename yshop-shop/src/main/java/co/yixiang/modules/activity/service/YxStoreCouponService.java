package co.yixiang.modules.activity.service;

import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.service.dto.YxStoreCouponDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-09
*/
//@CacheConfig(cacheNames = "yxStoreCoupon")
public interface YxStoreCouponService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxStoreCouponQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxStoreCouponDTO> queryAll(YxStoreCouponQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxStoreCouponDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxStoreCouponDTO create(YxStoreCoupon resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxStoreCoupon resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}