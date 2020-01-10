package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2020-01-09
*/
public interface YxMaterialRepository extends JpaRepository<YxMaterial, String>, JpaSpecificationExecutor<YxMaterial> {
}