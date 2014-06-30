<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>


<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
var result='<spring:message code="common.result" />';
var page_message='<spring:message code="common.page" />';
var notfound='<spring:message code="common.notfound" />';
var elements='<spring:message code="common.elements" />';
</script>

<tiles:insertAttribute name="init"/>

<input type="hidden" id="profile"/>
<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:input path="category" id="category" value=""/>
<form:input path="skill.name" id="skill" value=""/>
<form:input path="search" id="search" value=""/>
<form:input path="itemSort" id="itemSort" value=""/>
<form:input path="dirSort" id="dirSort" value="asc"/>
<form:input path="page" id="page" value=""/>
<form:input path="itemsForPage" id="itemsForPage" value="5"/>
<form:input path="userID" id="userID" value="${userLogged.userID}"/>
<form:input path="status" id="status" value="HIRING_OPEN"/>
</form:form>

<section id="main_content">
	<div class="top_title_holder add-bottom">
		<tiles:insertAttribute name="header"/>
	</div>
	<div class="container">
		<div class="four columns add-top add-bottom">
			<tiles:insertAttribute name="sidebar"/>
		</div>
		<div class="twelve columns">
			<div class="title">
				<c:choose>
				<c:when test="${requestScope.title eq 'f'}">
				<h2> <span class="bold"><spring:message code="common.our" /></span> <span>freelancers</span> </h2>
				</c:when>
				<c:when test="${requestScope.title eq 'c'}">
				<h2> <span class="bold"><spring:message code="common.our" /></span> <span>clients</span> </h2>
				</c:when>
				<c:when test="${requestScope.title eq 'p'}">
				<h2> <span class="bold"><spring:message code="common.our" /></span> <span><spring:message code="menu.projects" /></span> </h2>
				</c:when>
				<c:otherwise>
				<h2> <span class="bold"><spring:message code="common.list" /></span> <span><spring:message code="menu.projects" /></span> </h2>
				</c:otherwise>
				</c:choose>
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div><!-- end .title_sep_container -->			
			</div>
		</div>
		<div class="twelve columns" style="border-bottom:1px solid #e0e0e0;padding-bottom:10px;margin-bottom:10px;">
			<span id="counter"></span>
			<span style="float:right;">
			<tiles:insertAttribute name="order"/>
			</span>
		</div>
			<div id="result"></div>
			<nav id="pagination" class="pagination">
			</nav>
		</div>
	<!-- end container -->
</section><!-- end #main_content -->

<tiles:insertAttribute name="rate_form" ignore="true"/>