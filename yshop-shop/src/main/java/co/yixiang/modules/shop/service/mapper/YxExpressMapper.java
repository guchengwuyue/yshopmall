package co.yixiang.modules.shop.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.shop.domain.YxExpress;
import co.yixiang.modules.shop.service.dto.YxExpressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-12-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxExpressMapper extends EntityMapper<YxExpressDTO, YxExpress> {

}