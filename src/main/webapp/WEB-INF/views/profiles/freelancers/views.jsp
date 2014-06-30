<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script>
$("#account").addClass("current");
</script>
<div id="dialog" title="<spring:message code="common.generic_delete_title" />">
  	<p><span style="float: left; margin: 0 7px 20px 0;"></span><spring:message code="common.generic_delete_message" /></p>
</div>
<section id="main_content">
<c:set var="userLogged" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user}"></c:set>
<c:set var="isOwner" value="${user.userID == userLogged.userID}"></c:set>
<c:set var="curriculum" value="${user.freelanceProfile.curriculum }"></c:set>
<c:set var="profile" value="${user.freelanceProfile}"></c:set>
<c:choose>
	<c:when test="${profile.image eq null}" >
		<c:set var="profileImgUri" value="${pageContext.request.contextPath}/resources/images/avatar_default.png"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="profileImgUri" value="${pageContext.request.contextPath}/files/image/freelancer/${profile.image.fileID}"></c:set>
	</c:otherwise>
</c:choose>			

	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3><spring:message code="freelancer.title"/></h3>
				<p><spring:message code="freelancer.description"/> </p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}/account/views">Account</a></li>
					<li>&#187;</li>
					<li><spring:message code="freelancer.title"/></li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="four columns add-top add-bottom">
			<aside class="sidebar">
				<article class="widget">
					<div class="title">
						<h2> <span class="bold">Freelancer </span> <span><spring:message code="freelancer.image"/></span></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div><!-- end .title_sep_container -->
					</div><!-- end .title -->
					<div class="team_imgHolder">
						<a href="${profileImgUri}" rel="prettyPhoto"><img alt="" src="${profileImgUri}"/></a>
					</div>	
				</article>
				<article  class="widget">
					<div class="title">
						<h2> <span class="bold">Freelancer </span> <span>info</span><c:if test="${isOwner}"><a href="${pageContext.request.contextPath}/profiles/freelancer/update?userID=${user.userID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a></c:if></h2>					
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
					</div>
					<table class="project_details">
						<tbody>
						<tr><td><strong>Alias: </strong></td><td>${profile.freelanceName}</td></tr>
						<tr><td><strong><spring:message code="common.category"/>: </strong></td><td>${profile.category.name}</td></tr>				
						</tbody>
					</table>
				</article>
				<article  class="widget">
					<div class="title">
						<h2> <span class="bold">Skills</span><c:if test="${isOwner}"><a href="${pageContext.request.contextPath}/curriculum/skills/views?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a></c:if></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
					</div>
					<c:choose>
						<c:when test="${empty curriculum.cvSkills && !isOwner}">
							<div class="notification info" style="cursor: pointer;">
								<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
								<p><spring:message code="freelancer.empty"/></p>
							</div>	
						</c:when>
						<c:otherwise>
						<ul class="tagList">
						<c:forEach items="${curriculum.cvSkills}" var="skill">
							<li><a href="#">${skill.name}</a></li>
						</c:forEach>
						</ul>
						</c:otherwise>	
					</c:choose>
				</article>	
				<article class="widget">
					<div class="title">
						<h2> <span class="bold">Client</span> <span>Testimonials</span> </h2>
							<div class="title_sep_container">
								<div class="title_sep"></div>
							</div>
					</div>
					<div class="bx-wrapper" style="width:220px; position:relative;">
						<div class="bx-window" style="position:relative; overflow:hidden; width:220px;">
							<ul class="testimonialList testimonials_slider" style="width: 999999px; position: relative; left: -220px;">
								<c:forEach var="feedback" items="${feedbacks}">
								<c:choose>
									<c:when test="${feedback.jobEvaluated.refProject.clientOwner.image eq null}" >
										<c:set var="clientImgUri" value="${pageContext.request.contextPath}/resources/images/avatar_default.png"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="clientImgUri" value="${pageContext.request.contextPath}/files/image/client/${feedback.jobEvaluated.refProject.clientOwner.image.fileID}"></c:set>
									</c:otherwise>
								</c:choose>	
								<li class="pager" style="width: 220px; float: left; list-style: none;">   
									<div class="testimonialHolder">
										<img src="${clientImgUri}" alt=""> 
										<blockquote>" ${feedback.remark} "</blockquote>
									</div>		
									<div class="arrow-down"></div>
									<div class="testimonialAuthor"> <span class="bold">${feedback.jobEvaluated.refProject.clientOwner.clientName},</span> <span class="testimonial_title">${feedback.jobEvaluated.refProject.clientOwner.companyName}</span> </div>
								</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</article>		
			</aside>
		</div>
		<div class="twelve columns add-top">
			<div class="title">
				<h2> <span class="bold">Curriculum </span><span>vitae</span></h2>					
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
			</div>
			<script type="text/javascript">
			var ex;
			function showAboutMe(){
				a="<form class='form-user' method='POST' action='${pageContext.request.contextPath}/curriculum/aboutme/create'>"+
				"<textarea name='aboutMe'>";
				b="</textarea>"+
				"<input type='hidden' name='curriculumID' value='${profile.curriculum.curriculumID}'/>"+
				"<input type='hidden' name='userID' value='${requestScope.user.userID}'/>"+
				"<button class='standard_button medium' style='margin-left:90px;' type='submit'><i class='icon-check'></i></button> "+
				"<button class='standard_button medium' onclick='javascript:hideAboutMe();'><i class='icon-cancel'></i></button>"+
				"</form>";
				ex=document.getElementById("aboutme").innerHTML;
				if(document.getElementById('aboutme_full')==null){
					document.getElementById("aboutme").innerHTML=a+b;
				}
				else{
					var content=document.getElementById('aboutme_full').innerHTML;
					document.getElementById("aboutme").innerHTML=a+content+b;
				}	
				
			}
			function hideAboutMe(){
				document.getElementById("aboutme").innerHTML=ex;
				
			}
			</script>
			<div id = "aboutme">
				<c:choose>
					<c:when test="${curriculum.aboutMe==null || curriculum.aboutMe=='' }">
						<c:choose>
						<c:when test="${!isOwner}">
						<blockquote class="bq" style="float:left;"><p><spring:message code="freelancer.about_empty"/></p></blockquote>
						</c:when>
						<c:otherwise>
						<blockquote class="bq" style="float:left;" title="<spring:message code='common.edit'/>" id="aboutme_empty"><a href="javascript:showAboutMe();"><spring:message code="freelancer.about" /> </a></blockquote>						
						</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
						<c:when test="${isOwner}">
						<blockquote class="bq" style="float:left;" title="<spring:message code='common.edit'/>" id="aboutme_full" onclick="javascript:showAboutMe();">"${requestScope.user.freelanceProfile.curriculum.aboutMe}"</blockquote>
						</c:when>
						<c:otherwise>
						<blockquote>${profile.curriculum.aboutMe}</blockquote>
						</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>		
		</div>
		<div class="twelve columns add-bottom">
				<div class="title">
					<h2> <span class="bold"><spring:message code="freelancer.education"/></span><c:if test="${empty curriculum.educations && isOwner}"><a href="${pageContext.request.contextPath}/curriculum/educations/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a></c:if></h2>					
					<div class="title_sep_container">
						<div class="title_sep"></div>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty curriculum.educations && isOwner}">
					</c:when>
					<c:when test="${empty curriculum.educations && !isOwner}">
					<div class="notification info" style="cursor: pointer;">
						<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
						<p><spring:message code="freelancer.empty"/></p>
					</div>
					</c:when>
					<c:otherwise>
					<table class="project_details">
					<thead>
					<tr>
							<td><strong><spring:message code="freelancer.date"/></strong> </td>
							<td><strong><spring:message code="freelancer.institute"/></strong> </td>
							<td><strong><spring:message code="freelancer.grade"/></strong> </td>
							<c:if test="${isOwner}">
							<td><strong><spring:message code="freelancer.action"/></strong> </td>
							</c:if>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${curriculum.educations}" var="education">
							<tr>
							<td><fmt:formatDate value="${education.graduationDate}" pattern="yyyy"/> </td>
							<td>${education.institute} </td>
							<td>${education.grade} </td>
							<c:if test="${isOwner}">
							<td><a href="${pageContext.request.contextPath}/curriculum/educations/update?educationID=${education.educationID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a>
							<a href="#"><span onclick="url='${pageContext.request.contextPath}/curriculum/educations/delete?educationID=${education.educationID}'; $( '#dialog' ).dialog( 'open' );"><i  title="<spring:message code='common.trash'/>" class="icon-trash"></i></span></a> </td>
							</c:if>
							</tr>
						</c:forEach>
					</tbody>
					</table>
					<c:if test="${isOwner}">
					<p><a href="${pageContext.request.contextPath}/curriculum/educations/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.add'/>" class="icon-plus-squared"></i></a></p>
					</c:if>
					</c:otherwise>	
				</c:choose>			
				<div class="title">
					<h2> <span class="bold"><spring:message code="freelancer.employment"/></span><c:if test="${empty curriculum.employments && isOwner}"><a href="${pageContext.request.contextPath}/curriculum/employments/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a></c:if></h2>					
					<div class="title_sep_container">
						<div class="title_sep"></div>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty curriculum.employments && isOwner}">
					</c:when>
					<c:when test="${empty curriculum.employments && !isOwner }">
						<div class="notification info" style="cursor: pointer;">
							<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
							<p><spring:message code="freelancer.empty"/></p>
						</div>
					</c:when>
					<c:otherwise>
					<table class="project_details">
					<thead>
					<tr>
							<td><strong><spring:message code="freelancer.start"/></strong> </td>
							<td><strong><spring:message code="freelancer.company"/></strong> </td>
							<td><strong><spring:message code="freelancer.employment.description"/></strong> </td>
							<td><strong><spring:message code="freelancer.end"/></strong> </td>
							<c:if test="${isOwner}">
							<td><strong><spring:message code="freelancer.action"/></strong> </td>
							</c:if>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${curriculum.employments}" var="employment">
							<tr>
							<td><fmt:formatDate value="${employment.startJob}" pattern="MM/yyyy"/> </td>							
							<td>${employment.company} </td>
							<td>${employment.description} </td>
							<td><fmt:formatDate value="${employment.endJob}" pattern="MM/yyyy"/> </td>
							<c:if test="${isOwner}">
							<td><a href="${pageContext.request.contextPath}/curriculum/employments/update?employmentID=${employment.employmentID}"><i  title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a>
							<a href="#"><span onclick="url='${pageContext.request.contextPath}/curriculum/employments/delete?employmentID=${employment.employmentID}'; $( '#dialog' ).dialog( 'open' );"><i title="<spring:message code='common.trash'/>" class="icon-trash"></i></span></a> </td>
							</c:if>
							</tr>
						</c:forEach>
					</tbody>
					</table>
					<c:if test="${isOwner}">
					<p><a href="${pageContext.request.contextPath}/curriculum/employments/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.add'/>" class="icon-plus-squared"></i></a></p>
					</c:if>
					</c:otherwise>	
				</c:choose>			
				<div class="title">
					<h2> <span class="bold"><spring:message code="freelancer.language"/></span><c:if test="${empty curriculum.languages && isOwner}"><a href="${pageContext.request.contextPath}/curriculum/languages/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a></c:if></h2>					
					<div class="title_sep_container">
						<div class="title_sep"></div>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty curriculum.languages && isOwner}">
					</c:when>
					<c:when test="${empty curriculum.languages && !isOwner}">
						<div class="notification info" style="cursor: pointer;">
							<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
							<p><spring:message code="freelancer.empty"/></p>
						</div>
					</c:when>
					<c:otherwise>
					<table class="project_details">
					<thead>
					<tr>
							<td><strong><spring:message code="freelancer.language.name"/></strong> </td>
							<td><strong><spring:message code="freelancer.reading"/></strong> </td>
							<td><strong><spring:message code="freelancer.speaking"/></strong> </td>
							<td><strong><spring:message code="freelancer.listening"/></strong> </td>
							<td><strong><spring:message code="freelancer.writing"/></strong> </td>
							<td><strong><spring:message code="freelancer.certification"/></strong> </td>
							<c:if test="${isOwner}">
							<td><strong><spring:message code="freelancer.action"/></strong> </td>
							</c:if>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${curriculum.languages}" var="language">
							<tr>
							<td>${language.name} </td>
							<td>${language.reading} </td>
							<td>${language.speaking} </td>
							<td>${language.listening} </td>
							<td>${language.writing} </td>
							<td>${language.certification} </td>	
							<c:if test="${isOwner}">						
							<td><a href="${pageContext.request.contextPath}/curriculum/languages/update?langID=${language.langID}"><i title="<spring:message code='common.edit'/>" class="icon-pencil"></i></a>
							<a href="#"><span onclick="url='${pageContext.request.contextPath}/curriculum/languages/delete?langID=${language.langID}'; $( '#dialog' ).dialog( 'open' );"><i title="<spring:message code='common.trash'/>" class="icon-trash"></i></span></a> </td>
							</c:if>
							</tr>
						</c:forEach>
					</tbody>
					</table>
					<c:if test="${isOwner}">
					<p><a href="${pageContext.request.contextPath}/curriculum/languages/create?curriculumID=${curriculum.curriculumID}"><i title="<spring:message code='common.add'/>" class="icon-plus-squared"></i></a></p>
					</c:if>
					</c:otherwise>	
				</c:choose>				
			<div class="title">
			<c:if test="${isOwner}">
				<c:choose>
					<c:when test="${empty profile.portfolio}">
						<c:set var="icon" value="icon-pencil"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="icon" value="icon-plus-squared"></c:set>						
					</c:otherwise>
				</c:choose>
			</c:if>
				<h2> <span class="bold">Portfolio</span><a title="<spring:message code='common.add'/>" href="${pageContext.request.contextPath}/portfolio/create?userID=${user.userID}"><i class="${icon}"></i></a></h2>
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty profile.portfolio && isOwner}">
				</c:when>
				<c:when test="${empty profile.portfolio && !isOwner}">
					<div class="notification info" style="cursor: pointer;">
						<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
						<p><spring:message code="freelancer.empty"/></p>
					</div>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${fn:length(profile.portfolio) gt 3}">
						<c:set var="visible" value="3"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="visible" value="${fn:length(profile.portfolio)}"></c:set>
					</c:otherwise>
					</c:choose>
					<ul class="rc_list">
						<c:forEach items="${profile.portfolio}" var="portfolioItem"  begin="0" end="${visible-1}">
							<c:choose>
								<c:when test="${portfolioItem.portfolioFile eq null}" >
									<c:set var="portfolioFilePath" value="${pageContext.request.contextPath}/resources/images/empty.png"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="portfolioFilePath" value="${pageContext.request.contextPath}/files/image/freelancer/${portfolioItem.portfolioFile.fileID}"></c:set>
								</c:otherwise>
							</c:choose>	
							<li class="first"> 
								<div class="hover panel personal"> 
									<div class="front">  
										<img src="${portfolioFilePath}" alt="">
										<span>${portfolioItem.title}</span>	
									</div><!-- end .front -->
									<div class="back">
										<div class="pad">
											<h2>${portfolioItem.title}</h2>
											<p>${portfolioItem.description}</p>
											<c:if test="${isOwner}">
											<a title="<spring:message code='common.edit'/>" href="#" class="portfolioItem_action"><span></span><i class="icon-pencil"></i></a>
											<a title="<spring:message code='common.trash'/>" href="#dialog" class="portfolioItem_action"><span onclick="url='${pageContext.request.contextPath}/portfolio/delete?portfolioItemID=${portfolioItem.portfolioItemID}'; $( '#dialog' ).dialog( 'open' );"><i class="icon-trash"></i></span></a>										
											</c:if>
											<a href="${portfolioFilePath}" rel="prettyPhoto" class="portfolioItem_action"><i title="<spring:message code='common.zoom'/>" class="icon-resize-full"></i></a>										
										</div><!-- end .pad -->
									</div><!-- end .back -->
								</div><!-- end .panel -->
							</li>
						</c:forEach>
						</ul>
						<ul id="list2" class="rc_list"></ul>
				</c:otherwise>
			</c:choose>
			<c:if test="${fn:length(profile.portfolio) gt 3}">
			<script type="text/javascript">
			var contextPath="${pageContext.request.contextPath}";
			var freelanceID="${profile.freelanceID}";
			var isOwner=${isOwner};
			var edit = "<spring:message code='common.edit'/>";
			var trash ="<spring:message code='common.trash'/>" ;
			var zoom="<spring:message code='common.zoom'/>";
			var moreText='<spring:message code="freelancer.portfolio.more" />';
			var lessText='<spring:message code="freelancer.portfolio.less" />';			
			</script>
     		<script src="${pageContext.request.contextPath}/resources/merlin/js/portfolio.js"></script>
			<img style="display:block;" id="loadImage" src="${pageContext.request.contextPath}/resources/merlin/images/load1.gif" width="128px" height="128px"/>
			<span id="more" onclick="more();" class="standard_button small" style="float:right;"><spring:message code="freelancer.portfolio.more" /> (${fn:length(profile.portfolio)}) &gt;&gt; </span>
		</c:if>		
		
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->
<script>
var url;
$( "#dialog" ).dialog({ 
	autoOpen: false,
	modal: true,
	//position: { my: "center top", at: "top top", of: window },
	buttons: {
		"<spring:message code='common.confirm'/>": function() {window.location.href=url;},
		"<spring:message code='common.cancel'/>": function() {$( this ).dialog( "close" );}
	}
});
</script>

