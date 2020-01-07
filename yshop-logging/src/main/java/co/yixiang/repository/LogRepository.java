package co.yixiang.repository;

import co.yixiang.domain.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Long>, JpaSpecificationExecutor<Log> {



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
     * @param date1 startTime
     * @param date2 entTime
     * @return IP数目
     */
    @Query(value = "select count(*) FROM (select request_ip FROM log where create_time between ?1 and ?2 GROUP BY request_ip) as s",nativeQuery = true)
    Long findIp(String date1, String date2);

    /**
     * 根据日志类型删除信息
     * @param logType 日志类型
     */
    @Query(nativeQuery = true,value = "delete from log where log_type = ?1")
    @Modifying
    void deleteByLogType(String logType);
}
