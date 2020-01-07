package co.yixiang.modules.system.service.mapper;

import co.yixiang.modules.system.service.dto.JobSmallDTO;
import co.yixiang.base.BaseMapper;
import co.yixiang.modules.system.domain.Job;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapper<JobSmallDTO, Job> {

}