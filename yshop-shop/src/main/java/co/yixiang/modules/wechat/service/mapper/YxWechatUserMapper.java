package co.yixiang.modules.wechat.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.wechat.domain.YxWechatUser;
import co.yixiang.modules.wechat.service.dto.YxWechatUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-12-13
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxWechatUserMapper extends EntityMapper<YxWechatUserDTO, YxWechatUser> {

}