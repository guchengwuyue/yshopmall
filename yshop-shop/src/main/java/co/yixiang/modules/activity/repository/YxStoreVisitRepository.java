package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-18
*/
public interface YxStoreVisitRepository extends JpaRepository<YxStoreVisit, Integer>, JpaSpecificationExecutor {
        int countByProductIdAndProductType(int productId, String productType);
}