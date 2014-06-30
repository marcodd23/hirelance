<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
$("#account").addClass("current");
</script>
<c:set var="user" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<div id="dialog" title="<spring:message code="common.account_delete_title" />">
  	<p><span style="float: left; margin: 0 7px 20px 0;"></span><spring:message code="common.account_delete_message"/></p>
</div>
<div id="dialog1" title="<spring:message code="common.freelance_delete_title" />">
  	<p><span style="float: left; margin: 0 7px 20px 0;"></span><spring:message code="common.freelance_delete_message"/></p>
</div>
<div id="dialog2" title="<spring:message code="common.client_delete_title" />">
  	<p><span style="float: left; margin: 0 7px 20px 0;"></span><spring:message code="common.client_delete_message"/></p>
</div>
<section id="main_content">
	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="account.title" /></h3>
				<p><spring:message code="account.description" /></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><spring:message code="common.logout" /></a></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<div class="one-third column alpha add-bottom">
			<div class="title">
				<h2> <span class="bold">Account </span> <span>info</span> </h2>					
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
			</div>
			<table class="project_details">
				<tbody>
				<tr><td><strong><spring:message code="common.name"/>: </strong></td><td><security:authentication property="principal.user.name"/></td></tr>
				<tr><td><strong><spring:message code="common.surname"/>: </strong></td><td><security:authentication property="principal.user.surname"/></td></tr>				
				<tr><td><strong>Username: </strong></td><td><security:authentication property="principal.user.username"/></td></tr>				
				<tr><td><strong>Email: </strong></td><td><security:authentication property="principal.user.email"/></td></tr>				
				<tr><td><strong><spring:message code="common.registration"/>: </strong></td><td><fmt:formatDate value="${user.creationDate}" pattern="dd/MM/yyyy"/></td></tr>				
				</tbody>
			</table>
			<div class="proect_details_btn_holder">
				<a style="margin-right:5px;" title="<spring:message code='common.account_update'/>" href="${pageContext.request.contextPath}/users/update?user_id=${user.userID}" class="blue_gradient small"><i class="icon-pencil"></i></a>
				<a title="<spring:message code='common.account_delete'/>" href="#" onclick="url='${pageContext.request.contextPath}/account/delete';$('#dialog').dialog('open');" class="red_gradient small"><i class="icon-cancel-circled"></i></a>
			
			</div>
			</div>
			<security:authorize access="hasRole('user')">
			<div class="one-third column alpha add-bottom">
				<div class="title">
					<h2> <span class="bold">Freelancer </span> <span>info</span> </h2>					
					<div class="title_sep_container">
							<div class="title_sep"></div>
					</div>
				</div>
				<c:choose>
					<c:when test="${requestScope.fp != null}">
						<table class="project_details">
							<tbody>
								<tr><td><strong>Alias: </strong></td><td>${requestScope.fp.freelanceName}</td>
								<tr><td><strong><spring:message code="common.category"/>: </strong></td><td>${requestScope.fp.category.name}</td>
								<tr><td><strong><spring:message code="freelancer.jobs_done" />: </strong></td><td>${requestScope.fp.totalProjects}</td>
								<tr><td><strong><spring:message code="menu.projects" /> <spring:message code="project.watchlist" />: </strong></td><td>${fn:length(requestScope.fp.projectWatchList)}</td>							
								<tr><td><strong><spring:message code="common.rating"/>: </strong></td><td>${requestScope.fp.rating}</td>													
							</tbody>
						</table>
						<div class="proect_details_btn_holder">
							<a style="margin-right:5px;" title="<spring:message code='common.profile_view'/>" href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=<security:authentication property='principal.user.userID'/>" class="blue_gradient small"><i class="icon-menu"></i></a>
							<a title="<spring:message code='common.profile_delete'/>" href="#" onclick="url='${pageContext.request.contextPath}/profiles/freelancer/delete';$('#dialog1').dialog('open');" class="red_gradient small"><i class="icon-cancel-circled"></i></a>
						</div>
					</c:when>
					<c:otherwise>
				    <br>
				       <p><a href="${pageContext.request.contextPath}/profiles/freelancer/create" class="standard_button large"><spring:message code="menu.account.freelancer_become" /></a></p>
				    </c:otherwise>
				</c:choose>
			</div>
			
			<div class="one-third column alpha add-bottom">
				<div class="title">
					<h2> <span class="bold">Client </span> <span>info</span> </h2>					
					<div class="title_sep_container">
						<div class="title_sep"></div>
					</div>
				</div>
				<c:choose>
				<c:when test="${requestScope.cp != null }">
                <table class="project_details">
				  <tbody>
				    <tr><td><strong>Client Name: </strong></td><td>${requestScope.cp.clientName}</td>
				    <tr><td><strong><spring:message code="client.company"/>: </strong></td><td>${requestScope.cp.companyName}</td>
			      	<tr><td><strong><spring:message code="client.jobTitle"/>: </strong></td><td>${requestScope.cp.jobTitle}</td>													
			      	<tr><td><strong><spring:message code="common.rating"/>: </strong></td><td>${requestScope.cp.rating}</td>													
			     	<tr><td><strong><spring:message code="client.projects_number"/>: </strong></td><td>${requestScope.cp.totalProjects}</td>													
			      </tbody>
				</table>			
				   <div class="proect_details_btn_holder">
					    <a style="margin-right:5px;" title="<spring:message code='common.profile_view'/>" href="${pageContext.request.contextPath}/profiles/client/views?userID=<security:authentication property='principal.user.userID'/>" class="blue_gradient small"><i class="icon-menu"></i></a>
						<a style="margin-right:5px;" title="<spring:message code='common.profile_delete'/>" href="#" onclick="url='${pageContext.request.contextPath}/profiles/client/delete';$('#dialog1').dialog('open');" class="red_gradient small"><i class="icon-cancel-circled"></i></a>				  
						<a title="<spring:message code='common.post'/>" href="${pageContext.request.contextPath}/projects/create" class="green_gradient small"><i class="icon-forward"></i></a>				  
				   </div>
				</c:when>
				<c:otherwise>
				<br>
				<p><a href="${pageContext.request.contextPath}/profiles/client/create" class="standard_button large"><spring:message code="menu.account.client_become" /></a></p>
				</c:otherwise>
				</c:choose>
			</div>	
			
			</security:authorize>	
		</div>
	</div>
	<!-- end container -->
<script type="text/javascript">
var url;
$( "#dialog" ).dialog({ 
	autoOpen: false,
	modal: true,
	buttons: {
		"<spring:message code='common.confirm'/>": function() {window.location.href=url;},
		"<spring:message code='common.cancel'/>": function() {$( this ).dialog( "close" );}
	}
});
$( "#dialog1" ).dialog({ 
	autoOpen: false,
	modal: true,
	buttons: {
		"<spring:message code='common.confirm'/>": function() {window.location.href=url;},
		"<spring:message code='common.cancel'/>": function() {$( this ).dialog( "close" );}
	}
});
$( "#dialog2" ).dialog({ 
	autoOpen: false,
	modal: true,
	buttons: {
		"<spring:message code='common.confirm'/>": function() {window.location.href=url;},
		"<spring:message code='common.cancel'/>": function() {$( this ).dialog( "close" );}
	}
});
</script>
</section>
<!-- end #main_content -->