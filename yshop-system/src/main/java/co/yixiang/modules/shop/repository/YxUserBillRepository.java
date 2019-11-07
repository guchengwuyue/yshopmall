package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxUserBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-06
*/
public interface YxUserBillRepository extends JpaRepository<YxUserBill, Integer>, JpaSpecificationExecutor {
}