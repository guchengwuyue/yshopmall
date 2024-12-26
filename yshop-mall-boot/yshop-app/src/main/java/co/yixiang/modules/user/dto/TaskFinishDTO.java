package co.yixiang.modules.user.dto;


import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName TaskFinishDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/6
 **/
@Data
public class TaskFinishDTO implements Serializable {
    private String addTime;
    private String title;
    private Integer number;
}
