package co.yixiang.modules.wechat.rest.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 微信模板 查询参数对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "YxWechatTemplateQueryParam对象")
public class YxWechatTemplateQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
