package co.yixiang.modules.system.service.mapper;

import co.yixiang.modules.system.service.dto.DeptDTO;
import co.yixiang.base.BaseMapper;
import co.yixiang.modules.system.domain.Dept;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDTO, Dept> {

}