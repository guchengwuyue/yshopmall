package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreProductAttrValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author hupeng
* @date 2019-10-13
*/
public interface YxStoreProductAttrValueRepository extends JpaRepository<YxStoreProductAttrValue, Integer>, JpaSpecificationExecutor {

    //@Modifying
   // @Query(value = "delete from yx_store_product_attr_value where product_id =?1",nativeQuery = true)
    void deleteByProductId(Integer id);

    @Query(value = "select sum(stock)  from yx_store_product_attr_value " +
            "where product_id = ?1",nativeQuery = true)
    Integer sumStock(Integer productId);
}