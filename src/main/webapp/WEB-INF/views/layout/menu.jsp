<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<c:set var="isFreelance" value="${userLogged.freelanceProfile ne null}"></c:set>
<c:set var="isClient" value="${userLogged.clientProfile ne null}"></c:set>

<header>
	<div class="container">
		<div class="four columns">
			<a href="${pageContext.request.contextPath}"> <img class="logo"
				src="${pageContext.request.contextPath}/resources/merlin/images/logo.png"
				alt="logo" />
			</a>
		</div>
		<div class="twelve columns">
			<nav id="mainNavigation">
			<security:authorize access="isAuthenticated()">
				<security:authorize access="hasRole('admin')">
					<ul class="sf-menu sf-v2" id="sf-main-nav">
						<li class="">
							<a  id="account" class="sf-with-ul" href="#">  <i class="icon-user"></i> Account</a>
							<ul style="display: none;">
								<li><a href="${pageContext.request.contextPath}/account/views"><span>- </span> <spring:message code="menu.account.view"/> </a></li>
								<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><span>- </span> <spring:message code="common.logout"/></a></li>
							</ul>
						</li>
						<li><a id="users" href="${pageContext.request.contextPath}/users/views"> <i class="icon-tools"></i> <spring:message code="menu.users" /></a></li>
						<li><a id="main" href="${pageContext.request.contextPath}/categories/main/views"> <i class="icon-tools"></i> <spring:message code="menu.main" /></a></li>
						<li><a id="sub" href="${pageContext.request.contextPath}/categories/sub/views"> <i class="icon-tools"></i> <spring:message code="menu.sub" /></a></li>
						<li><a id="skills" href="${pageContext.request.contextPath}/categories/skill/views"> <i class="icon-tools"></i> Skills</a></li>
						<li><a id="projects" href="${pageContext.request.contextPath}/projects/views_all"> <i class="icon-tools"></i> <spring:message code="menu.projects" /></a></li>					
					</ul>
				</security:authorize>
				<security:authorize access="hasRole('user')">
					<ul class="sf-menu sf-v2" id="sf-main-nav">
						<li class="">
							<a  id="account" class="sf-with-ul" href="#">  <i class="icon-user"></i> Account</a>
							<ul style="display: none;">
								<li><a href="${pageContext.request.contextPath}/account/views"><span>- </span> <spring:message code="menu.account.view"/> </a></li>
								<c:choose>
								<c:when test="${isFreelance}">
									<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${userLogged.userID}"><span>- </span> <spring:message code="menu.account.freelancer_profile"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/profiles/freelancer/create"><span>- </span> <spring:message code="menu.account.freelancer_become"/></a></li>
								</c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${isClient}">
									<li><a href="${pageContext.request.contextPath}/profiles/client/views?userID=${userLogged.userID}"><span>- </span> <spring:message code="menu.account.client_profile"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/profiles/client/create"><span>- </span> <spring:message code="menu.account.client_become"/></a></li>
								</c:otherwise>
								</c:choose>
								<li><a href="${pageContext.request.contextPath}/projects/my_projects"><span>- </span> <spring:message code="menu.account.projects"/></a></li>
								<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><span>- </span> <spring:message code="common.logout"/></a></li>
							</ul>
						</li>
						<li><a id="projects" href="${pageContext.request.contextPath}/projects/views_all"> <i class="icon-list"></i> <spring:message code="menu.projects"/></a></li>
						<li><a id="freelancers" href="${pageContext.request.contextPath}/profiles/freelancer/views_all"> <i class="icon-users"></i> Freelancers</a></li>
						<li><a id="clients" href="${pageContext.request.contextPath}/profiles/client/views_all"> <i class="icon-tools"></i> Clients</a></li>
						<li><a id="support" href="${pageContext.request.contextPath}/support"> <i class="icon-info"></i> <spring:message code="menu.support"/></a></li>
						<li><a id="contact" href="${pageContext.request.contextPath}/contact"> <i class="icon-mail"></i> <spring:message code="menu.contact"/></a></li>		
						<li id="inbox-menu">
						   <a href="${pageContext.request.contextPath}/inbox/views" > 
						      <i class="icon-inbox"></i> <spring:message code="menu.inbox"/>
						   </a>
						   <div id="inbox-notification">
						      <!-- <span class="nav-counter" style="display: block;">1</span> -->
						   </div>
						</li>
					</ul>
				</security:authorize>
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
				<ul class="sf-menu sf-v2" id="sf-main-nav">
					<li><a id="home" href="${pageContext.request.contextPath}"> <i class="icon-home"></i> Home</a></li>
					<li><a id="projects" href="${pageContext.request.contextPath}/projects/views_all"> <i class="icon-list"></i> <spring:message code="menu.projects"/></a></li>					
					<li><a id="freelancers" href="${pageContext.request.contextPath}/profiles/freelancer/views_all"> <i class="icon-users"></i> Freelancers</a></li>
					<li><a id="clients" href="${pageContext.request.contextPath}/profiles/client/views_all"> <i class="icon-tools"></i> Clients</a></li>					
					<li><a id="support" href="${pageContext.request.contextPath}/support"> <i class="icon-info"></i> <spring:message code="menu.support"/></a></li>
					<li><a id="contact" href="${pageContext.request.contextPath}/contact"> <i class="icon-mail"></i> <spring:message code="menu.contact"/></a></li>
				</ul>
			</security:authorize>
			</nav>
		</div>
	</div>
</header>

<security:authorize access="isAuthenticated()">
<security:authorize access="hasRole('user')">
	<script type="text/javascript" >
	var contextPath='${pageContext.request.contextPath}';
	var userLogged = '${userLogged}';
	</script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/inbox/messageNotification.js" ></script>

</security:authorize>
</security:authorize>
