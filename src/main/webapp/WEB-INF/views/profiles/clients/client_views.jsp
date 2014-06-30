<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
$("#account").addClass("current");
</script>
<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<c:set var="isOwner" value="${user.userID == userLogged.userID}"></c:set>
<section id="main_content">
	<div class="top_title_holder add-bottom">


		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="menu.account.client_profile"/> </h3>
				<p><spring:message code="client.description"/> </p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li><spring:message code="menu.account.client_profile"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
	
  <c:choose>
  	<c:when test="${user.clientProfile.image eq null}" >
  		<c:set var="profileImgUri" value="${pageContext.request.contextPath}/resources/images/avatar_default.png"></c:set>
 	 </c:when>
 	 <c:otherwise>
 	    <c:set var="profileImgUri" value="${pageContext.request.contextPath}/files/image/client/${user.clientProfile.image.fileID}"></c:set>
 	 </c:otherwise>
  </c:choose>
  
	<div class="container">
	  <div class="sixteen columns add-top">
		<div class="three columns add-top add-bottom">
		    <div class="client_image">
 			  <a href="${profileImgUri}"  rel="prettyPhoto"><img alt="" src="${profileImgUri}" style = "width:100%; height:100%;"/></a>
			</div>
		</div>
		<div class="ten columns add-top">
			
			  <div class="client_name ten columns">
				  <h2> <span class="bold">${requestScope.user.clientProfile.clientName} </span><a href="${pageContext.request.contextPath}/profiles/client/update?userID=${user.userID}"><i class="icon-pencil"></i></a></h2>						
			  </div>
			
			<div class="company_name ten columns">
				<h2> <span class="bold">${requestScope.user.clientProfile.companyName}</span></h2>						
			</div>
			<div class="job_title ten columns">
				<h2> <span class="bold">${requestScope.user.clientProfile.jobTitle}</span></h2>						
			</div>
		</div>
		<div class="two columns add-top">
		   
		   <!-- ADD TO WATCHLIST  -->
		  		   <div class="proect_details_btn_holder">
<%-- 					  <a href="${pageContext.request.contextPath}/projects/create/?userID=<security:authentication property='principal.user.userID'/>" class="blue_gradient small"> View profile </a> --%>
					  <a href="${pageContext.request.contextPath}/projects/create" class="blue_gradient small"> <spring:message code="common.post"/> </a>
				   </div>
		  
		</div>
	</div>
		
	<div class="sixteen columns">
	 <div class="nine columns add-bottom">
	  <div class="title">
	    <h2> <span class="bold"><spring:message code="client.about"/> </span></h2>
	  </div>
      <blockquote class="bq">
		<p>${requestScope.user.clientProfile.description}</p>
	  </blockquote>
	 </div>
	 <div class="one columns add-bottom">
	 </div>
	 <div class="five columns add-bottom">
       <div class="title">
	      <h2> <span class="bold"><spring:message code="client.details"/> </span></h2>
	   </div>
	   <table class="project_details">
				<tbody>
				<tr><td><strong><spring:message code="common.rating"/>: </strong></td><td><div id="client_rating" data-average="${requestScope.user.clientProfile.rating}"></div></td></tr>
				<tr><td><strong><spring:message code="client.since"/>: </strong></td><td><fmt:formatDate value="${requestScope.user.creationDate}" pattern="dd/MM/yyyy"/></td></tr>				
				</tbody>
	   </table>
	 </div>	
	</div>	
	
<div class="basic" data-average="12" data-id="1"></div>

<!-- JS to add -->
<script type="text/javascript">
  
</script>	
	
	<div class="sixteen columns" style="margin-bottom: 0px;">
	  <div class="title">
	    <h2> <span class="bold"><spring:message code="client.my_job"/> </span></h2>
	  </div>
	  
	    <div class="snapshot-bar-buyer-c">
	    </div> 
    </div>
    <!-- HTML CODE -->

<!-- JS to add -->
    <div class="sixteen columns add-bottom" style="margin-top: 20px;">
       <div id="noresults">       </div>
  
     <div id="jobexperience" class="sixteen columns add-bottom">    
     
     </div>
      <nav id="pagination" class="pagination">
			</nav>
    </div>  
			
  </div>
	<!-- end container -->

</section>
<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:input path="category" id="category" value=""/>
<form:input path="skill.name" id="skill" value=""/>
<form:input path="search" id="search" value=""/>
<form:input path="itemSort" id="itemSort" value=""/>
<form:input path="dirSort" id="dirSort" value="asc"/>
<form:input path="page" id="page" value="1"/>
<form:input path="itemsForPage" id="itemsForPage" value="5"/>
<form:input path="userID" id="userID" value=""/>
<form:input path="status" id="status" value="COMPLETED"/>
</form:form>

<script>
$(document).ready(function(){
	$("#client_rating").jRating({
		  isDisabled : true,
		  rateMax:5,
		  length:5,
		  decimalLength:1
	 	});
});
	
	var result='<spring:message code="common.result" />';
	var contextPath="${pageContext.request.contextPath}";
    var my_rating="<spring:message code='client.my_rating'/>";
    var page_message='<spring:message code="common.page" />';
    var notfound='<spring:message code="common.notfound" />';
    var elements='<spring:message code="common.elements" />';
</script>
<script src="${pageContext.request.contextPath}/resources/merlin/js/proposalsProfileClientFilter.js"></script>
<!-- end #main_content -->