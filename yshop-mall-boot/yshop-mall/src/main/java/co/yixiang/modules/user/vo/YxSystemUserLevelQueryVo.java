package co.yixiang.modules.user.vo;


import co.yixiang.modules.user.service.dto.TaskDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "YxSystemUserLevelQueryVo对象", description = "设置用户等级表查询参数")
public class YxSystemUserLevelQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "商户id")
    private Integer merId;

    @ApiModelProperty(value = "会员名称")
    private String name;

    @ApiModelProperty(value = "购买金额")
    private BigDecimal money;

    @ApiModelProperty(value = "有效时间")
    private Integer validDate;


    @ApiModelProperty(value = "会员等级")
    private Integer grade;

    @ApiModelProperty(value = "享受折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "会员卡背景")
    private String image;

    @ApiModelProperty(value = "会员图标")
    private String icon;

    @ApiModelProperty(value = "说明")
    private String explain;

    @ApiModelProperty(value = "添加时间")
    private Integer addTime;

    private TaskDto taskList;

    private Boolean isClear;

    private Integer isForever;



}
