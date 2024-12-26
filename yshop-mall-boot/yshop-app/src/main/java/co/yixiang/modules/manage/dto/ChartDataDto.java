package co.yixiang.modules.manage.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChartDataDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/25
 **/
@Data
public class ChartDataDto implements Serializable {
    private Double num;
    private String time;
}
