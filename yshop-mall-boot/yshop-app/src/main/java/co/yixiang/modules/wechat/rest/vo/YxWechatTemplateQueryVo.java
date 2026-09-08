package co.yixiang.modules.wechat.rest.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 微信模板 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-10
 */
@Data
@Schema(description = "YxWechatTemplateQueryVo对象")
public class YxWechatTemplateQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "模板id")
    private Integer id;

    @Schema(description = "模板编号")
    private String tempkey;

    @Schema(description = "模板名")
    private String name;

    @Schema(description = "回复内容")
    private String content;

    @Schema(description = "模板ID")
    private String tempid;

    @Schema(description = "添加时间")
    private String addTime;

    @Schema(description = "状态")
    private Integer status;

}
