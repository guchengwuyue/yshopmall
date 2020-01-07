package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStorePink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author hupeng
* @date 2019-11-18
*/
public interface YxStorePinkRepository extends JpaRepository<YxStorePink, Integer>, JpaSpecificationExecutor {
     int countByCid(int cid);

     int countByCidAndKId(int cid, int kid);

     int countByKId(int kid);

     YxStorePink findByOrderIdKey(int id);

}