/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package ${package}.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
<#if isNotNullColumns??>
import javax.validation.constraints.*;
</#if>
<#if hasDateAnnotation>
</#if>
<#if hasDateTime>
import java.util.Date;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import co.yixiang.domain.BaseDomain;

/**
* @author ${author}
* @date ${date}
*/
@Data
@TableName("${tableName}")
public class ${className} extends BaseDomain {
<#if columns??>
    <#list columns as column>
    <#if column.changeColumnName != 'isDel' && column.changeColumnName != 'createTime' && column.changeColumnName != 'updateTime' >
    <#if column.remark != ''>
    /** ${column.remark} */
    </#if>
    <#if column.columnKey = 'PRI'>
    @TableId
    </#if>
    <#if column.istNotNull && column.columnKey != 'PRI'>
        <#if column.columnType = 'String'>
    @NotBlank
        <#else>
    @NotNull
        </#if>
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#if>

    </#list>
</#if>

    public void copy(${className} source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
