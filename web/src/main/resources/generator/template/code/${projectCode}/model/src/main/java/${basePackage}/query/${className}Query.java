<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
package ${basePackage}.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import java.io.Serializable;
import java.util.*;
import ${basePackage}.query.PageQuery;

/**
<#include "/java_description.include">
 */
public class ${className}Query extends PageQuery implements Serializable {
	
    private static final long serialVersionUID = 3148176768559230877L;
    
	<@generateFields/>
	
	<@generateProperties/>

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

<#macro generateFields>
	<#list table.columns as column>
	<#if column.comment??>
	/** ${column.comment} **/
	</#if>
	<#if column.isDateTimeColumn && !column.contains("begin, start, end")>
	private ${column.javaType} ${column.columnNameFirstLower}Begin;
	private ${column.javaType} ${column.columnNameFirstLower}End;
	<#else>
	private ${column.javaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
</#macro>

<#macro generateProperties>
	<#list table.columns as column>
	<#assign javaType = column.javaType> 
	<#assign columnNameFirstUpper = column.columnNameFirstUpper> 
	<#assign columnNameFirstLower = column.columnNameFirstLower> 
	<#if column.isDateTimeColumn && !column.contains("begin, start, end")>
	public ${javaType} get${columnNameFirstUpper}Begin() {
		return this.${columnNameFirstLower}Begin;
	}
	
	public void set${columnNameFirstUpper}Begin(${javaType} ${columnNameFirstLower}Begin) {
		this.${columnNameFirstLower}Begin = ${columnNameFirstLower}Begin;
	}	
	
	public ${javaType} get${columnNameFirstUpper}End() {
		return this.${columnNameFirstLower}End;
	}
	
	public void set${columnNameFirstUpper}End(${javaType} ${columnNameFirstLower}End) {
		this.${columnNameFirstLower}End = ${columnNameFirstLower}End;
	}
	
	<#else>
	public ${javaType} get${columnNameFirstUpper}() {
		return this.${columnNameFirstLower};
	}
	
	public void set${columnNameFirstUpper}(${javaType} ${columnNameFirstLower}) {
		this.${columnNameFirstLower} = ${columnNameFirstLower};
	}
	
	</#if>	
	</#list>
</#macro>



