<%@ include file="/commons/taglibs.jsp" %>

<c:if test="${r'${flash.success != null}'}">
	<div class="alert alert-success">
		${r'${flash.success}'}<br/>
	</div>    
</c:if>

<c:if test="${r'${flash.error != null}'}">
	<div class="alert alert-danger">
		${r'${flash.error}'}<br/>
	</div> 
</c:if>

<c:if test="${r'${flash.warning != null}'}">
	<div class="alert alert-warning">
		${r'${flash.warning}'}<br/>
	</div> 
</c:if>

<c:if test="${r'${flash.info != null}'}">
	<div class="alert alert-info">
		${r'${flash.info}'}<br/>
	</div>
</c:if>
