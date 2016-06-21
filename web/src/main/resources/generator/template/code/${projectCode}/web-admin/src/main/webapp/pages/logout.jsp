<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${r'${ctx}'}/js/jquery.min.js?version=2.1.1" type="text/javascript"></script>
<script type="text/javascript">
var port=location.port;
if(port==""||port==80){
	port=null;
}
var loginOutUrl="${r'${loginUrl}'}";
var loginAuth="JCL";
var url=window.location.protocol+"//"+location.hostname+(port!=null ? ":"+port:"")+"/";
if(loginOutUrl.charAt(loginOutUrl.length-1)=="="){
	loginOutUrl=loginOutUrl+url;
}
if (window.top != null && window.top.location != window.location) {
    window.top.location =loginOutUrl ;
}else{
	window.location = loginOutUrl;
}
</script>
