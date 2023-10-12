package co.yixiang.modules.shop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：LionCity
 * @date ：Created in 2020-12-09 10:47
 * @description：
 * @modified By：
 * @version:
 */
@Data
@ApiModel(description = "app校验升级")
public class AppCheckVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类型 1101 安卓   1102 ios")
    private String type;

    @ApiModelProperty(value = "app版本名称")
    private String versionName;
}
