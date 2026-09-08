package co.yixiang.modules.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 门店自提 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2020-03-04
 */
@Data
@Schema(description = "YxSystemStoreQueryVo对象")
public class YxSystemStoreQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "门店ID")
    private Integer id;

    @Schema(description = "门店名称")
    private String name;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "省市区")
    private String address;

    @Schema(description = "详细地址")
    private String detailedAddress;

    @Schema(description = "门店logo")
    private String image;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "核销有效日期")
    private String validTime;

    private String latlng;

    public String getLatlng(){
        return latitude+","+longitude;
    }

    @Schema(description = "每日营业开关时间")
    private String dayTime;

    @Schema(description = "距离")
    private String distance;

}
