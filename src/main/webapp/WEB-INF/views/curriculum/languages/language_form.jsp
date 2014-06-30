<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="curriculum.title" /></h3>
				<p><spring:message code="language.description" /></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}"><spring:message code="common.profile" /></a></li>
					<li>&#187;</li>
					<li><spring:message code="language.path" /></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<spring:message code="language.form.name" var="name_message"/>
		<spring:message code="language.form.low" var="low_message"/>
		<spring:message code="language.form.good" var="good_message"/>
		<spring:message code="language.form.optimal" var="optimal_message"/>
		<spring:message code="language.form.excellent" var="excellent_message"/>
		<spring:message code="language.form.native" var="native_message"/>
		<spring:message code="language.form.certification" var="certification_message"/>
		
			<form:form modelAttribute="language" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<input type="hidden" name="curriculumID" value="${requestScope.curriculumID}"/>
				<form:hidden path="langID"/>
				<form:errors path="name" cssClass="errors_list"/>
				<form:input path="name" id="name" placeholder="${name_message}"/>
				<br>
				<form:label path="reading"><spring:message code="language.form.reading"/></form:label><br>
				<form:select path="reading" id="reading">
					<form:option value="scarso" label="${low_message}"/>
					<form:option value="buono" label="${good_message}"/>
					<form:option value="ottimo" label="${optimal_message}"/>
					<form:option value="eccellente" label="${excellent_message}"/>
					<form:option value="madrelingua" label="${native_message}"/>
				</form:select>
				<br>
				<form:label path="speaking"><spring:message code="language.form.speaking"/></form:label><br>
				<form:select path="speaking" id="speaking">
					<form:option value="scarso" label="${low_message}"/>
					<form:option value="buono" label="${good_message}"/>
					<form:option value="ottimo" label="${optimal_message}"/>
					<form:option value="eccellente" label="${excellent_message}"/>
					<form:option value="madrelingua" label="${native_message}"/>
				</form:select>
				<br>
				<form:label path=""><spring:message code="language.form.listening"/></form:label><br>
				<form:select path="listening" id="listening">
					<form:option value="scarso" label="${low_message}"/>
					<form:option value="buono" label="${good_message}"/>
					<form:option value="ottimo" label="${optimal_message}"/>
					<form:option value="eccellente" label="${excellent_message}"/>
					<form:option value="madrelingua" label="${native_message}"/>
				</form:select>
				<br>
				<form:label path="writing"><spring:message code="language.form.writing"/></form:label><br>
				<form:select path="writing" id="writing">
					<form:option value="scarso" label="${low_message}"/>
					<form:option value="buono" label="${good_message}"/>
					<form:option value="ottimo" label="${optimal_message}"/>
					<form:option value="eccellente" label="${excellent_message}"/>
					<form:option value="madrelingua" label="${native_message}"/>
				</form:select>
				<br>
				<form:input path="certification" id="certification" placeholder="${certification_message}"/>
				<br>
				<button type="submit" class="standard_button medium">
				<c:choose>
      				<c:when test="${!requestScope.delete}">
						<spring:message code='common.confirm'/>
					</c:when>
      				<c:otherwise>
      					<spring:message code='common.confirm'/>
      				</c:otherwise>
      			</c:choose>
      			</button>
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->