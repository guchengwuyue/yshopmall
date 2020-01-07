package co.yixiang.modules.mnt.service.mapper;

import co.yixiang.base.BaseMapper;
import co.yixiang.modules.mnt.domain.App;
import co.yixiang.modules.mnt.service.dto.AppDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper extends BaseMapper<AppDto, App> {

}
