package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
* @author hupeng
* @date 2019-11-18
*/
public interface YxStoreCombinationRepository extends JpaRepository<YxStoreCombination, Integer>, JpaSpecificationExecutor {
    @Modifying
    @Query(value = "update yx_store_combination set is_show = ?1 where id = ?2",nativeQuery = true)
    void updateOnsale(int status, int id);

}