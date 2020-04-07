package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.dto.ChartDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author hupeng
* @date 2019-10-14
*/
public interface YxStoreOrderRepository extends JpaRepository<YxStoreOrder, Integer>, JpaSpecificationExecutor {


    //今天 //上周 //本月
    int countByPayTimeGreaterThanEqual(int time);

    //昨天
    int countByPayTimeLessThanAndPayTimeGreaterThanEqual(int timeO, int timeT);

    @Query(value = "select IFNULL(sum(pay_price),0)  from yx_store_order " +
            "where refund_status=0 and is_del=0 and paid=1 and  pay_time >= ?1",nativeQuery = true)
    double sumPrice(Integer time);

    @Query(value = "select IFNULL(sum(pay_price),0)  from yx_store_order " +
            "where refund_status=0 and is_del=0 and paid=1",nativeQuery = true)
    double sumTotalPrice();

    @Query(value = "select IFNULL(sum(pay_price),0)  from yx_store_order " +
            "where refund_status=0 and is_del=0 and paid=1 and  pay_time >= ?1 and pay_time < ?2",nativeQuery = true)
    double sumTPrice(Integer timeO, Integer timeT);

    @Query(value = "SELECT IFNULL(sum(pay_price),0) as num," +
            "FROM_UNIXTIME(add_time, '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= ?1" +
            " GROUP BY FROM_UNIXTIME(add_time,'%Y-%m-%d') " +
            " ORDER BY add_time ASC",nativeQuery = true)
    List<ChartDataDTO> chartList(Integer time);

    @Query(value = "SELECT count(id) as num," +
            "FROM_UNIXTIME(add_time, '%m-%d') as time " +
            " FROM yx_store_order where refund_status=0 and is_del=0 and paid=1 and pay_time >= ?1" +
            " GROUP BY FROM_UNIXTIME(add_time,'%Y-%m-%d') " +
            " ORDER BY add_time ASC",nativeQuery = true)
    List<ChartDataDTO> chartListT(Integer time);


    /**
     * findByUnique
     * @param unique
     * @return
     */
    YxStoreOrder findByUnique(String unique);
}