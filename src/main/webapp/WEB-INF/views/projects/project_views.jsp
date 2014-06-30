<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="isFreelance" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.freelanceProfile ne null}"></c:set>
<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<c:set var="isOwner" value="${project.clientOwner.clientID == userLogged.clientProfile.clientID}"></c:set>

<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
var userLoggedID = '${userLogged.clientProfile.clientID}';
var isClientOwner = ${isOwner};
var projectID = '${project}';

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/proposalsFilter.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/formatterDate.js" ></script>

<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<%-- <form:hidden path="category" id="category" value=""/>
<form:hidden path="skill.name" id="skill" value=""/>
<form:hidden path="search" id="search" value=""/> --%>
<form:hidden path="itemSort" id="itemSort" value=""/>
<form:hidden path="dirSort" id="dirSort" value="asc"/>
<form:hidden path="page" id="page" value=""/>
<form:hidden path="itemsForPage" id="itemsForPage" value="5"/>
<form:hidden path="projectID" id="projectID" value="${project.projectID}"/>
<form:hidden path="userID" id="userID" value="${userLogged.userID}"/>
</form:form>
<div id="dialog" title="<spring:message code='project.views.watchlist'/>">
  <p>
    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
<spring:message code="project.views.watchlist_added"/>  </p>
</div>
<div id="dialog_remove" title="<spring:message code='project.views.watchlist_remove'/>">
  <p>
    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
<spring:message code="project.views.watchlist_removed"/>  </p>
</div>
<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="project.views.head"/></h3>
				<p><spring:message code="project.views.subtitle"/></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/projects/views_all"><spring:message code="menu.projects"/></a></li>
					<li>&#187;</li>
					<li><spring:message code="project.views.head"/></li>
				</ul>
			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
  
    <div class="container">
       
       <div class="sixteen columns add-top add-bottom">
       
          <!--  <div class="project_info thirteen columns alpha add-bottom" style="float: left;" > -->

              <div class="title">
                 <h3>
                   <span class="bold" style="color: #2d94d7;">
                     ${project.title}
                   </span>
                   <c:if test="${isOwner}">
                   <a href="${pageContext.request.contextPath}/projects/update?projectID=${project.projectID}"><i class="icon-pencil"></i></a>
                 	</c:if>
                 </h3>
                </div> 
            
                <div style="margin-left: 5px; margin-bottom: 10px;">
                  <!-- <h6 id="jobCategory"><a href="">IT &amp; Programming</a> &gt; <a href="">Web Programming</a></h6> -->
                  <h6 id="jobCategory"><a href="">${project.projectSubCategory.parentCategory.name}</a> &gt; <a href="">${project.projectSubCategory.name}</a></h6>
                </div>
               <div class=" fifteen columns alpha" >
               
                 <!-- <div class="project_info nine columns alpha add-bottom"> -->
                 <div class="nine columns alpha">
               
                          
<!-- =================================================================== SCHEDA PROGETTO ========================================================================= -->
             
             <div class="project-card">
               <div id="project-Details">
