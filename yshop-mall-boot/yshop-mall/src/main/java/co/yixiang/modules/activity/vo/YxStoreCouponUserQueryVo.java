package co.yixiang.modules.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 优惠券发放记录表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@ApiModel(value = "YxStoreCouponUserQueryVo对象", description = "优惠券发放记录表查询参数")
public class YxStoreCouponUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券发放记录id")
    private Long id;

    @ApiModelProperty(value = "兑换的项目id")
    private Integer cid;

    @ApiModelProperty(value = "优惠券所属用户")
    private Long uid;

    @ApiModelProperty(value = "优惠券名称")
    private String couponTitle;

    @ApiModelProperty(value = "优惠券的面值")
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "最低消费多少金额可用优惠券")
    private BigDecimal useMinPrice;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "优惠券创建时间")
    private Date createTime;

    @ApiModelProperty(value = "优惠券结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "获取方式")
    private String type;

    @ApiModelProperty(value = "状态（0：未使用，1：已使用, 2:已过期）")
    private Integer status;

    @ApiModelProperty(value = "是否有效")
    private Integer isFail;

    @ApiModelProperty(value = "优惠券发放类型 0可用 1不可用")
    private Integer _type;

    @ApiModelProperty(value = "优惠券发放显示文字")
    private String _msg;

}
