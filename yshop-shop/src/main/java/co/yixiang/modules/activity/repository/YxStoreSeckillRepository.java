package co.yixiang.modules.activity.repository;

import co.yixiang.modules.activity.domain.YxStoreSeckill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author xuwenbo
* @date 2019-12-14
*/
public interface YxStoreSeckillRepository extends JpaRepository<YxStoreSeckill, Integer>, JpaSpecificationExecutor {
}