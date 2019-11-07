package co.yixiang.modules.wechat.repository;

import co.yixiang.modules.wechat.domain.YxWechatReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-10
*/
public interface YxWechatReplyRepository extends JpaRepository<YxWechatReply, Integer>, JpaSpecificationExecutor {

    /**
     * findByKey
     * @param key
     * @return
     */
    YxWechatReply findByKey(String key);
}