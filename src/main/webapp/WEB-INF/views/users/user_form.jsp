<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<script>
$("#users").addClass("current");

</script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	var del = "${requestScope.delete}"; 
	if (del == "true" ) {
		$(":input[type='text']").each(function () { $(this).attr('disabled','disabled'); });	
	}
	
});
</script>

<section id="main_content">


	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="join.title" /></h3>
				<p><spring:message code="join.description" /></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><spring:message code="common.join" /></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
<security:authorize access="hasRole('admin')">
<script>
function mySubmit(){
	$(".form-user").submit();

}
</script>
</security:authorize>
<security:authorize access="hasRole('user')">
<script>
function mySubmit(){
	alert("Verrà effettuato il logout: dovrai loggarti di nuovo per rendere effettive le modifiche");
	$(".form-user").submit();

}
</script>
</security:authorize>
<security:authorize access="!isAuthenticated()">
<script>
function mySubmit(){
	$(".form-user").submit();

}
</script>
</security:authorize>

<c:set var="actionUrl" value="" />

	<div class="container">
		<div class="sixteen columns add-top add-bottom">
			<form:form modelAttribute="user" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<form:hidden path="userID"/>
				<form:errors path="name" cssClass="errors_list"/>
				<form:input path="name" id="name" placeholder="Name"/>
				<form:errors path="surname" cssClass="errors_list"/>
				<form:input path="surname" id="surname" placeholder="Surname"/>
				<form:errors path="email" cssClass="errors_list"/>
				<form:input path="email" id="email" placeholder="Email"/>		
				<c:if test="${!requestScope.delete}">
				<input type="text" id="confirmEmail" name="confirmEmail" placeholder="Confirm Email"/>
				</c:if>	
				<form:errors path="username" cssClass="errors_list"/>
				<form:input path="username" id="username" placeholder="Username"/>				
				<form:errors path="password" cssClass="errors_list"/>
				<form:password path="password" id="password" placeholder="Password"/>
				<c:if test="${!requestScope.delete}">
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password"/>
			    </c:if>
				<br>
				<button onclick="event.preventDefault();mySubmit();" class="standard_button medium">
				<c:choose>
      				<c:when test="${!requestScope.delete}">
						<spring:message code="common.confirm" />
					</c:when>
      				<c:otherwise>
      					<spring:message code="common.trash" />
      				</c:otherwise>
      			</c:choose>
      			</button>
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->