package co.yixiang.mp.repository;


import co.yixiang.mp.domain.YxCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-06
*/
public interface YxCacheRepository extends JpaRepository<YxCache, String>, JpaSpecificationExecutor {
}