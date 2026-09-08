/**
 * Copyright 2018 bejson.com
 */
/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.tools.express.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2018-07-19 22:27:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ExpressInfo {

    @JsonProperty("LogisticCode")
    @Schema(description = "物流运单号")
    private String LogisticCode;

    @JsonProperty("ShipperCode")
    @Schema(description = "快递公司编码")
    private String ShipperCode;


    @JsonProperty("Traces")
    @Schema(description = "物流轨迹")
    private List<Traces> Traces;


    @JsonProperty("State")
    @Schema(description = "物流状态：2-在途中,3-签收,4-问题件")
    private String State;

    @JsonProperty("StateEx")
    private String StateEx;

    @JsonProperty("Location")
    private String Location;

    @JsonProperty("EBusinessID")
    @Schema(description = "用户ID")
    private String EBusinessID;


    @JsonProperty("Success")
    @Schema(description = "成功与否")
    private boolean Success;


    @JsonProperty("Reason")
    @Schema(description = "失败原因")
    private String Reason;

    private String ShipperName;

    @JsonProperty("OrderCode")
    @Schema(description = "订单编号")
    private String OrderCode;


}
