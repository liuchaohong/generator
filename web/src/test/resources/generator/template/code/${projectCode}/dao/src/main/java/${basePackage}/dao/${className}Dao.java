<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>  
package ${basePackage}.dao;

<#include "/java_imports.include">

import java.util.List;

import ${basePackage}.model.*;
import ${basePackage}.query.*;

/**
 * tableName: ${table.sqlName}
 * [${className}] 的Dao操作
<#include "/java_description.include">
*/
public interface ${className}Dao {
	
	public int insert(${className} entity);

	public int update(${className} entity);
	
	public int[] batchInsert(List<${className}> entities);
	
	public int[] batchUpdate(List<${className}> entities);

	public int deleteById(<@generateArguments table.pkColumns/>);
	
	public ${className} getById(<@generateArguments table.pkColumns/>);
	
	public List<${className}> getList(${className}Query query);	
	
	public int getCount(${className}Query query);
	
}
