package co.yixiang.modules.shop.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-11-03
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxStoreProductReplyMapper extends EntityMapper<YxStoreProductReplyDTO, YxStoreProductReply> {

}