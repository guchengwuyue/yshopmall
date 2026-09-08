package co.yixiang.modules.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxStoreCouponUserQueryVo对象")
public class YxStoreCouponUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "优惠券发放记录id")
    private Long id;

    @Schema(description = "兑换的项目id")
    private Integer cid;

    @Schema(description = "优惠券所属用户")
    private Long uid;

    @Schema(description = "优惠券名称")
    private String couponTitle;

    @Schema(description = "优惠券的面值")
    private BigDecimal couponPrice;

    @Schema(description = "最低消费多少金额可用优惠券")
    private BigDecimal useMinPrice;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Schema(description = "优惠券创建时间")
    private Date createTime;

    @Schema(description = "优惠券结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    @Schema(description = "使用时间")
    private Date useTime;

    @Schema(description = "获取方式")
    private String type;

    @Schema(description = "状态（0：未使用，1：已使用, 2:已过期）")
    private Integer status;

    @Schema(description = "是否有效")
    private Integer isFail;

    @Schema(description = "优惠券发放类型 0可用 1不可用")
    private Integer _type;

    @Schema(description = "优惠券发放显示文字")
    private String _msg;

}
