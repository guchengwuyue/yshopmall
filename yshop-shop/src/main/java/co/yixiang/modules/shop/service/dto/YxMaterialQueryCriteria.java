package co.yixiang.modules.shop.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;
import java.util.List;


/**
* @author hupeng
* @date 2020-01-09
*/
@Data
public class YxMaterialQueryCriteria{
    @Query
    private String groupId;
}