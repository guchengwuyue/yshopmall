package co.yixiang.modules.activity.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.dto.YxStorePinkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-11-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxStorePinkMapper extends EntityMapper<YxStorePinkDTO, YxStorePink> {

}