<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basePackage}.dao.impl;

<#include "/java_imports.include">
import ${basePackage}.dao.${className}Dao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ${basePackage}.model.${className};
import ${basePackage}.service.${className}Service;

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
	    return ${classNameFirstLower}Dao.insert(${classNameFirstLower});		
	}

	public int update(${className} entity){
	    Assert.notNull(entity, "'entity' must be not null");
	    return ${classNameFirstLower}Dao.update(${classNameFirstLower});		
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
	
}
