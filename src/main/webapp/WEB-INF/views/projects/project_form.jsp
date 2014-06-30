<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<%-- <c:set var="string1" value="errorMsg"/> --%>

<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
var projectID='${project.projectID}';
var subCategorySelectedID = '${project.projectSubCategory.catID}';
var mainCategoryPreSelected = '${mainCategoryPreSelected}';
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/project_form.js" ></script>

                 
<section id="main_content">


	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="project.form.head"/></h3>
				<p><spring:message code="project.form.description"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/client/views?userID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}"><spring:message code="common.profile"/></a></li>
					<li>&#187;</li>
					<li><spring:message code="project.form.head"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		
   <!--  ========================================= INIZIO FORM ============================================= -->      
         <spring:message code="project.form.name" var="name_message"/>
         <spring:message code="project.form.description_input" var="description_message"/>
         <form:form modelAttribute="project" action="${pageContext.request.contextPath}${requestScope.action}" method="POST" cssClass="form-user" id="projectForm">
             
            <form:hidden path="projectID"/>   
            <input type="hidden" name="userID" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.userID}">
			<form:errors path="title" cssClass="errors_list"/>
			<form:input path="title" id="title" placeholder="${name_message}"/>
			<form:errors path="description" cssClass="errors_list"/>
			<form:textarea path="description" id="description" placeholder="${description_message}"/>
			<br>
			<br>
		    <c:if test="${!empty mainCategorySelectError}">
		              <span style="color:red"><spring:message code="${mainCategorySelectError}"/></span>
	        </c:if> 
			<select name="projectMainCategory" id="projectMainCategory">
			    <c:choose>
			       <c:when test="${not empty project.projectSubCategory and project.projectSubCategory.catID!=0 }">
			         <option value='${project.projectSubCategory.parentCategory.catID}' selected="selected">${project.projectSubCategory.parentCategory.name}</option>
			       </c:when>
			       <c:otherwise>
			         <option value="0" selected="selected">-- Select a category --</option>
			         <%-- <option value='' selected="selected">-- <spring:message code="project.form.category"/> --</option> --%>
			       </c:otherwise>
			    </c:choose>
    			<c:forEach items="${categories}" var="category">
    			  <option value="${category.catID}">${category.name}</option>
    			</c:forEach>
    		</select>
    		<br>
    		<form:errors path="projectSubCategory.catID" cssClass="errors_list"/>
            <form:select path="projectSubCategory.catID" id="projectSubCategory">
                <option value="0">-- No category selected --</option>
                <%-- <option value="">-- <spring:message code="project.form.catCategory"/> --</option> --%>
                <!-- <option selected="selected">-- Select a subcategory --</option> -->
    		    <form:options  itemLabel="name" itemValue="catID"/>
            </form:select> 
            <br>
            <br>
            <form:errors path="skills" cssClass="errors_list"/>           
            <form:select path="skills" multiple="true" id="skills" itemLabel="name" itemValue="skillID" class="chosen-select">
            </form:select>        
            
            <br>
            <br>   
            <br> 
            <span style="color:red" id="budgetError">I campi budget devono esssere numeri</span>
            <form:errors path="budgetMIN" cssClass="errors_list"/>
            <form:input path="budgetMIN" id="budgetMIN"  cssClass="errors_list" placeholder="Budget Min"/>
            <form:errors path="budgetMAX" cssClass="errors_list"/>
            <form:input path="budgetMAX"  id="budgetMAX" cssClass="errors_list" placeholder="Budget Max"/>
            <br>
            <br>
            <label for="daysToPost"><span class="bold" style="color: #818181; padding-left: 5px;"><spring:message code="project.form.postTime"/> </span></label>
            <br>
            <select name="daysToPost" id="daysToPost">
                <!-- <option value="15" selected="selected">Post This Job For</option> -->
                <option value="1"> 1 <spring:message code="project.form.days"/> </option>
                <option value="2"> 2 <spring:message code="project.form.days"/> </option>
                <option value="3"> 3 <spring:message code="project.form.days"/> </option>
                <option value="7"> 7 <spring:message code="project.form.days"/> </option>
                <option value="15" selected="selected"> 15 <spring:message code="project.form.days"/> </option>
                <option value="30"> 30 <spring:message code="project.form.days"/> </option>
                <option value="60"> 60 <spring:message code="project.form.days"/> </option>
                <option value="90"> 90 <spring:message code="project.form.days"/> </option>
            </select> 

	       		<br>
	       <form:errors path="country" cssClass="errors_list"/>
	       <form:select path="country" id="country">
                <option value=""><spring:message code="project.form.location"/> </option>
                <form:option value="anywhere"> anywhere </form:option>
                <form:option value="Italy"> Italy </form:option>
                <form:option value="Germany"> Germany </form:option>
                <form:option value="Spain"> Spain </form:option>
                <form:option value="UK"> UK </form:option>
                <form:option value="USA"> USA </form:option>
            </form:select> 
	       		
              <br>
              <br>
			<button onclick="event.preventDefault();mySubmit();" class="standard_button medium"><spring:message code="common.confirm"/> </button>			 
		  </form:form>
		</div>		
	</div>
	<!-- end container -->
<script type="text/javascript">
$(document).ready(function(){
	$("#budgetError").hide();
});

function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}
function mySubmit(){
	var budgetMin=$("#budgetMIN").val();
	var budgetMax=$("#budgetMAX").val();
	if(isNumber(budgetMin)){
		//alert("min numero");
		if(isNumber(budgetMax)){
			$("#projectForm").submit();
			//alert("max numero");
		}
		else{
			$("#budgetError").show();
		}
	}
	else{
		$("#budgetError").show();
	}
	//alert(budgetMax+budgetMin);
}
</script>

</section>
<!-- end #main_content -->
