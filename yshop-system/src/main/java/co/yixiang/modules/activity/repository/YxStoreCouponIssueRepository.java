package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-09
*/
public interface YxStoreCouponIssueRepository extends JpaRepository<YxStoreCouponIssue, Integer>, JpaSpecificationExecutor {
}