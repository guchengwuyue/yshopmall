/**
 * Copyright 2018 bejson.com
 */
/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.tools.express.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "物流运单号")
    private String LogisticCode;

    @JsonProperty("ShipperCode")
    @ApiModelProperty(value = "快递公司编码")
    private String ShipperCode;


    @JsonProperty("Traces")
    @ApiModelProperty(value = "物流轨迹")
    private List<Traces> Traces;


    @JsonProperty("State")
    @ApiModelProperty(value = "物流状态：2-在途中,3-签收,4-问题件")
    private String State;

    @JsonProperty("StateEx")
    private String StateEx;

    @JsonProperty("Location")
    private String Location;

    @JsonProperty("EBusinessID")
    @ApiModelProperty(value = "用户ID")
    private String EBusinessID;


    @JsonProperty("Success")
    @ApiModelProperty(value = "成功与否")
    private boolean Success;


    @JsonProperty("Reason")
    @ApiModelProperty(value = "失败原因")
    private String Reason;

    private String ShipperName;

    @JsonProperty("OrderCode")
    @ApiModelProperty(value = "订单编号")
    private String OrderCode;


}
