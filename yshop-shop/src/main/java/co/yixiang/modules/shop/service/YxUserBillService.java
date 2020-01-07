package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.dto.YxUserBillDTO;
import co.yixiang.modules.shop.service.dto.YxUserBillQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-11-06
*/
//@CacheConfig(cacheNames = "yxUserBill")
public interface YxUserBillService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxUserBillQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxUserBillDTO> queryAll(YxUserBillQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxUserBillDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxUserBillDTO create(YxUserBill resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxUserBill resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);
}