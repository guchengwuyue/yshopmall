package co.yixiang.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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


    @Schema(description = "总数量")
    private long totalElements;

    @Schema(description = "内容")
    private List<T> content;

    public PageResult(long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public PageResult() {
    }
}
