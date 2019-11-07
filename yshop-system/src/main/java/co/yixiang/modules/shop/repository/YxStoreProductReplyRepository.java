package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreProductReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-03
*/
public interface YxStoreProductReplyRepository extends JpaRepository<YxStoreProductReply, Integer>, JpaSpecificationExecutor {
}