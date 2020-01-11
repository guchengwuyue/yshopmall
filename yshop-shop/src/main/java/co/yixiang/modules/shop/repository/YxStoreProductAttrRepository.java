package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreProductAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author hupeng
* @date 2019-10-13
*/
public interface YxStoreProductAttrRepository extends JpaRepository<YxStoreProductAttr, Integer>, JpaSpecificationExecutor {
    //@Modifying
    //@Query(value = "delete from yx_store_product_attr where product_id =?1",nativeQuery = true)
    void deleteByProductId(int id);



}