package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.StoreOrderCartInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
* @author hupeng
* @date 2019-10-14
*/
public interface YxStoreOrderCartInfoRepository extends JpaRepository<StoreOrderCartInfo, Integer>, JpaSpecificationExecutor {

    List<StoreOrderCartInfo> findByOid(int oid);

}