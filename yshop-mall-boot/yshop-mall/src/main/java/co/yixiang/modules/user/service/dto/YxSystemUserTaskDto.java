/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxSystemUserTaskDto implements Serializable {
    private Integer id;

    // 任务名称
    private String name;

    // 配置原名
    private String realName;

    // 任务类型
    private String taskType;

    // 限定数
    private Integer number;

    // 等级id
    private Integer levelId;

    private String levalName;

    // 排序
    private Integer sort;

    // 是否显示
    private Integer isShow;

    // 是否务必达成任务,1务必达成,0=满足其一
    private Integer isMust;

    // 任务说明
    private String illustrate;

    // 新增时间
    private Integer addTime;
}
