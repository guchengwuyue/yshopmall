package co.yixiang.repository;

import co.yixiang.domain.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Long>, JpaSpecificationExecutor {

    @Query(nativeQuery = true,
            value = "select l.id,l.create_time as createTime,l.description," +
                    "l.request_ip as requestIp,l.address," +
                    "u.nickname from log l left join yx_user u on u.uid=l.uid " +
                    " where l.type=1" +
                    " and if(?1 !='',u.nickname LIKE CONCAT('%',?1,'%'),1=1) order by l.id desc",
            countQuery = "select count(*) from log l left join yx_user u on u.uid=l.uid" +
                    " where l.type=1 " +
                    "and if(?1 !='',u.nickname LIKE CONCAT('%',?1,'%'),1=1)")
    Page<Map> findAllByPageable(String nickname,
                                Pageable pageable);

    /**
     * 获取一个时间段的IP记录
     * @param date1
     * @param date2
     * @return
     */
    @Query(value = "select count(*) FROM (select request_ip FROM log where create_time between ?1 and ?2 GROUP BY request_ip) as s",nativeQuery = true)
    Long findIp(String date1, String date2);

    /**
     * findExceptionById
     * @param id
     * @return
     */
    @Query(value = "select exception_detail FROM log where id = ?1",nativeQuery = true)
    String findExceptionById(Long id);
}
