package co.yixiang.modules.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 优惠券前台领取表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@ApiModel(value = "YxStoreCouponIssueQueryVo对象", description = "优惠券前台领取表查询参数")
public class YxStoreCouponIssueQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "优惠券ID")
    private Integer cid;

    @ApiModelProperty(value = "优惠券名称")
    private String cname;

    @ApiModelProperty(value = "优惠券类别")
    private Integer ctype;

    @ApiModelProperty(value = "优惠券领取开启时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "优惠券领取结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "优惠券领取数量")
    private Integer totalCount;

    @ApiModelProperty(value = "优惠券剩余领取数量")
    private Integer remainCount;

    @ApiModelProperty(value = "是否无限张数")
    private Integer isPermanent;

    @ApiModelProperty(value = "1 正常 0 未开启 -1 已无效")
    private Integer status;

    @ApiModelProperty(value = "优惠券价格")
    private Double couponPrice;

    @ApiModelProperty(value = "优惠券最低满多少能使用")
    private Double useMinPrice;

    @ApiModelProperty(value = "优惠券是否使用")
    private Boolean isUse;

}
