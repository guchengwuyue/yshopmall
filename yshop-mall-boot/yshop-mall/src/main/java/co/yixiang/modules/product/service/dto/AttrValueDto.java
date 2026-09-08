package co.yixiang.modules.product.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName AttrValueDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/23
 **/
@Data
public class AttrValueDto {

    @Schema(description = "属性")
    private String attr;

    @Schema(description = "是否选择")
    private Boolean check = false;
}
