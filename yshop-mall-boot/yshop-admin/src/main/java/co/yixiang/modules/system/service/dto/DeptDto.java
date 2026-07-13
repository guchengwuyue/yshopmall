/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-14
*/
@Getter
@Setter
public class DeptDto implements Serializable {

    /** ID */
    private Long id;

    /** 名称 */
    private String name;

    /** 上级部门 */
    private Long pid;

    /** 状态 */
    private Boolean enabled;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;

    /** 创建日期 */
    private Timestamp createTime;

    public String getLabel() {
        return name;
    }
}
