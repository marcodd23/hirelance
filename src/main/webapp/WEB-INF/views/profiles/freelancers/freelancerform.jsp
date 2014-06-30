<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="menu.account.freelancer_become"/></h3>
				<p><spring:message code="freelancer.form.head"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li><spring:message code="menu.account.freelancer_become"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
			<!--<form action="${pageContext.request.contextPath}/profiles/freelancer/create" method="POST" class="form-user">
				<input type="hidden" name="userID" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}">
				<input type="text" name="alias" placeholder="Alias"/>
				<select name="mainCategory">
				<c:forEach var="mainCategory" items="${requestScope.mainCategories}">
				<option value="${mainCategory.mainID}">${mainCategory.name}</option>
				</c:forEach>
				</select>
				<button type="submit">submit</button>
			</form>!-->
			<spring:message code="freelancer.form.name" var="name_message"/>
			<spring:message code="freelancer.form.select" var="select_message"/>
			<spring:message code="freelancer.form.change" var="change_message"/>
			<spring:message code="freelancer.form.remove" var="remove_message"/>
	
	<!-- =================================== INIZIO FORM  ========================================== -->		
			<form:form modelAttribute="freelanceProfile" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" enctype="multipart/form-data" cssClass="form-user">		
			<form:hidden path="freelanceID"/>
			<form:errors path="freelanceName" cssClass="errors-list"/>
			<form:input path="freelanceName" id="freelanceName" placeholder="${name_message}"/>		
			<form:select path="category.catID" id="category">
    			<form:options items="${categories}" itemLabel="name" itemValue="catID"/>
    		</form:select>
			<br>
			<c:if test="${!empty imageErrors}">
			  <ul>
	             <c:forEach items="${imageErrors}" var="error">
		              <li style="color:red"><spring:message code="${error}"/></li>
	             </c:forEach>
	          </ul>
	        </c:if>  
			   <div class="fileinput fileinput-new" data-provides="fileinput">
  					<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
 					<div>
    				  <span class="btn btn-default btn-file">
    				    <span class="fileinput-new">${select_message}</span>
    				    <span class="fileinput-exists">${change_message}</span>
    				    <input type="file" name="imagefile">
    				  </span>
    				<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">${remove_message}</a>
  					</div>
				</div>
				<br>
			<input type="hidden" name="userID" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}">
			<button type="submit" class="standard_button medium"><spring:message code="common.confirm"/></button>			
			</form:form>
			
	 <!-- ======================================= FINE FORM ====================================== -->		
			
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->