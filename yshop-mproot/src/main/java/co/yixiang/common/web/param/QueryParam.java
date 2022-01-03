/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.common.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("查询参数对象")
public abstract class QueryParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @ApiModelProperty(value = "页码,默认为1")
    private Integer page = 1;
    @ApiModelProperty(value = "页大小,默认为10")
    private Integer limit = 10;
    @ApiModelProperty(value = "搜索字符串")
    private String keyword;

    public void setCurrent(Integer current) {
        if (current == null || current <= 0) {
            this.page = 1;
        } else {
            this.page = current;
        }
    }

    public void setSize(Integer size) {
        if (size == null || size <= 0) {
            this.limit = 10;
        } else {
            this.limit = size;
        }
    }

}
