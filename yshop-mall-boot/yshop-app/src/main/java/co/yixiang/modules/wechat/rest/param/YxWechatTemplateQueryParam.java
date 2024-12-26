package co.yixiang.modules.wechat.rest.param;

import co.yixiang.common.web.param.QueryParam;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="YxWechatTemplateQueryParam对象", description="微信模板查询参数")
public class YxWechatTemplateQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
