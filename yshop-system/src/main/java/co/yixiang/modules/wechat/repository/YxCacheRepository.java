package co.yixiang.modules.wechat.repository;

import co.yixiang.modules.wechat.domain.YxCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-06
*/
public interface YxCacheRepository extends JpaRepository<YxCache, String>, JpaSpecificationExecutor {
}