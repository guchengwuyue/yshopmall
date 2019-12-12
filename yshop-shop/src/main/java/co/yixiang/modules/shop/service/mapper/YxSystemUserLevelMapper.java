package co.yixiang.modules.shop.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.shop.service.dto.YxSystemUserLevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-12-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxSystemUserLevelMapper extends EntityMapper<YxSystemUserLevelDTO, YxSystemUserLevel> {

}