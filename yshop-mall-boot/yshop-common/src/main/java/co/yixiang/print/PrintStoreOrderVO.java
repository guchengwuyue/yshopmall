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

/**
 * @author ：LionCity
 * @date ：Created in 2020-05-11 11:01
 * @description：门店销售数据
 * @modified By：
 * @version: V1.0
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintStoreOrderVO implements Serializable {
    @Schema(description = "开始时间")
    private String startTime;
    @Schema(description = "结束时间")
    private String endTime;
    @Schema(description = "打印机编号")
    private String driverNo;
    @Schema(description = "门店列表")
    private Integer storeId;
    @Schema(hidden = true)
    Long startSecond;
    @Schema(hidden = true)
    Long endSecond;
}
