<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<c:set var="isFreelance" value="${userLogged.freelanceProfile ne null}"></c:set>
<c:set var="isClient" value="${userLogged.clientProfile ne null}"></c:set>
<aside class="sidebar">
	<article class="widget search">
		<input id="visibleSearch" onkeyup="addSearch();" type="text" placeholder="<spring:message code='common.search'/>...">
 	</article><!-- end .search -->					
	
		<c:if test="${isFreelance}">
		<article class="widget latest_posts">
		<div class="widget_title">
				<h2> <span class="bold">Freelance</span> </h2>
			<div class="title_sep_container"><div class="title_sep"></div></div>
		</div><!-- end .title -->
		<ul id="categoryList">
			<li> <a id="watch" href="javascript:addStatus('watch','freelance');"><spring:message code="project.watchlist"/></a> </li>
			<li> <a id="NOT_HIRED" href="javascript:addStatus('NOT_HIRED','freelance');"><spring:message code="project.proposal"/></a> </li>
			<li> <a id="WORKING" href="javascript:addStatus('WORKING','freelance');"><spring:message code="project.working"/></a> </li>
			<li> <a id="COMPLETED" href="javascript:addStatus('COMPLETED','freelance');"><spring:message code="project.done"/></a> </li>
		</ul>	
		</article><!-- end .latest_posts -->
		</c:if>
		<c:if test="${isClient}">
		<article class="widget latest_posts">
		<div class="widget_title">
				<h2> <span class="bold">Client</span> </h2>
			<div class="title_sep_container"><div class="title_sep"></div></div>
		</div><!-- end .title -->
		<ul id="categoryList">
			<li> <a id="HIRING_OPEN" href="javascript:addStatus('HIRING_OPEN','client');"><spring:message code="project.open"/></a> </li>
			<li> <a id="HIRING_CLOSED" href="javascript:addStatus('HIRING_CLOSED','client');"><spring:message code="project.working"/></a> </li>
			<li> <a id="EXPIRED" href="javascript:addStatus('EXPIRED','client');"><spring:message code="project.expired"/></a> </li>
			<li> <a id="COMPLETED" href="javascript:addStatus('COMPLETED','client');"><spring:message code="project.done"/></a> </li>
		</ul>	
		</article><!-- end .latest_posts -->
		</c:if>
	
</aside>
