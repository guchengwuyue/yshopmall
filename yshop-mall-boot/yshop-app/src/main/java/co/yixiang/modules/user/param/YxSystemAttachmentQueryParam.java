package co.yixiang.modules.user.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 附件管理表 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxSystemAttachmentQueryParam对象")
public class YxSystemAttachmentQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
