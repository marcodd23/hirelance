<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3><spring:message code="project.my.title"/></h3>
				<p><spring:message code="project.my.description"/></p>
			</div>
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li>&#187;</li>
					<li><spring:message code="project.my.title"/></li>
				</ul>
			</div>
		</div>