package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
* @author hupeng
* @date 2019-10-06
*/
public interface YxUserRepository extends JpaRepository<YxUser, Integer>, JpaSpecificationExecutor {

    @Modifying
    @Query(value = "update yx_user set status = ?1 where uid = ?2",nativeQuery = true)
    void updateOnstatus(int status, int id);

    @Modifying
    @Query(value = "update yx_user set now_money = now_money + ?1 where uid = ?2",nativeQuery = true)
    void updateMoney(double money, int id);

    @Modifying
    @Query(value = "update yx_user set brokerage_price = brokerage_price+?1 where uid = ?2",nativeQuery = true)
    void incBrokeragePrice(double price, int id);




}