package co.yixiang.modules.shop.repository;

import co.yixiang.modules.shop.domain.YxUserBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
* @author hupeng
* @date 2019-11-06
*/
public interface YxUserBillRepository extends JpaRepository<YxUserBill, Integer>, JpaSpecificationExecutor {
    @Query(nativeQuery = true,
            value = "select b.title,b.pm,b.category,b.type,b.number,b.add_time as addTime," +
                    "u.nickname from yx_user_bill b left join yx_user u on u.uid=b.uid " +
                    " where if(?1 !='',b.category=?1,1=1) and if(?2 !='',b.type=?2,1=1)  " +
                    "and if(?3 !='',u.nickname LIKE CONCAT('%',?3,'%'),1=1) order by b.id desc",
            countQuery = "select count(*) from yx_user_bill b left join yx_user u on u.uid=b.uid" +
                    " where if(?1 !='',b.category=?1,1=1) and if(?2 !='',b.type=?2,1=1)  " +
                    "and if(?3 !='',u.nickname LIKE CONCAT('%',?3,'%'),1=1)")
    Page<Map> findAllByPageable(String category, String type, String nickname,
                                Pageable pageable);

}