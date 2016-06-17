<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower>
<#assign dollar = '$'>
<#macro editFormatterParams><#list table.pkColumns as column><#if column_index == 0>+'${column.columnNameFirstLower}='+row.${column.columnNameFirstLower}<#else>+'&${column.columnNameFirstLower}='+row.${column.columnNameFirstLower}</#if></#list></#macro>
<#macro queryParams><#list table.columns as column><#if column_index == 0>+'${column.columnNameFirstLower}='+row.${column.columnNameFirstLower}<#else>+'&${column.columnNameFirstLower}='+row.${column.columnNameFirstLower}</#if></#list></#macro>	
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>${className} 列表</title>
	<script >
		$(document).ready(function() {
		});
	
	    function editFormatter(value, row) {
	        var doView = '<a class="btn btn-primary btn-xs" href="${r'${ctx}'}/${classNameFirstLower}/show.do?'<@editFormatterParams/>+'"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 查看</a>&nbsp;&nbsp;';
			var doEdit = '<a class="btn btn-primary btn-xs" href="${r'${ctx}'}/${classNameFirstLower}/edit.do?'<@editFormatterParams/>+'"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 修改</a>&nbsp;&nbsp;';
			var doDelete = '<a class="btn btn-danger btn-xs" href="${r'${ctx}'}/${classNameFirstLower}/delete.do?'<@editFormatterParams/>+'" onclick="Javascript:return confirm(\'确定要删除吗？\');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除</a>';
			var html = doView + doEdit + doDelete;
	        return html;
	    }

	    function query(){
	    	<#list table.columns as column>
	    	var ${column.columnNameFirstLower} = ${dollar}{'#${column.columnNameFirstLower}'}.val();
	    	</#list>
	    	var $table = ${dollar}('#${classNameFirstLower}Table');
	    	$table.bootstrapTable('refresh', {url: '/${classNameFirstLower}/page.do?'<@queryParams/>});
	    }
	    
	</script>
</rapid:override>


<rapid:override name="content">

	<div class="panel panel-default">
	
		<div class="panel-heading">${className} 列表</div>
		<div class="panel-body">
			
			<div class="row">
			<#list table.columns as column>
				<div class="col-sm-3">
					<div class="input-group">
						<#if column.isDateTimeColumn>
						<div class="input-group-addon">开始
							<#if column.comment??>
							${column.comment}
							< #else>
							${column.columnNameFirstLower}
							</#if>	
						</div>
						<input id="${column.columnNameFirstLower}" class="form-control input-from-control" placeholder="开始时间" value="<fmt:formatDate value='<@jspEl "query."+column.columnNameFirstLower+'Begin'/>' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="${column.columnNameFirstLower}Begin" name="${column.columnNameFirstLower}Begin"   />
						<div class="input-group-addon">结束
							<#if column.comment??>
							${column.comment}
							< #else>
							${column.columnNameFirstLower}
							</#if>							
						</div>
						<input id="${column.columnNameFirstLower}" class="form-control input-from-control" placeholder="结束时间" value="<fmt:formatDate value='<@jspEl "query."+column.columnNameFirstLower+'End'/>' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="${column.columnNameFirstLower}End" name="${column.columnNameFirstLower}End"   />
						
						<#else>
						<div class="input-group-addon">
							<#if column.comment??>
							${column.comment}
							< #else>
							${column.columnNameFirstLower}
							</#if>						
						</div>
						<input id="${column.columnNameFirstLower}" class="form-control input-from-control" placeholder="${column.comment!}" value="<@jspEl "query."+column.columnNameFirstLower/>" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" maxlength="${column.dataLength!}" />
						</#if>
					</div>
				</div>
			</#list>
							
			</div>

			<div style="margin-top:20px"  class="row text-left">
				<div class="col-sm-5">
					<a href="#" class="btn btn-primary btn-sm"  onclick="query();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索</a>
					<a class="btn btn-primary btn-sm" href="${r'${ctx}'}/${classNameFirstLower}/add.do"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增</a>
					<a class="btn btn-primary btn-sm" href="/pages/${classNameFirstLower}/upload.jsp"><span class="glyphicon glyphicon-import" aria-hidden="true"></span> 批量导入</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default table-responsive">

		<table id="${classNameFirstLower}Table" data-toggle="table" data-sort-name="id" data-sort-order="desc" data-classes="table table-no-bordered" data-show-export="true" 
              	   data-pagination="true"
		       data-side-pagination="server"
		       data-url="/${classNameFirstLower}/page.do"
                  data-page-list="[10, 20, 50, 100, 200]">
		  <thead>
			  <tr>
			<#list table.columns as column>
				 <th data-field="${column.columnNameFirstLower}" data-sortable="true">${column.comment!}</th>
			</#list>				  
				<th data-formatter="editFormatter">操作</th>	
			  </tr>
		  </thead>
		</table>
				
	</div>
	
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

