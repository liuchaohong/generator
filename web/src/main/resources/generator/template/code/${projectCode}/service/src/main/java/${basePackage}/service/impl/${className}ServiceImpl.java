<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basePackage}.service.impl;

<#include "/java_imports.include">
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ${basePackage}.common.util.ListSortUtil;
import ${basePackage}.model.Page;
import ${basePackage}.service.${className}Service;
import ${basePackage}.model.${className};
import ${basePackage}.query.${className}Query;
import ${basePackage}.dao.${className}Dao;

/**
 * tableName: ${table.sqlName}
 * [${className}] 的Service操作 
 * 
<#include "/java_description.include">
*/
@Transactional
@Service("${classNameFirstLower}Service")
public class ${className}ServiceImpl implements ${className}Service {
	
	@Autowired
	private ${className}Dao ${classNameFirstLower}Dao;
	
	public int insert(${className} entity){
	    Assert.notNull(entity, "'entity' must be not null");
	    return ${classNameFirstLower}Dao.insert(entity);		
	}

	public int update(${className} entity){
	    Assert.notNull(entity, "'entity' must be not null");
	    return ${classNameFirstLower}Dao.update(entity);		
	}
	
	public int[] batchInsert(List<${className}> entities){
	    Assert.notNull(entities, "'entities' must be not null");
	    return ${classNameFirstLower}Dao.batchInsert(entities);		
	}
	
	public int[] batchUpdate(List<${className}> entities){
	    Assert.notNull(entities, "'entities' must be not null");
	    return ${classNameFirstLower}Dao.batchUpdate(entities);				
	}

	public int deleteById(<@generateArguments table.pkColumns/>){
		<#list table.pkColumns as column>
         Assert.notNull(${column.columnNameFirstLower}, "'${column.columnNameFirstLower}' must be not null");
		</#list>
		return ${classNameFirstLower}Dao.deleteById(<@generatePassingParameters table.pkColumns/>);
	}
	
	public ${className} getById(<@generateArguments table.pkColumns/>){
		<#list table.pkColumns as column>
        Assert.notNull(${column.columnNameFirstLower}, "'${column.columnNameFirstLower}' must be not null");
		</#list>
		return ${classNameFirstLower}Dao.getById(<@generatePassingParameters table.pkColumns/>);
	}
	
	public List<${className}> getList(${className}Query query){
	    Assert.notNull(query, "'query' must be not null");
		return ${classNameFirstLower}Dao.getList(query);
	}
	
	public int getCount(${className}Query query){
	    Assert.notNull(query, "'query' must be not null");
		return ${classNameFirstLower}Dao.getCount(query);
	}
	
	public Page<${className}> getPage(${className}Query query) {
		Assert.notNull(query, "'query' must be not null");
		Page<${className}> page = new Page<${className}>();
		List<${className}> list = getList(query);
		Integer total = getCount(query);
		if (StringUtils.isNotBlank(query.getSort()) && StringUtils.isNotBlank(query.getOrder())) {//排序
			   ListSortUtil<${className}> sortList = new ListSortUtil<${className}>(); 
			   sortList.sort(list, query.getSort(), query.getOrder()); 
		}
		page.setRows(list);
		page.setTotal(total);
		return page;
	}
}
