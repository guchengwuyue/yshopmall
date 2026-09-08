package co.yixiang.modules.activity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 优惠券表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@Schema(description = "YxStoreCouponQueryVo对象")
public class YxStoreCouponQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "优惠券表ID")
    private Integer id;

    @Schema(description = "优惠券名称")
    private String title;

    @Schema(description = "兑换消耗积分值")
    private Integer integral;

    @Schema(description = "兑换的优惠券面值")
    private BigDecimal couponPrice;

    @Schema(description = "最低消费多少金额可用优惠券")
    private BigDecimal useMinPrice;

    @Schema(description = "优惠券有效期限（单位：天）")
    private Integer couponTime;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态（0：关闭，1：开启）")
    private Boolean status;

    @Schema(description = "兑换项目添加时间")
    private Integer addTime;

    @Schema(description = "是否删除")
    private Boolean isDel;

}