<#include "macro.include"/>
<#include "custom.include"/>  
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<html:base/>
	<title><%=${className}.TABLE_ALIAS%>新增</title>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<html:form action="${strutsActionBasePath}/save.do" method="post">
	<input id="submit" name="submit" type="submit" value="提交" />
	<input type="button" value="返回列表" onclick="window.location='<@jspEl 'ctx'/>${strutsActionBasePath}/list.do'"/>
	
	<table class="formTable">
	<%@ include file="form_include.jsp" %>
	</table>
</html:form>

<script>
	
	new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
		var finalResult = result;
		
		//在这里添加自定义验证
		
		return disableSubmit(finalResult);
	}});
	
	function disableSubmit(finalResult) {
		if(finalResult) {
			$('submit').disabled = true;
			return finalResult;
		}else {
			return finalResult;
		}
	}
</script>

</body>
</html>