<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
$("#clients").addClass("current");

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/clientsFilter.js" ></script>

<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:input title="category" path="category" id="category" value=""/>
<form:input title="skill" path="skill.name" id="skill" value=""/>
<form:input title="search" path="search" id="search" value=""/>
<form:input title="item sort" path="itemSort" id="itemSort" value=""/>
<form:input title="dir sort" path="dirSort" id="dirSort" value="asc"/>
<form:input title="page" path="page" id="page" value=""/>
<form:input title="items for page" path="itemsForPage" id="itemsForPage" value="5"/>
<form:input title="user id" path="userID" id="userID" value="${userLogged.userID}"/>
<form:input path="status" id="status" value=""/>

</form:form>

<section id="main_content">
	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3>Search clients</h3>
				<p>Search the client just for you</p>
			</div>
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li>&#187;</li>
					<li>Find Client</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="four columns add-top add-bottom">
			<aside class="sidebar">
				<article class="widget search">
					<input id="visibleSearch" onkeyup="addSearch();" type="text" placeholder="Search name or company...">
 				</article><!-- end .search -->					
			</aside>
		</div>
		<div class="twelve columns">
		<div class="title">
				<h2> <span class="bold">Our</span> <span>clients</span> </h2>
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div><!-- end .title_sep_container -->			
			</div>
		</div>
		<div class="twelve columns" style="border-bottom:1px solid #e0e0e0;padding-bottom:10px;margin-bottom:10px;">
			<span id="counter"></span>
			<span style="float:right;">
			<a id="name" href="javascript:addOrder('name');">Name<i class="icon-down-dir"></i></a>
			 <a id="rating" href="javascript:addOrder('rating');">Rating<i class="icon-down-dir"></i></a>
			  <a id="jobs" href="javascript:addOrder('jobs');">Jobs<i class="icon-down-dir"></i></a>
			  </span>
		</div>
			<div id="result">
			</div>
			<nav id="pagination" class="pagination">
			</nav>
		</div>
	<!-- end container -->
</section>
<!-- end #main_content -->