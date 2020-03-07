package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxUserRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2020-03-02
*/
public interface YxUserRechargeRepository extends JpaRepository<YxUserRecharge, Integer>, JpaSpecificationExecutor<YxUserRecharge> {
    /**
    * 根据 OrderId 查询
    * @param order_id /
    * @return /
    */
    YxUserRecharge findByOrderId(String order_id);
}