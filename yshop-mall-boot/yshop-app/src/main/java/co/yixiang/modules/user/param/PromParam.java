package co.yixiang.modules.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PromParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/12
 **/
@Data
public class PromParam  implements Serializable {

    @ApiModelProperty(value = "推荐人级别 0一级推荐人 1二级推荐人")
    private Integer grade;

    @ApiModelProperty(value = "关键字搜索")
    private String  keyword;

    @ApiModelProperty(value = "页码")
    private Integer limit;

    @ApiModelProperty(value = "页大小")
    private Integer page;

    @ApiModelProperty(value = "排序")
    private String  sort;
}
