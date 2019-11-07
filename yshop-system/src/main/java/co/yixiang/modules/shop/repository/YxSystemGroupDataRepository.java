package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxSystemGroupData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-18
*/
public interface YxSystemGroupDataRepository extends JpaRepository<YxSystemGroupData, Integer>, JpaSpecificationExecutor {
}