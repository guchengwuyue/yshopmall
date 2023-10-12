package co.yixiang.modules.mp.vo;

import co.yixiang.modules.mp.service.dto.YxWechatLiveDto;
import lombok.Data;

import java.util.List;

@Data
public class WechatLiveVo {

    private List<YxWechatLiveDto> content;

    private Long totalElements;

    private Integer pageNumber;

    private Integer lastPage;


}
