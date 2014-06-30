<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<input type="hidden" value="" id="projectsNumber"/>
<script src="${pageContext.request.contextPath}/resources/merlin/js/projectComplete.js"></script>
  
	<div id="dialog-form" title="<spring:message code="project.rate" />">
		<span class="validateTips">&nbsp;</span>
		 
		<form:form modelAttribute="feedback" id="feedbackForm">
			<fieldset>
				<div class="basic" data-average="1" data-id="3"></div>
				<form:hidden path="grade" id="rate"/>
				<form:hidden path="type" id="type"/>
				<br><label for="comment"><spring:message code="project.comment"/> (max 60 <spring:message code="common.chars"/>)</label><br>
				<form:input path="remark" id="comment" cssClass="text ui-widget-content ui-corner-all"/>
				<form:hidden path="jobEvaluated.refProject.projectID" id="projectIDevaluated"/>
			</fieldset>
		</form:form>
	</div>