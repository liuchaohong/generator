<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<#assign dollar = '$'>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<#list table.notPkColumns as column>
	<div class="form-group">
		<label for="${column.columnNameFirstLower}" class="col-sm-4 control-label">${column.comment!}:</label>
		<div class="col-md-4">
		<#if column.isDateTimeColumn>
		
		<#else>
			<input value="${dollar}{${classNameFirstLower}.${column.columnNameFirstLower}}" name="${column.columnNameFirstLower}" id="${column.columnNameFirstLower}" class="form-control" maxlength="${column.dataLength!}" placeholder="${column.columnNameFirstLower}" required />
		</#if>
		</div>
	 </div>
</#list>			