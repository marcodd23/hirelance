<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main_content">


	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3>New Message</h3>
				<p>Write Message about proposal</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>Join</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
			<form:form modelAttribute="message" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
				<input type="hidden" name="proposalID" value="${proposalID}">
				<form:textarea path="text" id="message" placeholder="Message"/>			
                <input type="submit" class="standard_button medium" value="Send" />
			</form:form>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->