<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>



<%-- <c:set var="isFreelance" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.freelanceProfile ne null}"></c:set> --%>
<%-- <c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set> --%>
<%-- <c:set var="isOwner" value="${project.clientOwner.clientID == userLogged.clientProfile.clientID}"></c:set> --%>
 
<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
//var messageAction = '${pageContext.request.contextPath}${messageActionUri}';
var userLogged = "${userLogged}";
var loggedFreelanceID = "${freelanceProfileID}";
var loggedClientID = "${clientProfileID}";
//var projectID = '${projectID}';
//var proposalID = '${proposalID}';
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/inbox/inbox.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/formatterDate.js" ></script>

<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:hidden path="itemSort" id="itemSort" value=""/>
<form:hidden path="dirSort" id="dirSort" value="asc"/>
<form:hidden path="page" id="page" value=""/>
<form:hidden path="itemsForPage" id="itemsForPage" value="5"/>
<form:input path="proposalID" id="proposalID" value=""/>
<%-- <form:input path="projectID" id="projectID" value="${projectID}"/> --%>
<form:input path="userID" id="userID" value="${userLogged}"/>
<form:input path="conversationType" id="conversationType" value=""/>
</form:form>


<section id="main_content">

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3>Inbox</h3>
				<p></p>

			</div>
			<!-- end nine -->

			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li>Freelancer profile</li>
				</ul>
			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	
	
	<div class="container">
	
	    <div class="sixteen columns ">
	       		<div class="title">
					   <h3><span class="bold">Inbox</span></h3>				   		
 				</div>	
	    </div>
	    
	    <div class="four columns">
	     		<div class="title">
					   <h5><span class="bold">Sections</span></h5>				   		
 				</div>	
	    </div>
	    <div class="twelve columns">
	      <div class="title" >
				<h5> <span class="bold" id="bodyTitle"></span></h5>	
		  </div>
	    </div>
	    
	   <div class="four columns add-bottom">
	       
	       <aside class="sidebar" style="min-height: 600px;">
	
<!--  				<div class="title"> -->
<!-- 					   <h5><span class="bold">Workroom</span></h5>				   		 -->
<!--  				</div>			 -->
				<article class="widget latest_posts">
				
					<ul id="categoryList">
					
					<li style="margin-bottom: 10px;"> <a href="${pageContext.request.contextPath}/inbox/views" ><span class="bold">Proposals Messages</span></a> </li>
					<!-- <li style="margin-bottom: 10px;"> <a id="3" href="javascript:addCategory('3');"><span class="bold">Proposals Messages</span></a> </li> -->
					
					<li style="margin-bottom: 10px;"> <a id="1" href="javascript:workroomInboxBody();"><span class="bold">Workroom Messages</span></a> </li>
					
					<li style="margin-bottom: 10px;"> <a id="1" href="javascript:systemInboxBody();"><span class="bold">System Messages</span></a> </li>
					
					<!-- <li style="margin-bottom: 10px;"> <a id="2" href="javascript:addCategory('2');"><span class="bold">Web designer</span></a> </li> -->
						
					</ul>	
				</article><!-- end .latest_posts -->
				
				<!-- <article class="widget">
					<div class="widget_title">
						<h2> <span class="bold">Skills</span> </h2>
						<div class="title_sep_container"><div class="title_sep"></div></div>
					</div>end .title
					

					end ul.tagList
				</article> -->
				
			</aside>
	   
	   </div>
	   
	   <div class="twelve columns add-bottom">
	   

	   
<!-- ====================================================== CORPO CENTRALE ========================================================= -->
       <div id="result">
      
       </div>
       
       <div id="messagesBody-inbox">
       </div>
       
<!--        <table class="msgTable" id="msgTable">
         <thead id="msgTableHead">
           <tr class="msgRowHead">
			 <th id="firstcol" width="4px"></th>
			 <th id="firsttextcol" width="15%">Sender</th>
			 <th id="secondtextcol" width="75%">Message</th>
			 <th id="thirdtextcol" width="15px">Date/Time</th>
			 <th id="lastcol" width="5px"></th>
          </tr>
       </thead>
       <tbody id="messages">
       
       </tbody>  
       </table> -->
             
<!-- ====================================================== FINE CORPO CENTRALE ========================================================= -->
	   
	   <nav id="pagination" class="pagination">
			</nav>
	   
	   
	   
	   </div>
	
	
	
	
	
	
	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- end container -->

</section>
<!-- end #main_content -->	
