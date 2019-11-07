package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-14
*/
public interface YxStoreOrderRepository extends JpaRepository<YxStoreOrder, Integer>, JpaSpecificationExecutor {

    /**
     * findByUnique
     * @param unique
     * @return
     */
    YxStoreOrder findByUnique(String unique);
}