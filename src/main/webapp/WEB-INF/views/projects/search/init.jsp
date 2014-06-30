<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" >

$("#projects").addClass("current");
var category="<spring:message code='common.category'/>";
var name_table="<spring:message code='common.name'/>";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/projectsFilter.js" ></script>
