package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxWechatUser;
import co.yixiang.modules.shop.service.dto.YxWechatUserDTO;
import co.yixiang.modules.shop.service.dto.YxWechatUserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-12-13
*/
//@CacheConfig(cacheNames = "yxWechatUser")
public interface YxWechatUserService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxWechatUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxWechatUserDTO> queryAll(YxWechatUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param uid
     * @return
     */
    //@Cacheable(key = "#p0")
    YxWechatUserDTO findById(Integer uid);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxWechatUserDTO create(YxWechatUser resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxWechatUser resources);

    /**
     * 删除
     * @param uid
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer uid);
}