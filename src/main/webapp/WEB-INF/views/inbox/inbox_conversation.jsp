<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%-- <c:set var="isFreelance" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.freelanceProfile ne null}"></c:set> --%>
<%-- <c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set> --%>
<%-- <c:set var="isOwner" value="${(not empty userLogged.clientProfile) and (project.clientOwner.clientID == userLogged.clientProfile.clientID)}"></c:set> --%>
<%-- <c:set var="isOwner" value="${project.clientOwner.clientID == userLogged.clientProfile.clientID}"></c:set> --%>
 
<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
var messageAction = contextPath + '${messageActionUri}';
var userLogged = "${userLogged}";
var loggedFreelanceID = "${loggedFreelanceProfileID}";
var loggedClientID = "${loggedClientProfileID}";
var proposalDeliveryTime = "${proposal.deliveryTime}";
var proposalPayBid = "${proposal.payBid}";
var projectID = '${proposal.refProject.projectID}';
var proposalID = '${proposal.proposalID}';
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/inbox/inboxConversation.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/formatterDate.js" ></script>

<!-- Questo form viene usato per la query di paginazione di tutti i messaggi di una proposal  -->
<form:form modelAttribute="filterDataRequest" id="filterDataForm">
<form:hidden path="itemSort" id="itemSort" value=""/>
<form:hidden path="dirSort" id="dirSort" value="asc"/>
<form:hidden path="page" id="page" value=""/>
<form:hidden path="itemsForPage" id="itemsForPage" value="5"/>
<form:input path="proposalID" id="proposalID" value="${proposal.proposalID}"/>
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

				<h3>WorRoom</h3>
				<p>Mange your job workflow</p>

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
					   <h3><span class="bold">Progetto Hirelance</span></h3>				   		
 				</div>	
	    </div>
	    
	    <div class="four columns">
	     		<div class="title">
					   <h5><span class="bold">Workroom</span></h5>				   		
 				</div>	
	    </div>
	    <div class="twelve columns">
	      <div class="title">
				<h5> <span class="bold">Messages</span></h5>	
		  </div>
	    </div>
	    
	   <div class="four columns add-bottom">
	       
	       <aside class="sidebar" style="min-height: 600px;">
	
<!--  				<div class="title"> -->
<!-- 					   <h5><span class="bold">Workroom</span></h5>				   		 -->
<!--  				</div>			 -->
				<article class="widget latest_posts">
				
					<ul id="categoryList">
					
					<!-- <li style="margin-bottom: 10px;"> <a id="3" href="javascript:addCategory('3');"><span class="bold">Proposals Messages</span></a> </li> -->
					<li style="margin-bottom: 10px;"> <a href="${pageContext.request.contextPath}/inbox/views" ><span class="bold">Proposals Messages</span></a> </li>
					
					<li style="margin-bottom: 10px;"> <a id="1" href="javascript:addCategory('1');"><span class="bold">Workroom Messages</span></a> </li>
					
					<li style="margin-bottom: 10px;"> <a id="1" href="javascript:systemInboxBody();"><span class="bold">System Messages</span></a> </li>
						
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
	   
	   	   <!-- ///////////////////////////////////////// PROPOSAL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ -->

             <div class="proposal-card" style="margin-top: 0px;">
               <div class="proposal-card-top">
                <div class="proposal-card-image">
                <img alt="" src="${pageContext.request.contextPath}/files/image/freelancer/${proposal.aspirantFreelance.image.fileID}" style = "width:100%; height:100%;"/>
                 <%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/user-default.jpg" style = "width:100%; height:100%;"/> --%>
                </div>
                <div class="proposal-card-info">
                  <div class="proposal-card-name" style="width: 100px;">
                   <span class="bold">${proposal.aspirantFreelance.freelanceName}</span>
                  </div>
                  <div class="proposal-card-info-detail">
                     <div class="proposal-card-country italy-flag" title="italy">Italy</div>
                     <div class="proposal-card-separator">|</div>
                     <div id="subcategory" style="float: left;" title="Sub Category">${proposal.aspirantFreelance.category.name}</div>
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
                   <div class="proposal-card-date"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${proposal.proposalDate}"/></div>
                   <div class="proposal-card-text ">
                   ${proposal.description} 
                    </div>
                    <div class="proposal-card-skills">
                      Skills:         
                      <c:forEach var="skill" items="${proposal.aspirantFreelance.curriculum.cvSkills}" varStatus="status">
                          <c:choose>
                          <c:when test="${status.last}">
                          	<a href="">${skill.name}</a>
                          </c:when>
                          <c:otherwise>
                          	<a href="">${skill.name}</a>, 
                          </c:otherwise>
                          </c:choose>
                      </c:forEach>
                    </div>
                 </div>
               </div> 
               <div class="proposal-card-bottom" >
                 <!-- <div class="proposal-price" id="price-deliveryTime">$95000 - Estimated Delivery: 30 giorni</div> -->   
                 <div class="proposal-price" id="price-deliveryTime"></div>     
               </div>              
             </div>
<!-- ====================================================== FINE PROPOSAL ========================================================= -->
	   
	   
	   <!-- ==================================================== MESSAGES ========================================================= -->

       <div id="result">
      
       </div>
       
       <div id="messagesBody-conversation">
       </div>


	   
<!--               <div class="msgContent" style="margin-bottom: 25px;">
                 <div class="message-text-box" style="background-color: aliceblue;">
                   <div class="msgPanelContent container">
                      <textarea style="width: 677px; max-width: width: 680px; min-width: width: 680px;border: #9B9B9B 1px solid; z-index: -10;" rows="" cols="">
                      </textarea>
                      <div style="margin: 10px;">
                      </div>
                        <a href="javascript:addWatchList(3);" class="blue_gradient small"> Post message </a>
                   </div>
                 </div>
              </div>
              
              <table class="msgTable" id="msgTable">
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
                  <tr id="msgRowA1" class="msgRowDark" style="border-bottom: 1px dotted gray;">
                     <td id="msgRowUserA1" class="msgTableUser" colspan="2"> 
                       <div class="msgUserWidth" >
                          Marco R.
                       </div>
                     </td>
                     <td id="msgRowContentA1">
                       <div class="msgContentWidth">
                         <div>
                            <a  href="" > New question about your proposal for the project <span class="bold">project name</span></a>
                         </div>
                       </div>
                     </td>
                     <td id="msgRowTimestampA1" class="msgTableTimestamp" colspan="2">
                         <div class="msgdateWidth">
                            5:00 pm
                         </div>
                     </td>
                  </tr>
                  <tr id="msgRowA2" class="msgRowLight">
                    <td id="msgRowUserA1" class="msgTableUser" colspan="2"> 
                       <div class="msgUserWidth" >
                          Marco R.
                       </div>
                     </td>
                     <td id="msgRowContentA1">
                       <div class="msgContentWidth">
                         <div>
                            Mention css and tables in the 
                            same sentence and controversy is sure 
                            to follow. Web designers 
                            like myself have been telling you not to
                            use html tables for layouts and now here we 
                            have a way to create tables with css alone.
                         </div>
                       </div>
                     </td>
                     <td>
                     </td>
                  </tr>
                </tbody>
              </table> -->
              
              
              
         <!-- ============================================================= FINE MESSAGES ======================================================== -->     
              
               <nav id="pagination" class="pagination">
			</nav>
              
	   
	   </div>
	
	
	
	
	
	
	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- end container -->

</section>
<!-- end #main_content -->	

</body>
</html>