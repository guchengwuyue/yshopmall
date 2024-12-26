package co.yixiang.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "YxUserSignQueryVo对象", description = "签到记录表查询参数")
public class YxUserSignQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "用户uid")
    private Integer uid;

    @ApiModelProperty(value = "签到说明")
    private String title;

    @ApiModelProperty(value = "获得积分")
    private Integer number;

    @ApiModelProperty(value = "剩余积分")
    private Integer balance;

    @ApiModelProperty(value = "添加时间")
    private Integer addTime;

}
