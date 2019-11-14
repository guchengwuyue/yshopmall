package co.yixiang.modules.activity.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.dto.YxUserExtractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-11-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxUserExtractMapper extends EntityMapper<YxUserExtractDTO, YxUserExtract> {

}