package co.yixiang.modules.template.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 运费模板对象 yx_shipping_templates
 * 
 * @author hupeng
 * @date 2020-05-23
 */
@Getter
@Setter
public class ShippingTemplatesDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Integer id;

    /** 模板名称 */
    @NotBlank(message = "模板名称不能为空")
    private String name;

    /** 计费方式 */
    private Integer type;

    /** 地域以及费用 */
    @NotNull(message = "请设置地域")
    @JsonProperty(value = "region_info")
    private List<RegionInfoDto> regionInfo;

    /** 指定包邮开关 */
    private Integer appoint;

    /** 指定包邮内容 */
    @JsonProperty(value = "appoint_info")
    private List<AppointInfoDto> appointInfo;

    /** 排序 */
    private Long sort;



}
