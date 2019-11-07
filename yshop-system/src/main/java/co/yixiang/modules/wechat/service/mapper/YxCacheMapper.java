package co.yixiang.modules.wechat.service.mapper;

import co.yixiang.modules.wechat.service.dto.YxCacheDTO;
import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.wechat.domain.YxCache;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-10-06
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxCacheMapper extends EntityMapper<YxCacheDTO, YxCache> {

}