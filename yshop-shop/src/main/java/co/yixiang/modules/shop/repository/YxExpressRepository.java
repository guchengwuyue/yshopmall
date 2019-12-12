package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxExpress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-12-12
*/
public interface YxExpressRepository extends JpaRepository<YxExpress, Integer>, JpaSpecificationExecutor {

    /**
     * findByCode
     * @param code
     * @return
     */
    YxExpress findByCode(String code);
}