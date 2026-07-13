/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package ${package}.repository;

import ${package}.domain.${className};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author ${author}
* @date ${date}
*/
public interface ${className}Repository extends JpaRepository<${className}, ${pkColumnType}>, JpaSpecificationExecutor<${className}> {
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>
    /**
    * 根据 ${column.capitalColumnName} 查询
    * @param ${column.columnName} /
    * @return /
    */
    ${className} findBy${column.capitalColumnName}(${column.columnType} ${column.columnName});
        </#if>
    </#list>
</#if>
}
