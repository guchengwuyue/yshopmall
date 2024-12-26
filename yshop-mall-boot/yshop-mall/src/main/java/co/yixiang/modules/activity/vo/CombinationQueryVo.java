package co.yixiang.modules.activity.vo;

import lombok.Data;

import java.util.List;

@Data
public class CombinationQueryVo {

    private List<YxStoreCombinationQueryVo> storeCombinationQueryVos;

    private Long lastPage;

}
