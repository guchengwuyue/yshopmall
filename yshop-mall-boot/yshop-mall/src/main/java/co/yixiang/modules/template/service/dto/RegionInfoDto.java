package co.yixiang.modules.template.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RegionInfoDto
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/5/25
 **/
@Getter
@Setter
@ToString
public class RegionInfoDto {

    @NotBlank(message = "请填写可配送区域")
    private String regionName;

    @NotBlank(message = "请填写首件")
    private String first;

    @NotBlank(message = "请填写首件价格")
    private String price;

    @NotBlank(message = "请填写续件")
    @JsonProperty("_continue")
    private String _continue;

    @NotBlank(message = "请填写续件价格")
    private String continue_price;

    private List<RegionDto> region;
}
