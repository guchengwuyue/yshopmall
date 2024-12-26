package co.yixiang.modules.user.service.dto;


import co.yixiang.modules.user.vo.YxSystemUserLevelQueryVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserLevelDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/6
 **/
@Data
public class UserLevelDto implements Serializable {
    private List<YxSystemUserLevelQueryVo> list;
    private TaskDto task;
}
