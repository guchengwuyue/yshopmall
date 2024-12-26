package co.yixiang.modules.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 拼团表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-11-19
 */
@Data
@ApiModel(value = "YxStorePinkQueryVo对象", description = "拼团表查询参数")
public class YxStorePinkQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "订单id 生成")
    private String orderId;

    @ApiModelProperty(value = "订单id  数据库")
    private Integer orderIdKey;

    @ApiModelProperty(value = "购买商品个数")
    private Integer totalNum;

    @ApiModelProperty(value = "购买总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "拼团产品id")
    private Long cid;

    @ApiModelProperty(value = "产品id")
    private Long pid;

    @ApiModelProperty(value = "拼团总人数")
    private Integer people;

    @ApiModelProperty(value = "拼团产品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "开始时间")
    private Date createTime;

    @ApiModelProperty(value = "结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "团长id 0为团长")
    private Long kId;


    @ApiModelProperty(value = "状态1进行中2已完成3未完成")
    private Integer status;

}
