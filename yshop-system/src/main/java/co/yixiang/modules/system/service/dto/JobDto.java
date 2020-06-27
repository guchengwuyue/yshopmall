/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package co.yixiang.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
public class JobDto implements Serializable {

    private Long id;

    private Long sort;

    private String name;

    private Boolean enabled;

    private DeptDto dept;

    private String deptSuperiorName;

    private Timestamp createTime;

//    public JobDto(String name, Boolean enabled) {
//        this.name = name;
//        this.enabled = enabled;
//    }
}
