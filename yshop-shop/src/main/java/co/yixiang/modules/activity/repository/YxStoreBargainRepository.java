package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreBargain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author xuwenbo
* @date 2019-12-22
*/
public interface YxStoreBargainRepository extends JpaRepository<YxStoreBargain, Integer>, JpaSpecificationExecutor {
}