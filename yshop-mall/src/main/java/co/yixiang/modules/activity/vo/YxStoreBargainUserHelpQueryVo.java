package co.yixiang.modules.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 砍价用户帮助表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-21
 */
@Data
@ApiModel(value = "YxStoreBargainUserHelpQueryVo对象", description = "砍价用户帮助表查询参数")
public class YxStoreBargainUserHelpQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "砍价用户帮助表ID")
    private Long id;

    @ApiModelProperty(value = "帮助的用户id")
    private Long uid;

    @ApiModelProperty(value = "砍价产品ID")
    private Long bargainId;

    @ApiModelProperty(value = "用户参与砍价表id")
    private Long bargainUserId;

    @ApiModelProperty(value = "帮助砍价多少金额")
    private BigDecimal price;

    private String nickname;

    private String avatar;

}