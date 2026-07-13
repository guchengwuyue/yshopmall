package co.yixiang.modules.product.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : gzlv 2021/6/26 19:17
 */
@Getter
@Setter
@ApiModel("查询参数对象")
public class CollectDelFootParam {

    private List<Long> ids;
}
