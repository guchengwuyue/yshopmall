package co.yixiang.modules.wechat.rest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "YxWechatTemplateQueryVo对象", description = "微信模板查询参数")
public class YxWechatTemplateQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板id")
    private Integer id;

    @ApiModelProperty(value = "模板编号")
    private String tempkey;

    @ApiModelProperty(value = "模板名")
    private String name;

    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "模板ID")
    private String tempid;

    @ApiModelProperty(value = "添加时间")
    private String addTime;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
