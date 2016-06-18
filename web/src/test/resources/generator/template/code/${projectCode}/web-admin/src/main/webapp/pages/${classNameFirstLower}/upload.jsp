<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>导入${className}</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">导入${className}</h2>
	
	<form id="inputForm" class="form-horizontal" action="${r'${ctx}'}/${classNameFirstLower}/upload.do" method="post"  enctype="multipart/form-data" >
		  
		<div class="form-group">
			<label for="username" class="col-sm-4 control-label">文件</label>
			<div class="col-sm-4">
				<input type="file" name="file" class="file" placeholder="选择上传文件" required />
				<span class="help-block"><a href="${r'${ctx}'}/pages/${classNameFirstLower}/upload.csv" >下载数据上传模板</a></span>
			</div>
		</div>
	 
		<div class="form-group">
		    <div class="text-center">
				<input id="submitButton" class="btn btn-primary" name="submitButton" type="submit" value="上传" />&nbsp;&nbsp;&nbsp;
				<a class="btn btn-default" href="${r'${ctx}'}/${classNameFirstLower}/index.do">返回列表</a>&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-default" value="后退" onclick="history.back();"/>
		    </div>
		</div>
	</form>
	
	<script>
	</script>
	
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>