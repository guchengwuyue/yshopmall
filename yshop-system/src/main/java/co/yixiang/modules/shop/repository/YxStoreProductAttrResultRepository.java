package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxStoreProductAttrResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-10-13
*/
public interface YxStoreProductAttrResultRepository extends JpaRepository<YxStoreProductAttrResult, Integer>, JpaSpecificationExecutor {

    /**
     * findByProductId
     * @param product_id
     * @return
     */
    YxStoreProductAttrResult findByProductId(Integer product_id);

    void deleteByProductId(Integer product_id);
}