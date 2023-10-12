package co.yixiang.modules.activity.vo;


import co.yixiang.modules.activity.service.dto.SeckillTimeDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SeckillConfigVo {

    @ApiModelProperty(value = "秒杀产品时间信息")
    private List<SeckillTimeDto> seckillTime;

    private String lovely;

    @ApiModelProperty(value = "秒杀产品时间索引")
    private Integer seckillTimeIndex;
}
