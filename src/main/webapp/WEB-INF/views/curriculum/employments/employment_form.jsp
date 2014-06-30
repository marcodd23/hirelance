<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript">
$(function() {
    $("#startJob").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
      });;
    $("#endJob").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
      });;
  });
</script>

<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="curriculum.title"/></h3>
				<p><spring:message code="employment.description"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}"><spring:message code="common.profile" /></a></li>
					<li>&#187;</li>
					<li><spring:message code="employment.path" /></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<spring:message code="employment.form.company" var="company_message"/>
		<spring:message code="employment.form.description" var="description_message"/>
		<spring:message code="employment.form.start" var="start_message"/>
		<spring:message code="employment.form.end" var="end_message"/>
			<form:form modelAttribute="employment" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<input type="hidden" name="curriculumID" value="${requestScope.curriculumID}"/>
				<form:hidden path="employmentID"/>
				
				<form:errors path="company" cssClass="errors_list"/>
				<form:input path="company" id="company" placeholder="${company_message}"/>
				
				<form:errors path="description" cssClass="errors_list"/>
				<form:input path="description" id="description" placeholder="${description_message}"/>
				
				<form:errors path="startJob" cssClass="errors_list"/>
				<form:input path="startJob" id="startJob" placeholder="${start_message }"/>		
				
				<form:errors path="endJob" cssClass="errors_list"/>
				<form:input path="endJob" id="endJob" placeholder="${end_message }"/>				
				<br>
				<button type="submit" class="standard_button medium"><spring:message code='common.confirm'/></button>
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->
