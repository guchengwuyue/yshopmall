package co.yixiang.modules.wechat.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.wechat.domain.YxWechatReply;
import co.yixiang.modules.wechat.service.dto.YxWechatReplyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-10-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxWechatReplyMapper extends EntityMapper<YxWechatReplyDTO, YxWechatReply> {

}