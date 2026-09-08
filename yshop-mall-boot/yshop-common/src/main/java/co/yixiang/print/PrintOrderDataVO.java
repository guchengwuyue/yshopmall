/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.print;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：LionCity
 * @date ：Created in 2020-05-11 10:33
 * @description：打印数据VO
 * @modified By：
 * @version: V1.0
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintOrderDataVO implements Serializable{
    @Schema(description = "订单号")
    private String orderId;
    @Schema(description = "实际支付金额")
    private BigDecimal payPrice;
    @Schema(description = "用户姓名")
    private String realName;
    @Schema(description = "详细地址")
    private String userAddress;
    @Schema(description = "用户电话")
    private String userPhone;
    @Schema(description = "备注")
    private String mark;
    @Schema(description = "门店名称")
    private String storeName;
}
