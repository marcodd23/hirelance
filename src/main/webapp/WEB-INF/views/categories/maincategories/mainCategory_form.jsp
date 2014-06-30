<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$("#main").addClass("current");
</script>
<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3>Manage Main Categories</h3>
				<p>Add new main category</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/categories/main/views">View main categories</a></li>
					<li>&#187;</li>
					<li>Add main category</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
			<form:form modelAttribute="mainCategory" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<form:hidden path="catID"/>
				<form:errors path="name" cssClass="errors_list"/>
				<form:input path="name" id="name" placeholder="Name"/>
				<br>
				<button type="submit" class="standard_button medium">
				<c:choose>
      				<c:when test="${!requestScope.delete}">
						submit
					</c:when>
      				<c:otherwise>
      					delete
      				</c:otherwise>
      			</c:choose>
      			</button>
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->