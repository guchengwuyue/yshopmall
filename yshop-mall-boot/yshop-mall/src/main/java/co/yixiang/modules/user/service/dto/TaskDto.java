package co.yixiang.modules.user.service.dto;


import co.yixiang.modules.user.vo.YxSystemUserTaskQueryVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TaskDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/6
 **/
@Data
public class TaskDto implements Serializable {
    private List<YxSystemUserTaskQueryVo> list;
    private Long reachCount;
    private List<YxSystemUserTaskQueryVo> task;
}
