<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript">
$(function() {
    $("#graduationDate").datepicker({
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

				<h3><spring:message code="curriculum.title" /></h3>
				<p><spring:message code="education.description" /></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}"><spring:message code="common.profile" /></a></li>
					<li>&#187;</li>
					<li><spring:message code="education.path" /></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<spring:message code='education.form.institute' var="institute_message"/>
		<spring:message code='education.form.location' var="location_message"/>
		<spring:message code='education.form.date' var="date_message"/>
			<form:form modelAttribute="education" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<input type="hidden" name="curriculumID" value="${requestScope.curriculumID}"/>
				<form:hidden path="educationID"/>
				<form:errors path="institute" cssClass="errors_list"/>
				<form:input path="institute" id="institute" placeholder="${institute_message}"/>
				<form:input path="location" id="location" placeholder="${location_message}"/>
				<form:errors path="grade" cssClass="errors_list"/>
				<form:input path="grade" id="grade" placeholder="Grade"/>		
				<form:errors path="graduationDate" cssClass="errors_list"/>
				<form:input path="graduationDate" id="graduationDate" placeholder='${date_message}'/>				
				<br>
				<button type="submit" class="standard_button medium"><spring:message code='common.confirm'/></button>
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->
