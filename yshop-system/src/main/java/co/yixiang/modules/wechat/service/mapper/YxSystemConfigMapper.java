package co.yixiang.modules.wechat.service.mapper;

import co.yixiang.modules.wechat.domain.YxSystemConfig;
import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.wechat.service.dto.YxSystemConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-10-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxSystemConfigMapper extends EntityMapper<YxSystemConfigDTO, YxSystemConfig> {

}