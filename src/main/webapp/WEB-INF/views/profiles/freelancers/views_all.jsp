<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
var result='<spring:message code="common.result" />';
var page_message='<spring:message code="common.page" />';
var jobs='<spring:message code="common.jobs" />';
var notfound='<spring:message code="common.notfound" />';
$("#freelancers").addClass("current");

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/freelancersFilter.js" ></script>

<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:input path="category" id="category" value=""/>
<form:input path="skill.name" id="skill" value=""/>
<form:input path="search" id="search" value=""/>
<form:input path="itemSort" id="itemSort" value=""/>
<form:input path="dirSort" id="dirSort" value="asc"/>
<form:input path="page" id="page" value=""/>
<form:input path="itemsForPage" id="itemsForPage" value="5"/>
<form:input path="userID" id="userID" value="${userLogged.userID}"/>
<form:input path="status" id="status" value=""/>

</form:form>

<section id="main_content">
	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3><spring:message code="common.search_freelancer"/></h3>
				<p><spring:message code="common.search_freelancer.description"/></p>
			</div>
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li>&#187;</li>
					<li><spring:message code="common.search_freelancer"/></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="four columns add-top add-bottom">
			<aside class="sidebar">
				<article class="widget search">
					<input id="visibleSearch" onkeyup="addSearch();" type="text" placeholder="<spring:message code='common.search'/>...">
 				</article><!-- end .search -->					
				<article class="widget latest_posts">
					<div class="widget_title">
						<h2> <span class="bold"><spring:message code="common.categories"/></span> </h2>
						<div class="title_sep_container"><div class="title_sep"></div></div>
					</div><!-- end .title -->
					<ul id="categoryList">
					<c:forEach var="mainCategory" items="${categories}">
					<li> <a id="${mainCategory.mainID}" href="javascript:addCategory('${mainCategory.mainID}');">${mainCategory.name}</a> </li>
					</c:forEach>	
					</ul>	
				</article><!-- end .latest_posts -->
				<article class="widget">
					<div class="widget_title">
						<h2> <span class="bold">Skills</span> </h2>
						<div class="title_sep_container"><div class="title_sep"></div></div>
					</div><!-- end .title -->
					
					<ul id="skillList" class="tagList">
						<c:forEach var="skill" items="${skills}">
						<li id="${skill.name}"><a href="javascript:addSkill('${skill.name}');">${skill.name}</a></li>
						</c:forEach>
					</ul>
					<!-- end ul.tagList -->
				</article>
			</aside>
		</div>
		<div class="twelve columns">
		<div class="title">
				<h2> <span class="bold"><spring:message code="common.our" /></span> <span>freelancers</span> </h2>
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div><!-- end .title_sep_container -->			
			</div>
		</div>
		<div class="twelve columns" style="border-bottom:1px solid #e0e0e0;padding-bottom:10px;margin-bottom:10px;">
			<span id="counter"></span>
			<span style="float:right;">
			<a id="name" href="javascript:addOrder('name');"><spring:message code="common.name" /><i class="icon-down-dir"></i></a>
			 <a id="rating" href="javascript:addOrder('rating');"><spring:message code="common.rating" /><i class="icon-down-dir"></i></a>
			  <a id="jobs" href="javascript:addOrder('jobs');"><spring:message code="common.jobs" /><i class="icon-down-dir"></i></a></span>
		</div>
			<%-- <div id="LoadingImage" style="display: none">
				<img src="${pageContext.request.contextPath}/resources/merlin/images/load1.gif" width="128px" height="128px"/>
			</div> --%>
			<div id="result">
			<%-- <c:choose>
			<c:when test="${empty freelancers}"><p>There isn't freelaners</p></c:when>
			<c:otherwise>
			<c:forEach var="freelancer" items="${freelancers}">
			
			<div class="twelve columns add-bottom" style="border-bottom:1px solid #e0e0e0;padding-bottom:20px;">
				<div class="freelancerList_imgHolder"><img src="${pageContext.request.contextPath}/resources/merlin/images/avatar_default.png"></div>
				<div style="float:left;margin-left:20px;min-width:200px;">
					<div> 
						<h5> <a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${freelancer.userID}"> ${freelancer.freelanceProfile.freelanceName}</a> </h5>		
						<span>${freelancer.freelanceProfile.category.name}&nbsp;&nbsp;&nbsp;</span>
						<span>Jobs: 35</span>
						<div class="rating" data-average="10" data-id="4"></div>
					</div><!-- end .blog_list_item_description -->
				</div>
				<div style="float:left;margin-left:20px;">	
					<ul class="tagList">
						<c:forEach var="skill" items="${freelancer.freelanceProfile.curriculum.cvSkills}">
						<li>${skill.name}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</c:forEach>
			</c:otherwise>
			</c:choose> --%>
			</div>
			<nav id="pagination" class="pagination">
			</nav>
		</div>
	<!-- end container -->
</section>

<!-- end #main_content -->