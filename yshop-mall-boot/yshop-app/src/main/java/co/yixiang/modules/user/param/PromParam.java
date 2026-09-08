package co.yixiang.modules.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PromParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/12
 **/
@Data
public class PromParam  implements Serializable {

    @Schema(description = "推荐人级别 0一级推荐人 1二级推荐人")
    private Integer grade;

    @Schema(description = "关键字搜索")
    private String  keyword;

    @Schema(description = "页码")
    private Integer limit;

    @Schema(description = "页大小")
    private Integer page;

    @Schema(description = "排序")
    private String  sort;
}