<%--                 <div class="proposal-card-image">
                 <img alt="" src="${pageContext.request.contextPath}/resources/images/user-default.jpg" style = "width:100%; height:100%;"/>
                </div> --%>
                <div class="four columns">
                  <ul class="projectDetail" id="jobDetailLeft">
                    <li class="atomicJobDetail bid-icon-spr spr-posted_date bid-icon-spr-bg" title="Posted: <fmt:formatDate value="${project.postedDate}" pattern="dd/MM/yyyy"/>">
                       <span style="display: inline-block;"><spring:message code="project.views.posted"/>: <fmt:formatDate value="${project.postedDate}" pattern="dd/MM/yyyy"/></span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-time_left bid-icon-spr-bg" title="Time left: ${timeleft}">
                       <span style="display: inline-block;"><spring:message code="project.views.timeLeft"/>: ${project.timeLeft}</span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-job_location bid-icon-spr-bg" title="Location: ${project.country}">
                       <span style="display: inline-block;"><spring:message code="project.views.location"/>: ${project.country}</span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-budget bid-icon-spr-bg" title="Budget: $${project.budgetMIN} - $${project.budgetMAX}">
                       <span style="display: inline-block;">Budget: $${project.budgetMIN} - $${project.budgetMAX}</span>
                    </li>
                  </ul>
                </div>
                <div class="four columns" style="float: right;">
                
                    <div class="bidContainer" id="bidContainer2">
	                  <div class="title" style="margin-bottom: 0px;">
		              <h4> <span class="bold" >Status:</span></h4>
	                  </div>
                      <h6 class="grey"> ${projectStatus} </h6><spring:message code="project.views.closed"/> <fmt:formatDate value="${project.expiryDate}" pattern="dd/MM/yyyy"/> 
                      <br><br>
                   </div>
                
                
                  <%-- <ul class="projectDetail" id="jobDetailLeft">
                    <li class="atomicJobDetail bid-icon-spr spr-posted_date bid-icon-spr-bg" title="Posted: <fmt:formatDate value="${project.postedDate}" pattern="dd/MM/yyyy"/>">
                       <span style="display: inline-block;">Posted: <fmt:formatDate value="${project.postedDate}" pattern="dd/MM/yyyy"/></span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-time_left bid-icon-spr-bg" title="Time left: ${timeleft}">
                       <span style="display: inline-block;">Time left: ${timeleft}</span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-job_location bid-icon-spr-bg" title="Location: ${project.country}">
                       <span style="display: inline-block;">Location: ${project.country}</span>
                    </li>
                    <li class="atomicJobDetail bid-icon-spr spr-budget bid-icon-spr-bg" title="Budget: $${project.budgetMIN} - $${project.budgetMAX}">
                       <span style="display: inline-block;">Budget: $${project.budgetMIN} - $${project.budgetMAX}</span>
                    </li>
                  </ul> --%>
                </div>
   
               </div>    
               <div class="project-card-middle">
                 <div style="margin-left: 3px;  float: inherit; margin-top: 7px;">
                   <div class="project-card-title" style=" float: inherit;">
                     <span class="bold"><spring:message code="project.views.description"/></span>
                   </div>
                   <div style="margin-top: 8px; margin-right: 0px; margin-bottom: 18px;"></div>
                   <div class="project-card-text"> ${project.description}</div>
<!--                     <div class="project-card-skills">
                      Skills: 
                      <a href="/r/contractors/q-Android/">Android</a>,
                      <a href="/r/contractors/q-HTML5/">HTML5</a>, 
                      <a href="/r/contractors/q-iOS/">iOS</a>, 
                      <a href="/r/contractors/q-iPhone/">iPhone</a>
                    </div> -->
                 </div>
               </div> 
               <security:authorize access="isAuthenticated()">
				<security:authorize access="hasRole('user')">
               <c:if test="${not hiringClosed}">
               <c:if test="${not isOwner and not alreadyCandidate}">
                 <div class="project-card-bottom" >   
                   <div class="proect_details_btn_holder" style="float:right;">
                    <security:authorize access="isAuthenticated()">
                   <c:if test="${isFreelance}">
                   		<c:set var="watchlist" value="${userLogged.freelanceProfile.projectWatchList}"/>
<%--                    		${fn:length(watchlist)}
 --%>                   		<c:forEach items="${watchlist}" var="projectAdded">	
                   			<c:choose>
                   			<c:when test="${projectAdded.projectID == project.projectID}">
<%--                    				<c:out value="${projectAdded.projectID == project.projectID}"/>
 --%>                   				<c:set var="alreadyAdded" value="true"/>
                   			</c:when>
                   			<c:otherwise>
