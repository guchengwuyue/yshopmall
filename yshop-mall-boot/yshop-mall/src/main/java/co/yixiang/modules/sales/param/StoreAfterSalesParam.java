package co.yixiang.modules.sales.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author : gzlv 2021/6/27 16:36
 */
@Data
public class StoreAfterSalesParam {

    /**
     * 订单号
     */
    @NotBlank
    private String orderCode;

    /**
     * 服务类型 0仅退款1退货退款
     */
    @NotBlank
    private Integer serviceType;

    /**
     * 申请原因
     */
    @NotBlank
    private String reasonForApplication;

    /**
     * 申请说明
     */
    private String applicationInstructions;

    /**
     * 申请说明图片
     */
    private String applicationDescriptionPicture;

    /**
     * 商品数据
     */
    @NotBlank
    private List<ProsuctParam> productParamList;

}
