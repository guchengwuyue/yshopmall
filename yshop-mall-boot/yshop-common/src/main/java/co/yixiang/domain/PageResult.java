package co.yixiang.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：LionCity
 * @date ：Created in 2020-08-04 16:55
 * @description：分页参数返回
 * @modified By：
 * @version: V1.0
 */
@Data
@Accessors(chain = true)
@Builder
public class PageResult<T> implements Serializable {


    @ApiModelProperty("总数量")
    private long totalElements;

    @ApiModelProperty("内容")
    private List<T> content;

    public PageResult(long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public PageResult() {
    }
}
