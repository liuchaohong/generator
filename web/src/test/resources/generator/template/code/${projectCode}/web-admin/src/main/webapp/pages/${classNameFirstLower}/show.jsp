<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<#assign dollar = '$'>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>${className} 信息</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">查看 ${className}</h2>
	
	<form:form modelAttribute="${classNameFirstLower}" cssClass="form-horizontal"  >

		<#list table.columns as column>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>
				<#if column.comment??>
				${column.comment}
				<#else>
				${column.columnNameFirstLower}
				</#if>
				:</b></div>	
				<div class="col-xs-4">
				<#if column.isDateTimeColumn>
					<fmt:formatDate value='<@jspEl classNameFirstLower+"."+column.columnNameFirstLower/>' pattern="yyyy-MM-dd"/>
				<#else>
					<c:out value='<@jspEl classNameFirstLower+"."+column.columnNameFirstLower/>'/>
				</#if>
				</div>
			</div>
		</#list>
		
			<div class="form-group">
				<div class="text-center">
					<a class="btn btn-default" href="${r'${ctx}'}/${classNameFirstLower}/index.do">返回列表</a>&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-default" value="后退" onclick="history.back();"/>
				</div>
			</div>

	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>