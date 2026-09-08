package co.yixiang.modules.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 签到记录表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-05
 */
@Data
@Schema(description = "YxUserSignQueryVo对象")
public class YxUserSignQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "用户uid")
    private Integer uid;

    @Schema(description = "签到说明")
    private String title;

    @Schema(description = "获得积分")
    private Integer number;

    @Schema(description = "剩余积分")
    private Integer balance;

    @Schema(description = "添加时间")
    private Integer addTime;

}
