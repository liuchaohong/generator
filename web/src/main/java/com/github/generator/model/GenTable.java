package com.github.generator.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.generator.util.StringHelper;

public class GenTable {
	
	private String sqlName;
	
	private List<GenColumn> columns;
	
	private List<GenColumn> pkColumns;
	
	private List<GenColumn> notPkColumns;
	
	private String comment;

	/**
	 * 映射属性
	 */
	private String className;
	private String classNameFirstLower; 
	
	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
		this.className = StringHelper.getClassName(sqlName);
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
