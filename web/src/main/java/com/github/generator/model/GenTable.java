package com.github.generator.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.generator.util.StringHelper;

public class GenTable {
	/** 表名 **/
	private String sqlName;
	/** 列名 **/
	private List<GenColumn> columns;
	/** 主键列 **/
	private List<GenColumn> pkColumns;
	/** 非主键列 **/
	private List<GenColumn> notPkColumns;
	/** 注释  **/
	private String comment;

	/** 映射属性 begin **/
	private String tableRemovePrefixes;
	private String className;
	private String classNameFirstLower; 
	/** 映射属性 end **/
	
	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
		String sqlClassName = sqlName;
		if (StringUtils.isNotBlank(tableRemovePrefixes)) {//去掉表前缀 wh_metadata_ds => metadata_ds
			String[] deleteClassNamePrefixArray = tableRemovePrefixes.split(",");
			for (int i = 0; i < deleteClassNamePrefixArray.length; i++) {
				if (sqlName.contains(deleteClassNamePrefixArray[i])) {
					sqlClassName = sqlName.replace(deleteClassNamePrefixArray[i], "");
				}
			}
		}
		this.className = StringHelper.getClassName(sqlClassName);
		this.classNameFirstLower = StringHelper.toLowerCaseFirstOne(className);
	}

	public List<GenColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<GenColumn> columns) {
		this.columns = columns;
	}

	public List<GenColumn> getPkColumns() {
		return pkColumns;
	}

	public void setPkColumns(List<GenColumn> pkColumns) {
		this.pkColumns = pkColumns;
	}

	public List<GenColumn> getNotPkColumns() {
		return notPkColumns;
	}

	public void setNotPkColumns(List<GenColumn> notPkColumns) {
		this.notPkColumns = notPkColumns;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTableRemovePrefixes() {
		return tableRemovePrefixes;
	}

	public void setTableRemovePrefixes(String tableRemovePrefixes) {
		this.tableRemovePrefixes = tableRemovePrefixes;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNameFirstLower() {
		return classNameFirstLower;
	}

	public void setClassNameFirstLower(String classNameFirstLower) {
		this.classNameFirstLower = classNameFirstLower;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
