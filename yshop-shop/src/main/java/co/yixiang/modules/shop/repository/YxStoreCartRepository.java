package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreCart;
import co.yixiang.modules.shop.service.dto.CountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @author xwb
 * @date 2020-04-02
 */
public interface YxStoreCartRepository extends JpaRepository<YxStoreCart, Long>, JpaSpecificationExecutor {

    @Query(value ="SELECT t.cate_name as catename from yx_store_cart c  " +
            "LEFT JOIN yx_store_product p on c.product_id = p.id  " +
            "LEFT JOIN yx_store_category t on p.cate_id = t.id " +
            "WHERE c.is_pay = 1",nativeQuery = true)
    List<CountDto> findCateName();
}
