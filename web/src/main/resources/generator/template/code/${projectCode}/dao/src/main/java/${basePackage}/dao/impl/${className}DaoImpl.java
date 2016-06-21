<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameFirstLower = table.classNameFirstLower> 
package ${basePackage}.dao.impl;

<#include "/java_imports.include">
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import com.github.rapid.common.util.ObjectUtil;
import com.github.rapid.common.beanutils.PropertyUtils;
import com.github.rapid.common.jdbc.dao.support.BaseSpringJdbcDao;
import ${basePackage}.dao.${className}Dao;
import ${basePackage}.model.${className};
import ${basePackage}.query.${className}Query;

/**
 * tableName: ${table.sqlName}
 * [${className}] 的Dao操作 
 * 
<#include "/java_description.include">
*/
@Repository("${classNameFirstLower}Dao")
public class ${className}DaoImpl extends BaseSpringJdbcDao implements ${className}Dao {
	
	private RowMapper<${className}> entityRowMapper = new BeanPropertyRowMapper<${className}>(getEntityClass());
	
	static final private String COLUMNS = "<#list table.columns as column>${column.sqlName}<#if column_has_next>, </#if></#list>";
	static final private String SELECT_FROM = "select " + COLUMNS + " from ${table.sqlName}";
	
	@Override
	public Class<${className}> getEntityClass() {
		return ${className}.class;
	}
	
	public RowMapper<${className}> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public int insert(${className} entity){
		String sql = "insert into ${table.sqlName} " 
				 + " (<#list table.columns as column>${column.sqlName}<#if column_has_next>, </#if></#list>) " 
				 + " values "
				 + " (<#list table.columns as column>:${column.columnNameFirstLower}<#if column_has_next>, </#if></#list>)";
		return insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql); //手工分配		
	}

	public int update(${className} entity){
		List<${className}> entities = Arrays.asList(entity);
		return batchUpdate(entities).length;
	}
	
	public int[] batchInsert(List<${className}> entities){
		String sql = "insert into ${table.sqlName} " 
				 + " (<#list table.columns as column>${column.sqlName}<#if column_has_next>, </#if></#list>) " 
				 + " values "
				 + " (<#list table.columns as column>:${column.columnNameFirstLower}<#if column_has_next>, </#if></#list>)";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(entities.toArray());
		return getNamedParameterJdbcTemplate().batchUpdate(sql, params);
	}
	
	public int[] batchUpdate(List<${className}> entities){
		String sql = "update ${table.sqlName} set "
				+ " <#list table.notPkColumns as column>${column.sqlName} = :${column.columnNameFirstLower}<#if column_has_next>, </#if></#list> "
				+ " where <#list table.pkColumns as column>${column.sqlName} = :${column.columnNameFirstLower} <#if column_has_next> and </#if></#list>";		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(entities.toArray());
		return getNamedParameterJdbcTemplate().batchUpdate(sql, params);		
	}

	public int deleteById(<@generateArguments table.pkColumns/>){
		String sql = "delete from ${table.sqlName} where <#list table.pkColumns as column>${column.sqlName} = ?<#if column_has_next> and </#if></#list>";
		return  getJdbcTemplate().update(sql, <@generatePassingParameters table.pkColumns/>);
	}
	
	public ${className} getById(<@generateArguments table.pkColumns/>){
		String sql = SELECT_FROM + " where <#list table.pkColumns as column>${column.sqlName} = ?<#if column_has_next> and </#if></#list>";
		return (${className})DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(), <@generatePassingParameters table.pkColumns/>));		
	}
	
	public List<${className}> getList(${className}Query query){
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from ${table.sqlName} where 1=1 ");
		<#list table.columns as column>
		<#if column.isDateTimeColumn>
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}Begin())) {
		    sql.append(" and ${column.sqlName} >= :${column.columnNameFirstLower}Begin ");
		}
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}End())) {
            sql.append(" and ${column.sqlName} <= :${column.columnNameFirstLower}End ");
        }
		<#else>
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}())) {
            sql.append(" and ${column.sqlName} = :${column.columnNameFirstLower} ");
        }
		</#if>
		</#list>
		if (ObjectUtil.isNotEmpty(query.getOffset()) && ObjectUtil.isNotEmpty(query.getLimit())) {
			sql.append(" limit ").append(" :offset ").append(",").append(" :limit ");
		}
		Map<String ,Object> paramMap = new HashMap<String ,Object> (PropertyUtils.describe(query));
		return getNamedParameterJdbcTemplate().query(sql.toString(), paramMap, getEntityRowMapper());
	}
	
	public int getCount(${className}Query query){
		StringBuilder sql = new StringBuilder("select count(*) from ${table.sqlName} where 1=1");
		<#list table.columns as column>
		<#if column.isDateTimeColumn>
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}Begin())) {
		    sql.append(" and ${column.sqlName} >= :${column.columnNameFirstLower}Begin ");
		}
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}End())) {
            sql.append(" and ${column.sqlName} <= :${column.columnNameFirstLower}End ");
        }
		<#else>
		if(ObjectUtil.isNotEmpty(query.get${column.columnNameFirstUpper}())) {
            sql.append(" and ${column.sqlName} = :${column.columnNameFirstLower} ");
        }
		</#if>
		</#list>
		Map<String ,Object> paramMap = new HashMap<String ,Object> (PropertyUtils.describe(query));
		return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramMap, Integer.class);
	}
	
}
