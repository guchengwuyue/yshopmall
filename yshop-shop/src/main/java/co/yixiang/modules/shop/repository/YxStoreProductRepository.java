package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author hupeng
* @date 2019-10-04
*/
public interface YxStoreProductRepository extends JpaRepository<YxStoreProduct, Integer>, JpaSpecificationExecutor {
    @Modifying
    @Query(value = "update yx_store_product set is_show = ?1 where id = ?2",nativeQuery = true)
    void updateOnsale(int status, int id);

    @Modifying
    @Query(value = "update yx_store_product set is_del = ?1 where id = ?2",nativeQuery = true)
    void updateDel(int status, int id);

    List<YxStoreProduct> findByStoreCategoryAndIsDel(YxStoreCategory storeCategory,int isDel);
}