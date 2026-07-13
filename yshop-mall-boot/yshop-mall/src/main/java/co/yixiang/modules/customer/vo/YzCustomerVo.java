package co.yixiang.modules.customer.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单通知用户表 接收订单通知的用户 查询结果对象
 * </p>
 *
 * @author LionCity
 * @date 2020-04-02
 */
@Data
@ApiModel(description = "订单通知用户表")
public class YzCustomerVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "创建时间 创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间 最后更新时间")
    private Date updateTime;

}