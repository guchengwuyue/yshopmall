/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxSystemGroupDataDto implements Serializable {

    // 组合数据详情ID
    private Integer id;

    // 对应的数据名称
    private String groupName;

    // 数据组对应的数据值（json数据）
    private String value;

    private Map<String,Object> map;

    // 添加数据时间
    private Integer addTime;

    // 数据排序
    private Integer sort;

    // 状态（1：开启；2：关闭；）
    private Integer status;
}
