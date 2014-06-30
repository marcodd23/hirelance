<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<p>Ciao <security:authentication property="principal.user.name"/></p>
<p>Hai il ruolo di <security:authentication property="principal.user.role.name"/></p>

<p><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></p>
