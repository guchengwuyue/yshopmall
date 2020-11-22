package co.yixiang.modules.monitor.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.monitor.domain.Visits;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VisitsMapper extends CoreMapper<Visits> {
    @Select("select * FROM visits where create_time between #{time1} and #{time2}")
    List<Visits> findAllVisits(@Param("time1") String time1, @Param("time2") String time2);
}
