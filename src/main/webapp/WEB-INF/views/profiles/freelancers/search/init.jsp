<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" >
var jobs='<spring:message code="common.jobs" />';
$("#freelancers").addClass("current");
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/freelancersFilter.js" ></script>
