<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import com.github.rapid.common.util.page.Page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

<#include "/java_imports.include">

/**
 * [${table.tableAlias}] 的业务操作
 * 
<#include "/java_description.include">
 */
public interface ${className}Service {

	/** 
	 * 创建${className}
	 **/
	public ${className} create(${className} ${classNameLower});
	
	/** 
	 * 更新${className}
	 **/	
    public ${className} update(${className} ${classNameLower});
    
	/** 
	 * 删除${className}
	 **/
    public void removeById(<@generateArguments table.pkColumns/>);
    
	/** 
	 * 根据ID得到${className}
	 **/    
    public ${className} getById(<@generateArguments table.pkColumns/>);
    
	/** 
	 * 分页查询: ${className}
	 **/      
	public Page<${className}> findPage(${className}Query query);
	
<#list table.columns as column>
	<#if column.unique && !column.pk>
	/** 
	 * 根据${column.columnName}(${column.columnAlias}) 查找 ${className}
	 **/ 	
	public ${className} getBy${column.columnName}(${column.primitiveJavaType} ${column.columnNameFirstLower});
	
	</#if>
</#list>
    
}
