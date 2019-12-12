package co.yixiang.modules.activity.service.mapper;

import co.yixiang.mapper.EntityMapper;
import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.service.dto.YxStoreCouponDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-11-09
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YxStoreCouponMapper extends EntityMapper<YxStoreCouponDTO, YxStoreCoupon> {

}