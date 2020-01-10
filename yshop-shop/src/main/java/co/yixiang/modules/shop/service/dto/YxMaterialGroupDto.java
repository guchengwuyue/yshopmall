package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-01-09
*/
@Data
public class YxMaterialGroupDto implements Serializable {

    /** PK */
    private String id;

    /** 逻辑删除标记（0：显示；1：隐藏） */
    private String delFlag;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建者ID */
    private String createId;

    /** 分组名 */
    private String name;
}