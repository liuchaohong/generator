<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
package ${basePackage}.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.io.Serializable;

/**
<#include "/java_description.include">
 */
public class ${className} implements Serializable {
	
    private static final long serialVersionUID = 3148176768559230877L;
    
	<@generateFields/>
	
	<@generateProperties/>

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof ${className} == false) return false;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnNameFirstUpper}(), other.get${column.columnNameFirstUpper}())
			</#list>
			.isEquals();
	}
}

<#macro generateFields>
	<#list table.columns as column>
	<#if column.comment??>
	/** ${column.comment} **/
	</#if>
	private ${column.javaType} ${column.columnNameFirstLower};
	</#list>
</#macro>

<#macro generateProperties>
	<#list table.columns as column>
	<#assign javaType = column.javaType> 
	<#assign columnNameFirstUpper = column.columnNameFirstUpper> 
	<#assign columnNameFirstLower = column.columnNameFirstLower> 
	public ${javaType} get${columnNameFirstUpper}() {
		return this.${columnNameFirstLower};
	}
	
	public void set${columnNameFirstUpper}(${javaType} ${columnNameFirstLower}) {
		this.${columnNameFirstLower} = ${columnNameFirstLower};
	}
	
	</#list>
</#macro>



