package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-10
*/
public interface YxStoreCouponUserRepository extends JpaRepository<YxStoreCouponUser, Integer>, JpaSpecificationExecutor {
}