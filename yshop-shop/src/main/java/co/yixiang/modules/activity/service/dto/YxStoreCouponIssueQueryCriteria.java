package co.yixiang.modules.activity.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
* @author hupeng
* @date 2019-11-09
*/
@Data
public class YxStoreCouponIssueQueryCriteria{
    @Query
    private Integer isDel;
}