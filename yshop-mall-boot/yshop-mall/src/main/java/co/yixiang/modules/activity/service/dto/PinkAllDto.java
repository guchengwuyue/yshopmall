package co.yixiang.modules.activity.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName 团长拼团数据PinkAllDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/20
 **/
@Getter
@Setter
@Builder
public class PinkAllDto {
    List<PinkDto> list; // list-团长参与的列表
    List<Long> pindAll; //pindAll-参与的拼团的id 集合
}
