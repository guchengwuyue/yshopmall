package co.yixiang.modules.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxUserTaskFinishQueryVo对象")
public class YxUserTaskFinishQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "任务id")
    private Integer taskId;

    @Schema(description = "用户id")
    private Integer uid;

    @Schema(description = "是否有效")
    private Boolean status;

    @Schema(description = "添加时间")
    private Integer addTime;

}
