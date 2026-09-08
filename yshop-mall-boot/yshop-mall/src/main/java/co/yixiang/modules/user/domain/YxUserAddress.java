package co.yixiang.modules.user.domain;


import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxUserAddress对象")
public class YxUserAddress extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户地址id")
    @TableId(value = "id", type = IdType.AUTO)
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

    private Integer cityId;

    @Schema(description = "收货人所在区")
    private String district;

    @Schema(description = "收货人详细地址")
    private String detail;

    @Schema(description = "邮编")
    private String postCode;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "是否默认")
    private Integer isDefault;

}
