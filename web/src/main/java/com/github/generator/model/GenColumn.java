package com.github.generator.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.generator.util.StringHelper;

public class GenColumn {
	/** 列名 **/
	private String sqlName;
	/** 数据类型 **/
	private String dataType;
	/** 数据长度 **/
	private String dataLength;
	/** 注释 **/
	private String comment;

	/** 映射属性 begin **/
	private String javaType;
	private String columnNameFirstLower;
	private String columnNameFirstUpper;
	private static Map<String, String> javaTypeMap;
	/** 映射属性 end **/
	
	static{//数据类型 => java类型
		javaTypeMap = new HashMap<String,String>();
		javaTypeMap.put("int", "Integer");
		javaTypeMap.put("tinyint", "Integer");
		javaTypeMap.put("varchar", "String");
		javaTypeMap.put("datetime", "Date");
	}
	
	/**
	 * 判断是否是时间类型
	 * @return
	 */
	public boolean getIsDateTimeColumn() {
		if(javaType.endsWith("Date") || javaType.endsWith("Timestamp") || javaType.endsWith("Time")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否包含keywords
	 * @param keywords
	 * @return
	 */
	public boolean contains(String keywords) {
		if(keywords == null) throw new IllegalArgumentException("'keywords' must be not null");
		return StringHelper.contains(getSqlName(), keywords.split(","));
	}
	
	/**
	 * get/set
	 * @return
	 */

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
		this.columnNameFirstLower = StringHelper.getColumnNameFirstLower(sqlName);
		this.columnNameFirstUpper = StringHelper.getColumnNameFirstUpper(sqlName);
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
		this.javaType = StringUtils.isNotBlank(javaTypeMap.get(dataType)) ? javaTypeMap.get(dataType) : "String" ;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getColumnNameFirstLower() {
		return columnNameFirstLower;
	}

	public void setColumnNameFirstLower(String columnNameFirstLower) {
		this.columnNameFirstLower = columnNameFirstLower;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public static Map<String, String> getJavaTypeMap() {
		return javaTypeMap;
	}

	public static void setJavaTypeMap(Map<String, String> javaTypeMap) {
		GenColumn.javaTypeMap = javaTypeMap;
	}

	public String getColumnNameFirstUpper() {
		return columnNameFirstUpper;
	}

	public void setColumnNameFirstUpper(String columnNameFirstUpper) {
		this.columnNameFirstUpper = columnNameFirstUpper;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sqlName == null) ? 0 : sqlName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenColumn other = (GenColumn) obj;
		if (sqlName == null) {
			if (other.sqlName != null)
				return false;
		} else if (!sqlName.equals(other.sqlName))
			return false;
		return true;
	}

}
