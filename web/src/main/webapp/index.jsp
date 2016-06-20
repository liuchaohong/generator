<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>web后台-代码生成器</title>

<script>


</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">web后台代码生成器 </h1>
	 	<form action="${ctx}/generator/gen.do" method="post" onsubmit="return checkForm();">
		 	<table width="100%">
		 		<tr>
		 			<td><b>项目代号</b></td>
		 			<td><input class="form-control" type="text" id="projectCode" name="projectCode" placeholder="项目代号" size="40" onchange="saveCookie()"/> 示例值:projectx</td>
		 		</tr>		 
		 		<tr>
		 			<td><b>作者</b></td>
		 			<td><input class="form-control" type="text" id="author" name="author" placeholder="作者" size="40" onchange="saveCookie()"/> 示例值:LIUCHAOHONG</td>
		 		</tr>		 			
		 		<tr>
		 			<td><b>create table SQL(用分号；分隔多条SQL)</b></td>
		 			<td><textarea class="form-control" id="sqls" name="sqls" cols="80" rows="19"  onchange="saveCookie()"></textarea>
		 				示例值: create table demo_table (id int AUTO_INCREMENT primary key, username varchar(20) ,age int,birth_date datetime);
		 			</td>
		 		</tr>
		 		<tr>
		 			<td><b>java package</b></td>
		 			<td><input class="form-control" type="text" id="basePackage" name="basePackage" placeholder="java包名" size="40" onchange="saveCookie()"/> 示例值:com.company.projectname</td>
		 		</tr>
		 		<tr>
		 			<td><b>需要删除表名的前缀</b></td>
		 			<td><input class="form-control" type="text" id="tableRemovePrefixes" name="tableRemovePrefixes" size="40" onchange="saveCookie()"/> 示例值: t_,v_</td>
		 		</tr> 		
		 	</table>
		 	<div class="text-center"><input class="btn btn-primary btn-lg" type="submit" value="生成代码" /></div>
	 	</form>
	</div>
 	
<script type="text/javascript">
	function checkForm(){
		var projectCode = $("#projectCode").val();
		var author = $("#author").val();
		var sqls = $("#sqls").val();
		var basePackage = $("#basePackage").val();
		if(projectCode == ""){
			alert("项目代号 不可为空");
			return false;
		}
		if(author == ""){
			alert("作者 不可为空");
			return false;
		}		
		if(sqls == ""){
			alert("create table SQL 不可为空");
			return false;
		}
		if(basePackage == ""){
			alert("java package 不可为空");
			return false;
		}
		return true;
	}

	function saveCookie() {
		$.cookie('projectCode', $('#projectCode').val(),{expires:10000000});
		$.cookie('author', $('#author').val(),{expires:10000000});
		$.cookie('sqls', $('#sqls').val(),{expires:10000000});
		$.cookie('basePackage', $('#basePackage').val(),{expires:10000000});
		$.cookie('tableRemovePrefixes', $('#tableRemovePrefixes').val(),{expires:10000000});
	}

	function recoverCookie() {
		$("#projectCode").val($.cookie('projectCode'));
		$("#author").val($.cookie('author'));
		$("#sqls").val($.cookie('sqls'));
		$("#basePackage").val($.cookie('basePackage'));
		$("#tableRemovePrefixes").val($.cookie('tableRemovePrefixes'));
	}

	recoverCookie();
	
</script>

</body>


</html>
