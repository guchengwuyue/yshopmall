package co.yixiang.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户地址表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-28
 */
@Data
@ApiModel(value = "YxUserAddressQueryVo对象", description = "用户地址表查询参数")
public class YxUserAddressQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户地址id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "收货人姓名")
    private String realName;

    @ApiModelProperty(value = "收货人电话")
    private String phone;

    @ApiModelProperty(value = "收货人所在省")
    private String province;

    @ApiModelProperty(value = "收货人所在市")
    private String city;

    @ApiModelProperty(value = "收货人所在区")
    private String district;

    @ApiModelProperty(value = "收货人详细地址")
    private String detail;

    @ApiModelProperty(value = "邮编")
    private Integer postCode;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;


}
