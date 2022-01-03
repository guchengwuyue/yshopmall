/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.print;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "打印机编号")
    private String driverNo;
    @ApiModelProperty(value = "门店列表")
    private Integer storeId;
    @ApiModelProperty(hidden = true)
    long startSecond;
    @ApiModelProperty(hidden = true)
    long endSecond;
}
