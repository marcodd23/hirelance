<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3><spring:message code="common.search_job"/></h3>
				<p><spring:message code="common.search_job.description"/></p>
			</div>
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li>&#187;</li>
					<li><spring:message code="common.search_job"/></li>
				</ul>
			</div>
		</div>