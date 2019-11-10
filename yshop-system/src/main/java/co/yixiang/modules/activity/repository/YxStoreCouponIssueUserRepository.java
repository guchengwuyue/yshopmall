package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-09
*/
public interface YxStoreCouponIssueUserRepository extends JpaRepository<YxStoreCouponIssueUser, Integer>, JpaSpecificationExecutor {
}