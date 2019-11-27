package co.yixiang.mp.service;


import co.yixiang.mp.domain.YxWechatReply;
import co.yixiang.mp.service.dto.YxWechatReplyDTO;
import co.yixiang.mp.service.dto.YxWechatReplyQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-10
*/
//@CacheConfig(cacheNames = "yxWechatReply")
public interface YxWechatReplyService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(YxWechatReplyQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<YxWechatReplyDTO> queryAll(YxWechatReplyQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    YxWechatReplyDTO findById(Integer id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    YxWechatReplyDTO create(YxWechatReply resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(YxWechatReply resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Integer id);

    YxWechatReply isExist(String key);

}