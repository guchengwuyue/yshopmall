package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2020-03-22
*/
public interface YxSystemStoreStaffRepository extends JpaRepository<YxSystemStoreStaff, Integer>, JpaSpecificationExecutor<YxSystemStoreStaff> {
}