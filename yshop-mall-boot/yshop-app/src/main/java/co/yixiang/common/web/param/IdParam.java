package co.yixiang.common.web.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@Schema(description = "ID参数")
public class IdParam implements Serializable {
    private static final long serialVersionUID = -5353973980674510450L;

    @NotBlank(message="ID不能为空")
    @Schema(description = "地址ID")
    private String id;
}
