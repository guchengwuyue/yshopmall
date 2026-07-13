package co.yixiang.modules.sales.param;

import lombok.Data;

/**
 * @author : gzlv 2021/6/29 5:25
 */
@Data
public class SalesCheckDto {

    /**
     * 售后id
     */
    private Long salesId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 审核状态0成功1失败
     */
    private Integer approvalStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 地址
     */
    private String address;
}
