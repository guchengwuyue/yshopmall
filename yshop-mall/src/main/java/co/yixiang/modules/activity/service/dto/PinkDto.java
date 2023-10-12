package co.yixiang.modules.activity.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PinkDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/19
 **/
@Data
public class PinkDto implements Serializable {

    @ApiModelProperty(value = "拼团ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "拼团总人数")
    private Integer people;

    @ApiModelProperty(value = "拼团产品单价")
    private Double price;

    @ApiModelProperty(value = "拼团结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "拼团用户昵称")
    private String nickname;

    @ApiModelProperty(value = "拼团用户头像")
    private String avatar;

    @ApiModelProperty(value = "参与的拼团的id集合")
    private String count;

    @ApiModelProperty(value = "拼团时效：小时")
    private String h;

    @ApiModelProperty(value = "拼团时效：分钟")
    private String i;

    @ApiModelProperty(value = "拼团时效：秒")
    private String s;


}
