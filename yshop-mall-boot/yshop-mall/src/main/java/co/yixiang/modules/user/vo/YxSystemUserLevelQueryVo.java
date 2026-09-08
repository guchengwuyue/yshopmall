package co.yixiang.modules.user.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设置用户等级表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-06
 */
@Data
@Schema(description = "YxSystemUserLevelQueryVo对象")
public class YxSystemUserLevelQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "商户id")
    private Integer merId;

    @Schema(description = "会员名称")
    private String name;

    @Schema(description = "购买金额")
    private BigDecimal money;

    @Schema(description = "有效时间")
    private Integer validDate;


    @Schema(description = "会员等级")
    private Integer grade;

    @Schema(description = "享受折扣")
    private BigDecimal discount;

    @Schema(description = "会员卡背景")
    private String image;

    @Schema(description = "会员图标")
    private String icon;

    @Schema(description = "说明")
    private String explain;

    @Schema(description = "添加时间")
    private Integer addTime;


    private Boolean isClear;

    private Integer isForever;



}
