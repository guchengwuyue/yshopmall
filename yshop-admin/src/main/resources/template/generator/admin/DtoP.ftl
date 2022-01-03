/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制
*/
package ${package}.service.dto;

import lombok.Data;
<#if hasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
    import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if !auto && pkColumnType == 'Long'>
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>

/**
* @author ${author}
* @date ${date}
*/
@Data
public class ${className}Dto implements Serializable {
<#if columns??>
    <#list columns as column>

        <#if column.remark != ''>
            /** ${column.remark} */
        </#if>
        <#if column.columnKey = 'PRI'>
            <#if !auto && pkColumnType = 'Long'>
                /** 防止精度丢失 */
                @JsonSerialize(using= ToStringSerializer.class)
            </#if>
        </#if>
        <#if column.changeColumnName != 'delFlag'>
            private ${column.columnType} ${column.changeColumnName};
        </#if>
    </#list>
</#if>
}
