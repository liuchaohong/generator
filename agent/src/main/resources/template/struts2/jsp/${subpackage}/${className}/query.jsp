<%@page import="${basepackage}.${subpackage}.model.*" %>
<#include "macro.include"/> 
<#include "custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<title><%=${className}.TABLE_ALIAS%>查询</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<s:form action="${strutsActionBasePath}/list.action" method="post">
	<input type="submit" value="提交" onclick="return new Validation(document.forms[0]).validate();"/>
	<input type="button" value="返回列表" onclick="window.location='<@jspEl 'ctx'/>${strutsActionBasePath}/list.action'"/>
	
	<table class="formTable">
	
	<#list table.columns as column>
	<#if !column.htmlHidden>
		<tr bgcolor="#FFFFFF">
		   <th><%=${className}.ALIAS_${column.sqlName?upper_case}%></th>
		   <td>
				<input <#if column.isDateTimeColumn>onclick="WdatePicker({dateFmt:'<%=${className}.FORMAT_${column.sqlName?upper_case}%>'})"</#if> type="text" name="s_${column.columnNameLower}" size="30" maxlength="${column.size}" class="${column.noRequiredValidateString}"/>
		   </td>
		</tr>
	</#if>
	</#list>
	
	</table>
</s:form>	
			
</body>

</html>