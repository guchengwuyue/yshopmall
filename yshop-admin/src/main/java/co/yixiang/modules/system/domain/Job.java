/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("job")
public class Job extends BaseDomain {

    /** 岗位ID */
    @TableId
    private Long id;

    /** 岗位名称 */
    @NotBlank(message = "岗位名称不能为空")
    private String name;


    /** 岗位状态 */
    private Boolean enabled;

    @TableField(exist = false)
    private Dept dept;

    /** 岗位排序 */
    private Long sort;


    /** 部门ID */
    private Long deptId;




    public void copy(Job source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
