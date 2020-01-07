package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.dto.UserMoneyDTO;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-06
*/
//@CacheConfig(cacheNames = "yxUser")
public interface YxUserService {

    void updateMoney(UserMoneyDTO param);

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxUserDTO> queryAll(YxUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param uid
     * @return
     */
    //@Cacheable(key = "#p0")
    YxUserDTO findById(Integer uid);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxUserDTO create(YxUser resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxUser resources);

    /**
     * 删除
     * @param uid
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer uid);

    void onStatus(Integer uid, Integer status);

    void incBrokeragePrice(double price, int uid);
}