<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%//用于实现jsp模板的继承关系,请查看:http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends %>
<%-- <%@ taglib uri="http://github.com/badqiu/rapid-framework" prefix="rapid" %> --%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--=== JavaScript ===-->
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>

<!-- Bootstrap -->
<link href="${ctx}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/lib/bootstrap/js/bootstrap.min.js"></script>

