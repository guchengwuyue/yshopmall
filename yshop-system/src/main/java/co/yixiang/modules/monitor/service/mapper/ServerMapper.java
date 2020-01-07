package co.yixiang.modules.monitor.service.mapper;

import co.yixiang.modules.monitor.domain.Server;
import co.yixiang.base.BaseMapper;
import co.yixiang.modules.monitor.service.dto.ServerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zhang houying
* @date 2019-11-03
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServerMapper extends BaseMapper<ServerDTO, Server> {

}
