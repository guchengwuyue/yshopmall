package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import java.util.List;
import co.yixiang.annotation.Query;

/**
* @author hupeng
* @date 2020-03-22
*/
@Data
public class YxSystemStoreStaffQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String staffName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String nickname;
}