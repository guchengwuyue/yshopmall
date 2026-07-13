package co.yixiang.modules.user.vo;

import co.yixiang.modules.user.service.dto.MUserBillDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BillVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/12
 **/
@Data
public class BillVo {
    private String time;
    @JsonIgnore
    private String ids;
    private List<MUserBillDto> list;
}
