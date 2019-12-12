package co.yixiang.modules.activity.service.dto;

import lombok.Data;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2019-11-09
*/
@Data
public class YxStoreCouponIssueQueryCriteria{
    @Query
    private Integer isDel;
}