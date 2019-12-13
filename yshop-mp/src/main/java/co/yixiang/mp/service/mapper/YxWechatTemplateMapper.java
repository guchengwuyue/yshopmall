package co.yixiang.mp.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.mp.domain.YxWechatTemplate;
import co.yixiang.mp.service.dto.YxWechatTemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author xuwenbo
* @date 2019-12-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxWechatTemplateMapper extends EntityMapper<YxWechatTemplateDTO, YxWechatTemplate> {

}