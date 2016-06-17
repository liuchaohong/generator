<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title>后台系统</title>

	<!--=== JavaScript ===-->
	<script type="text/javascript" src="${r'${ctx}'}/js/jquery.min.js"></script>
	<!-- General -->
	<script type="text/javascript" src="${r'${ctx}'}/js/breakpoints.js"></script>
	<!-- App -->
	<script type="text/javascript" src="${r'${ctx}'}/js/app_melon.js"></script>
	
	<!-- Bootstrap -->
	<link href="${r'${ctx}'}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script src="${r'${ctx}'}/lib/bootstrap/js/bootstrap.min.js"></script>

	<!-- bootstrap -->
	<link href="${r'${ctx}'}/lib/bootstrap-table/bootstrap-table.css" rel="stylesheet">
	<script src="${r'${ctx}'}/lib/bootstrap-table/bootstrap-table.js"></script>
	<script src="${r'${ctx}'}/lib/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<script src="${r'${ctx}'}/lib/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
	<script src="${r'${ctx}'}/lib/bootstrap-table/extensions/export/tableExport.js"></script>

	<!-- highcharts -->
	<script src="${r'${ctx}'}/lib/highcharts/highcharts.js"></script>
	<script src="${r'${ctx}'}/lib/highcharts/modules/exporting.js"></script> 
	<script src="${r'${ctx}'}/lib/highcharts/highchartsUtil.js"></script>
   
	<!-- multiple-select -->
	<script src="${r'${ctx}'}/lib/multiple-select/jquery.multiple.select.js"></script>
	<link href="${r'${ctx}'}/lib/multiple-select/multiple-select.css" rel="stylesheet"  media="screen"/>
		
	<!-- Theme -->
	<link href="${r'${ctx}'}/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${r'${ctx}'}/css/responsive.css" rel="stylesheet" type="text/css" />

	<rapid:block name="head"></rapid:block>
	
	<script>
	$(document).ready(function(){
		"use strict";
		App.init(); // Init layout and core plugins
		//Plugins.init(); // Init all plugins
		//FormComponents.init(); // Init all form-specific plugins
	});
	</script>


</head>

<body>

	<!-- Header -->
	<header class="header navbar navbar-fixed-top" role="banner">
		<!-- Top Navigation Bar -->
		<div class="container">

			<!-- Only visible on smartphones, menu toggle -->
			<ul class="nav navbar-nav">
				<li class="nav-toggle"><a href="javascript:void(0);" title=""><i class="glyphicon glyphicon-menu-hamburger"></i></a></li>
			</ul>

			<!-- Logo -->
			<a class="navbar-brand" href="index.html">
				<strong>广告监控系统</strong>
			</a>
			<!-- /logo -->

			<!-- Sidebar Toggler -->
			<a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
				<i class="glyphicon glyphicon-menu-hamburger"></i>
			</a>
			<!-- /Sidebar Toggler -->

			<!-- Top Left Menu -->
			<ul class="nav navbar-nav navbar-left hidden-xs hidden-sm">
				<li>
					<a href="#">
						Dashboard
					</a>
				</li>
			</ul>
			<!-- /Top Left Menu -->

		</div>
		<!-- /top navigation bar -->
	</header> <!-- /.header -->

	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">
				<!--=== Navigation ===-->
				<ul id="nav">
					<li>
						<a href="index.html">
							<i class="glyphicon glyphicon-folder-open"></i>
							一级菜单
						</a>
					</li>
					
					<li>
						<a href="javascript:void(0);">
							<i class="glyphicon glyphicon-folder-open"></i>
							二级菜单
						</a>
						<ul class="sub-menu">
							<li>
								<a href="login.html">
								<i class="glyphicon glyphicon-menu-right"></i>
								广告单监控
								</a>
							</li>
							<li>
								<a href="pages_user_profile.html">
								<i class="glyphicon glyphicon-menu-right"></i>
								广告组监控
								</a>
							</li>
						</ul>
					</li>

					<li>
						<a href="javascript:void(0);">
							<i class="glyphicon glyphicon-folder-open"></i>
							多级菜单
						</a>
						<ul class="sub-menu">
							<li>
								<a href="javascript:void(0);">
									<i class="icon-globe"></i>
									Item 1
									<span class="arrow"></span>
								</a>
								<ul class="sub-menu">
									<li><a href="javascript:void(0);"><i class="icon-user"></i>  Sample Link 1</a></li>
									<li><a href="javascript:void(0);"><i class="icon-external-link"></i>  Sample Link 1</a></li>
									<li><a href="javascript:void(0);"><i class="icon-bell"></i>  Sample Link 1</a></li>
								</ul>
							</li>						
							<li class="open-default">
								<a href="javascript:void(0);">
									<i class="icon-cogs"></i>
									Item 2
									<span class="arrow"></span>
								</a>
								<ul class="sub-menu">
								<!--
									<li class="open-default">
									  
										<a href="javascript:void(0);">
											Sample Link 1
										</a>
										<ul class="sub-menu">
											<li class="current"><a href="javascript:void(0);"><i class="icon-remove"></i> Sample Link 1</a></li>
											<li><a href="javascript:void(0);"><i class="icon-pencil"></i> Sample Link 1</a></li>
											<li><a href="javascript:void(0);"><i class="icon-edit"></i> Sample Link 1</a></li>
										</ul>
									
									</li>
								-->
									<li><a href="javascript:void(0);"><i class="icon-user"></i>  Sample Link 1</a></li>
									<li><a href="javascript:void(0);"><i class="icon-external-link"></i>  Sample Link 2</a></li>
									<li><a href="javascript:void(0);"><i class="icon-bell"></i>  Sample Link 3</a></li>
								</ul>
							</li>
							<li>
								<a href="javascript:void(0);">
									<i class="icon-folder-open"></i>
									Item 3
								</a>
							</li>
						</ul>
					</li>
															
				</ul>

			</div>
			<div id="divider" class="resizeable"></div>
		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container" style="margin-top:20px;">
				<%@ include file="/commons/messages.jsp"  %>
				<rapid:block name="content"/>
			</div>
		</div>
		
	</div>
<!-- /.container -->

</body>
</html>