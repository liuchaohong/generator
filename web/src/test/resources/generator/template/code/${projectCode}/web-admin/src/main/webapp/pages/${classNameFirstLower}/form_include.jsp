<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<#assign dollar = '$'>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<#list table.columns as column>
	<div class="form-group">
		<label for="${column.columnNameFirstLower}" class="col-sm-4 control-label">${column.comment!}:</label>
		<div class="col-md-4">
		<#if column.isDateTimeColumn>
			<input value='<fmt:formatDate value="<@jspEl classNameFirstLower+"."+column.columnNameFirstLower/>" pattern="yyyy-MM-dd"/>' class="form-control"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}"  maxlength="0" />
		<#else>
			<input value="${dollar}{${classNameFirstLower}.${column.columnNameFirstLower}}" name="${column.columnNameFirstLower}" id="${column.columnNameFirstLower}" class="form-control" maxlength="${column.dataLength!}" placeholder="${column.columnNameFirstLower}" />
		</#if>
		</div>
	 </div>
</#list>			