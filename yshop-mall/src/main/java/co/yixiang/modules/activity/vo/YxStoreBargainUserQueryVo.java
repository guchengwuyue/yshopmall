package co.yixiang.modules.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户参与砍价表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-21
 */
@Data
@ApiModel(value = "YxStoreBargainUserQueryVo对象", description = "用户参与砍价表查询参数")
public class YxStoreBargainUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户参与砍价表ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "砍价产品id")
    private Long bargainId;

    @ApiModelProperty(value = "砍价的最低价")
    private BigDecimal bargainPriceMin;

    @ApiModelProperty(value = "砍价金额")
    private BigDecimal bargainPrice;

    @ApiModelProperty(value = "砍掉的价格")
    private BigDecimal price;

    @ApiModelProperty(value = "状态 1参与中 2 活动结束参与失败 3活动结束参与成功")
    private Integer status;


    private Double residuePrice;

    private String title;

    private String image;

    private Date datatime;

}
