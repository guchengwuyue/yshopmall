package co.yixiang.modules.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 等级任务设置 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-06
 */
@Data
@Schema(description = "YxSystemUserTaskQueryVo对象")
public class YxSystemUserTaskQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "任务名称")
    private String name;

    @Schema(description = "配置原名")
    private String realName;

    @Schema(description = "任务类型")
    private String taskType;

    @Schema(description = "限定数")
    private Integer number;

    @Schema(description = "等级id")
    private Integer levelId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否显示")
    private Integer isShow;

    @Schema(description = "是否务必达成任务,1务必达成,0=满足其一")
    private Integer isMust;

    @Schema(description = "任务说明")
    private String illustrate;

    @Schema(description = "新增时间")
    private Integer addTime;

    private Integer newNumber;//已经完成了多少

    private Integer speed;//进度比例

    private Integer finish;//是否完成

    private String taskTypeTitle;//任务类型标题

}
