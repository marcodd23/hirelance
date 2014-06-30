<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<%-- <c:set var="string1" value="errorMsg"/> --%>

<section id="main_content">


	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="menu.account.client_become"/></h3>
				<p><spring:message code="client.form.head"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li><spring:message code="menu.account.client_become"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
         <spring:message code="client.form.name" var="name_message"/>
         <spring:message code="client.form.company" var="company_message"/>
         <spring:message code="client.form.jobTitle" var="jobTitle_message"/>
         <spring:message code="client.form.description" var="description_message"/>
         
         <!-- ============================== INIZIO FORM ================================== -->
         <form:form modelAttribute="clientProfile" enctype="multipart/form-data" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
         
            <form:hidden path="clientID"/> 
            <input type="hidden" name="userID" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}">
			<form:errors path="clientName" cssClass="errors_list"/>
			<form:input path="clientName" id="clientName" placeholder="${name_message}"/>
			<form:errors path="companyName" cssClass="errors_list"/>
			<form:input path="companyName" id="companyName" placeholder="${company_message}"/>
			<form:errors path="jobTitle" cssClass="errors_list"/>
			<form:input path="jobTitle" id="jobTitle" placeholder="${jobTitle_message}"/>
			<form:errors path="description" cssClass="errors_list"/>
			<form:textarea path="description" id="description" placeholder="${description_message}"/>
			
			<c:if test="${!empty imageErrors}">
			  <ul>
	             <c:forEach items="${imageErrors}" var="error">
		              <p style="color:red"><spring:message code="${error}"/></p>
	             </c:forEach>
	          </ul>
	        </c:if>  
				<div class="fileinput fileinput-new" data-provides="fileinput">
  					<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
 					<div>
    				<span class="btn btn-default btn-file">
    				<span class="fileinput-new"><spring:message code="client.form.select"/></span>
    				<span class="fileinput-exists"><spring:message code="client.form.change"/></span>
    				<input type="file" name="imagefile">
    				</span>
    				<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput"><spring:message code="client.form.remove"/></a>
  					</div>
				</div>

	       		<br>
			<button type="submit" class="standard_button medium"><spring:message code="common.confirm"/></button>			
		  </form:form>
		<!-- =========================================== FINE FORM ================================================= -->  
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->
