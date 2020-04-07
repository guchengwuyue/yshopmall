package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
* @author hupeng
* @date 2019-10-03
*/
public interface YxStoreCategoryRepository extends JpaRepository<YxStoreCategory, Integer>, JpaSpecificationExecutor {
    @Query(value = "select cate_name from yx_store_category where id = ?1",nativeQuery = true)
    String findNameById(Integer id);

    @Modifying
    @Query("update YxStoreCategory s set s.isDel = 1 where s.id =:id")
    void delCategory(Integer id);

    YxStoreCategory findByPid(Integer pid);

}