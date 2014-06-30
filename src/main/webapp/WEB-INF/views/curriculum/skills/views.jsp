<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<section id="main_content">
	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="skill.title"/></h3>
				<p><spring:message code="skill.description"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}"><spring:message code="common.profile"/></a></li>
					<li>&#187;</li>
					<li><spring:message code="skill.title"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
	<script>
	
  $(function() {
    var availableTags = [${skillArray}];
    $( "#skills" ).autocomplete({
      source: availableTags,
      autoFocus: true,
      select: function( event, ui ) {
    	  window.location.href="${pageContext.request.contextPath}/curriculum/skills/add?curriculumID=${curriculum.curriculumID}&name="+ui.item.value;
    	  //addAction('ok',ui);
  	  }
    });});
  </script>
		<div class="sixteen columns add-top add-bottom">
		<div class="form-user">
		<label for="skills"><spring:message code="skill.form.title"/></label>
		<input id="skills" type="text" name="skill"/>
		<ul class="skillList">
		<c:forEach items="${curriculum.cvSkills}" var="skill">
		<li><a href="${pageContext.request.contextPath}/curriculum/skills/delete?curriculumID=${curriculum.curriculumID}&skillID=${skill.skillID}" title="<spring:message code="common.cancel"/>">${skill.name}</a></li>
		</c:forEach>
		</ul>
		</div>
		
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->