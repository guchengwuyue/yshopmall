package co.yixiang.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户任务完成记录表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-07
 */
@Data
@ApiModel(value = "YxUserTaskFinishQueryVo对象", description = "用户任务完成记录表查询参数")
public class YxUserTaskFinishQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "是否有效")
    private Boolean status;

    @ApiModelProperty(value = "添加时间")
    private Integer addTime;

}
