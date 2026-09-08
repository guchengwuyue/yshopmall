package co.yixiang.modules.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxUserAddressQueryVo对象")
public class YxUserAddressQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户地址id")
    private Long id;

    @Schema(description = "用户id")
    private Long uid;

    @Schema(description = "收货人姓名")
    private String realName;

    @Schema(description = "收货人电话")
    private String phone;

    @Schema(description = "收货人所在省")
    private String province;

    @Schema(description = "收货人所在市")
    private String city;

    @Schema(description = "收货人所在区")
    private String district;

    @Schema(description = "收货人详细地址")
    private String detail;

    @Schema(description = "邮编")
    private Integer postCode;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "是否默认")
    private Integer isDefault;


}
