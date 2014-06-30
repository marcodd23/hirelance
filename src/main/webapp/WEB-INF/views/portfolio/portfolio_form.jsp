<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<section id="main_content">
	<div class="top_title_holder add-bottom">
		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3>Portfolio</h3>
				<p><spring:message code="portfolio.description"/></p>
			</div>
			<!-- end nine -->
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${user.userID}"><spring:message code="common.profile"/></a></li>
					<li>&#187;</li>
					<li><spring:message code="portfolio.path"/></li>
				</ul>
			</div>
			<!-- end seven -->
		</div>
		<!-- end container -->
	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<spring:message code="portfolio.form.title" var="title_message" />
		<spring:message code="portfolio.form.description" var="description_message" />
		<spring:message code="portfolio.form.select" var="select_message" />
		<spring:message code="portfolio.form.change" var="change_message" />
		<spring:message code="portfolio.form.remove" var="remove_message" />
			<form:form modelAttribute="portfolioItem" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" enctype="multipart/form-data" cssClass="form-user">
			
			<form:errors path="title" cssClass="errors-list"/>
			<form:input path="title" id="title" placeholder="${title_message}"/>
			
			<form:errors path="description" cssClass="errors-list"/>
			<form:textarea path="description" id="description" placeholder="${description_message}"/>
			
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
			<button type="submit" class="standard_button medium"><spring:message code="common.confirm"/></button>			
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->