<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>  
package ${basePackage}.service;

<#include "/java_imports.include">
import java.util.List;
import ${basePackage}.model.Page;
import ${basePackage}.model.${className};
import ${basePackage}.query.${className}Query;

/**
 * tableName: ${table.sqlName}
 * [${className}] 的Service操作
<#include "/java_description.include">
*/
public interface ${className}Service {
	
	public int insert(${className} entity);

	public int update(${className} entity);
	
	public int[] batchInsert(List<${className}> entities);
	
	public int[] batchUpdate(List<${className}> entities);

	public int deleteById(<@generateArguments table.pkColumns/>);
	
	public ${className} getById(<@generateArguments table.pkColumns/>);
	
	public List<${className}> getList(${className}Query query);	
	
	public int getCount(${className}Query query);
	
	public Page<${className}> getPage(${className}Query query);
	
}
