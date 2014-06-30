<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

      
<script type="text/javascript">
$(function() {
    $("#deliveryDate").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
      });;
  });
</script>

<section id="main_content">


	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3>Your Proposal</h3>
				<p>Fill the form below to post your proposal for this Project</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li>Became Client</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
         
         <form:form modelAttribute="proposal" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user">
                
            <input type="hidden" name="projectID" value="${projectID}">
            <input type="hidden" name="userID" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}">
			<form:errors path="description" cssClass="errors_list"/>
			<form:textarea path="description" id="description" placeholder="Describe Your Proposal"/>  
			<br>
			<br>
			<form:errors path="payBid" cssClass="errors_list"/>
            <form:input path="payBid" id="payBid" placeholder="My Earning in Euro"/>
<%--             <br>
            <form:errors path="deliveryDate" cssClass="errors_list"/>
			<form:input path="deliveryDate" id="deliveryDate" placeholder="Delivery Date"/>	            
            <br> --%>
            <br>
            <%-- <form:label path="deliveryTime" >Estimated Delivery Date</form:label> --%>
	        <form:select path="deliveryTime" id="deliveryTime">
                <option value="">Estimated Delivery Date</option>
                <form:option value="1"> Within 1 day </form:option>
                <form:option value="3"> Within 3 days </form:option>
                <form:option value="7"> Within 1 week </form:option>
                <form:option value="14"> Within 2 weeks </form:option>
                <form:option value="21"> Within 3 week </form:option>
                <form:option value="30"> Within 1 month </form:option>
                <form:option value="60"> Within 2 months </form:option>
                <form:option value="90"> Within 3 months </form:option>
                <form:option value="180"> Within 6 months </form:option>
                <form:option value="-1"> More than 6 months </form:option>s
            </form:select> 
              <br>
              <br>
			<button type="submit" class="standard_button medium">Sumbit</button>			 
		  </form:form>
		  
		</div>
    
		
	</div>
	<!-- end container -->


</section>
<!-- end #main_content -->