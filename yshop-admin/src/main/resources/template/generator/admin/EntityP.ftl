/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制
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
<#if hasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
    import java.math.BigDecimal;
</#if>
import java.io.Serializable;

/**
* @author ${author}
* @date ${date}
*/
@Data
@TableName("${tableName}")
public class ${className} implements Serializable {
<#if columns??>
    <#list columns as column>

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
        <#if column.dateAnnotation??>
            <#if column.dateAnnotation = 'CreationTimestamp'>
                @CreationTimestamp
            <#else>
                @UpdateTimestamp
            </#if>
        </#if>
        <#if column.changeColumnName = 'updateTime'|| column.changeColumnName = 'updateDate'>
            @TableField(fill= FieldFill.INSERT_UPDATE)
        </#if>
        <#if column.changeColumnName = 'createTime' || column.changeColumnName = 'createDate'>
            @TableField(fill= FieldFill.INSERT)
        </#if>
        <#if column.changeColumnName = 'delFlag'>
            @TableLogic
            @TableField(fill=FieldFill.INSERT_UPDATE)
            private Boolean ${column.changeColumnName};
        <#else>
            private ${column.columnType} ${column.changeColumnName};
        </#if>

    </#list>
</#if>

public void copy(${className} source){
BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
}
}