<%--                    			    <c:out value="${projectAdded.projectID == project.projectID}"/>
 --%>                   				<c:set var="alreadyAdded" value="false"/>
                   			</c:otherwise>
                   			</c:choose>
                   		</c:forEach>
                   		<c:choose>
                   		<c:when test="${!alreadyAdded}">
                      		<a href="javascript:addWatchList(${project.projectID});" class="blue_gradient small"> <spring:message code="project.views.watchlist"/> </a>
                 	 	</c:when>
                 	 	<c:otherwise>
                 	 		<a href="javascript:removeWatchList(${project.projectID});" class="green_gradient small"> <spring:message code="project.views.watchlist_remove"/> </a>
                 	 	</c:otherwise>
                 	 	</c:choose>
                  	</c:if>
                   </security:authorize>
                    <c:if test="${isFreelance}">
                     <a href="${pageContext.request.contextPath}/projects/new_proposal?projectID=${project.projectID}" class="blue_gradient small" style="margin-left:2px;'"> <spring:message code="project.views.add_proposal"/> </a>
			       </c:if>
			       </div>      
                 </div>   
               </c:if>
              </c:if> 
              </security:authorize>
              </security:authorize>
                         
             </div>
             
<!-- ================================================================== FINE SCHEDA PROGETO ===================================================================================== -->


   <!-- ======================= Skills ======================= -->             
              </div>     
                <div class="five columns alpha add-bottom" style="float: right;">
                   <article class="widget">
					  <div class="title">
						 <h2> <span class="bold" style="margin-left: 5px;">Skills</span></h2>
					  </div>
					  <ul class="tagList">
                         <c:forEach var="skill" items="${project.skills}">
						     <li><a href="#">${skill.name}</a></li>
						 </c:forEach>
                      </ul>	
				   </article>
                </div>  
             </div>
             
    <!-- ======================= FINE Skills ======================= -->     
         
             
      
      <!-- ////////////////////////////////////////////// INIZIO PARTE DELLA LISTA DI PROPOSAL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ -->
       <div class="sixteen columns" style="margin-bottom: 0px;">
        <div class="title" style="margin-bottom: 0px;">
	      <h2> <span class="bold" style="margin-left: 5px;"><spring:message code="project.views.proposals"/> </span></h2>
	    </div> 
	   </div> 
          
     <div class="sixteen columns add-bottom" style="margin-left: 0px;">
      <div id="noresults" style="margin-bottom: 0px;">
          <span class="bold" id="counter">0 risultati</span>
          <span style="float:right;">
             <span >Sort By: </span>
             <!-- <a id="date" href="javascript:addOrder('date');">Submitted Date<i class="icon-down-dir"></i></a>  --> 
             <a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>        
          </span>
      </div>
     <div id="proposal-container" class="sixteen columns add-bottom" style="margin-right: 0px; margin-left: 0px;">
   
      <!-- ========================================================================================================= -->
       
       <div id="result">
       
       <!-- ======================================= SINGOLA PROPOSTA =============================================== -->
             
