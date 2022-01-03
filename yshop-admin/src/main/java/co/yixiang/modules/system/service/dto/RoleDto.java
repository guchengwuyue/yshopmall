/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.system.service.dto;

import co.yixiang.modules.system.domain.Menu;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author hupeng
 * @date 2020-05-14
 */
@Data
public class RoleDto implements Serializable {

    /** ID */
    private Long id;

    /** 名称 */
    private String name;

    /** 备注 */
    private String remark;

    /** 数据权限 */
    private String dataScope;

    /** 角色级别 */
    private Integer level;

    private Set<Menu> menus;

    /** 创建日期 */
    private Timestamp createTime;

    /** 功能权限 */
    private String permission;
}
