package co.yixiang.modules.activity.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SeckillTimeDto {

    private Integer id;
    /**
     * 00:00
     */
    @ApiModelProperty(value = "秒杀产品时间：00:00")
    private String time;

    /**
     *状态
     */
    @ApiModelProperty(value = "秒杀产品状态显示中文值")
    private String state;

    @ApiModelProperty(value = "秒杀产品状态")
    private Integer status;

    @ApiModelProperty(value = "秒杀产品停止时间")
    private Integer stop;
}