<%--              <div class="proposal-card">
               <div class="proposal-card-top">
                <div class="proposal-card-image">
                 <img alt="" src="${pageContext.request.contextPath}/resources/images/user-default.jpg" style = "width:100%; height:100%;"/>
                </div>
                <div class="proposal-card-info">
                  <div class="proposal-card-name">
                   <span class="bold">Freelance Name</span>
                  </div>
                  <div class="proposal-card-info-detail">
                     <div class="proposal-card-country italy-flag" title="italy">Italy</div>
                     <div class="proposal-card-separator">|</div>
                     <div id="subcategory" style="float: left;" title="Sub Category">IT & Programming</div>
                     <div class="proposal-card-separator">|</div>
                     <div id="jobhistory" style="float: left;" title="job History">3 jobs</div>
                     <div class="proposal-card-separator">|</div>
                     <div class="eol-rating-stars-small" style="float: left;" title="Average Job Rating">  
                       <div class="jStar" style="height: 20px; background-image: url(http://localhost:8080/hirelance/resources/jquery/plugin/star-rating/icons/small.png); background-position: initial initial; background-repeat: repeat no-repeat;">
                       </div>                 
                     </div>
                     <div class="proposal-card-separator">|</div>
                     <!-- <div class="proposal-card-icon proposal-icon-portfolio" style="float: left; padding-left: 25px;" id="portfolio">Portfolio</div> -->
                     <div style="float: left;" id="portfolio">
                       <div class="proposal-card-icon proposal-icon-portfolio" style="float: left;"></div>
                       <div class="proposal-portfolio-link" style="float: left;">Portfolio</div>
                     </div>
                  </div>
                </div>
               </div>    
               <div class="proposal-card-detail">
                 <div class="proposal-card-left" style="margin-left: 3px;  float: inherit;">
                   <div class="proposal-card-title" style=" float: inherit;">
                     <span class="bold">Proposal</span>
                   </div>
                   <div class="proposal-card-date">Sep 27, 2013 - 1:55 PM ET</div>
                   <div class="proposal-card-text ">
                   We Are Experts in PHP , with CMS like WordPress , joomla , magento . 
                   also We have good hands On Experience  in Designing HTML , CSS , java Script , j-query , 
                   good Experts on database ,  Ms Sql , My Sql , Oracle , sqlite ..  We Are online 24*7 for Client response ..
                    on Skype and Gtalk  Our Clients of last three month...
                    </div>
                    <div class="proposal-card-skills">
                      Skills: 
                      <a href="/r/contractors/q-Android/">Android</a>,
                      <a href="/r/contractors/q-HTML5/">HTML5</a>, 
                      <a href="/r/contractors/q-iOS/">iOS</a>, 
                      <a href="/r/contractors/q-iPhone/">iPhone</a>
                    </div>
                 </div>
               </div> 
               <div class="proposal-card-bottom" >
                 <div class="proposal-price">$95000 - Estimated Delivery: 30 giorni</div>
                 
                 <div class="proect_details_btn_holder" style="float:right;">
				   <a href="/hirelance/projects/new_proposal?projectID=1" class="green_gradient small" > Hire this </a>
			     </div> 
                 <div class="proposal-message">
                    <a href="#" title="Send Message To This Freelance" >Contact Freelance</a>
                 </div>          
               </div>              
             </div> --%>
             
        <!-- =========================================== FINE SINGOLA PROPOSTA ================================================== -->
        
       </div>
           
       <!-- ======================================================================================================================= -->
         
         			<nav id="pagination" class="pagination">
			</nav>
           
     </div>   
    </div>  
          
  
      <!-- ////////////////////////////////////////////// FINE LISTA DI PROPOSAL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ -->
      </div>
    </div>
  
	<!-- end container -->

</section>
<!-- end #main_content -->

<script>
function addWatchList(projectID){
	$.ajax({
		url:contextPath+"/profiles/freelancer/addProjectToWatchList",
		type:'GET',
		data:{projectID:projectID},
	}).done(function(){
		$( '#dialog' ).dialog( 'open' );
	});
}
$( "#dialog" ).dialog({ 
	autoOpen: false,
	modal: true,
	//position: { my: "center top", at: "top top", of: window },
	buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
          window.location.href="${pageContext.request.contextPath}/projects/views?projectID=${project.projectID}";
        }
      }
});
function removeWatchList(projectID){
	$.ajax({
		url:contextPath+"/profiles/freelancer/removeProjectToWatchList",
		type:'GET',
		data:{projectID:projectID},
	}).done(function(){
		$( '#dialog_remove' ).dialog( 'open' );
	});
}
$( "#dialog_remove" ).dialog({ 
	autoOpen: false,
	modal: true,
	//position: { my: "center top", at: "top top", of: window },
	buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
          window.location.href="${pageContext.request.contextPath}/projects/views?projectID=${project.projectID}";
        }
      }
});
</script>