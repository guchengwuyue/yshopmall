package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxMaterialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2020-01-09
*/
public interface YxMaterialGroupRepository extends JpaRepository<YxMaterialGroup, String>, JpaSpecificationExecutor<YxMaterialGroup> {
}