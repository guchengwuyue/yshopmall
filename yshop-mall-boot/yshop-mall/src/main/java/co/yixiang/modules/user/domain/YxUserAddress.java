package co.yixiang.modules.user.domain;


import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author hupeng
 * @since 2019-10-28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "YxUserAddress对象", description = "用户地址表")
public class YxUserAddress extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户地址id")
    @TableId(value = "id", type = IdType.AUTO)
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

    private Integer cityId;

    @ApiModelProperty(value = "收货人所在区")
    private String district;

    @ApiModelProperty(value = "收货人详细地址")
    private String detail;

    @ApiModelProperty(value = "邮编")
    private String postCode;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;

}
