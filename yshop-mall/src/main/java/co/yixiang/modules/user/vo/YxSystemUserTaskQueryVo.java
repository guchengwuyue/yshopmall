package co.yixiang.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "YxSystemUserTaskQueryVo对象", description = "等级任务设置查询参数")
public class YxSystemUserTaskQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "配置原名")
    private String realName;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "限定数")
    private Integer number;

    @ApiModelProperty(value = "等级id")
    private Integer levelId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    @ApiModelProperty(value = "是否务必达成任务,1务必达成,0=满足其一")
    private Integer isMust;

    @ApiModelProperty(value = "任务说明")
    private String illustrate;

    @ApiModelProperty(value = "新增时间")
    private Integer addTime;

    private Integer newNumber;//已经完成了多少

    private Integer speed;//进度比例

    private Integer finish;//是否完成

    private String taskTypeTitle;//任务类型标题

}
