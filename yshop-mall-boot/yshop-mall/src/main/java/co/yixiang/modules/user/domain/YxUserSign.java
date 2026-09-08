package co.yixiang.modules.user.domain;


import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 签到记录表
 * </p>
 *
 * @author hupeng
 * @since 2019-12-05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxUserSign对象")
public class YxUserSign extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户uid")
    private Long uid;

    @Schema(description = "签到说明")
    private String title;

    @Schema(description = "获得积分")
    private Integer number;

    @Schema(description = "剩余积分")
    private Integer balance;

}
