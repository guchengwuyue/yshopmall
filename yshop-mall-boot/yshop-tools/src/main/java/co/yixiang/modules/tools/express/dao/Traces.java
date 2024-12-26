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
@Data
public class Traces {

    @JsonProperty("AcceptStation")
    @ApiModelProperty(value = "描述")
    private String AcceptStation;

    @JsonProperty("AcceptTime")
    @ApiModelProperty(value = "时间")
    private String AcceptTime;

    @JsonProperty("Action")
    private String Action;
    @JsonProperty("Location")
    private String Location;


}
