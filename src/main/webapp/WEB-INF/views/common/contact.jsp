<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" >
var contextPath='${pageContext.request.contextPath}';
$("#contact").addClass("current");
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/contact.js"></script>

<section id="main_content">
	<div class="top_title_holder add-bottom">
		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3>Contact</h3>
				<p>Get in touch with us</p>
			</div>
			<!-- end nine -->
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>Contact</li>
				</ul>
			</div>
			<!-- end seven -->
		</div>
		<!-- end container -->
	</div>
	<!-- end .top_title_holder -->
	<div class="container">
		<div class="twelve columns add-top  add-bottom">
			<div class="contact_map_holder add-bottom">
				<div class="iframe_holder">
					<iframe frameborder="0" scrolling="no" marginheight="0"
						marginwidth="0"
						src="https://maps.google.it/maps?q=Tozzanella,+TE&hl=it&ll=42.543295,13.631759&spn=0.009296,0.018153&sll=42.288594,13.900749&sspn=1.194692,2.323608&oq=tozza&t=h&hnear=Tozzanella,+Teramo,+Abruzzo&z=16&iwloc=A&amp;output=embed"></iframe>
				</div>
			</div>
			<!-- end .contact_map_holder -->
			<div id=result class="notification" style="display:none;">		
				<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/success.png" alt="">
				<p></p>
			</div>
			<div id="details" class="contact_form_holder add-top add-bottom">
				<form name="contactForm" id="contact_form" method="post" action="javascript:checkForm();">
					<security:authorize access="isAuthenticated()">
					<c:set var="name" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.name}"></c:set>
					<c:set var="surname" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.surname}"></c:set>
					<c:set var="email" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.email}"></c:set>
					
					<span id="error_name" style="display:none;color:red;clear:both;">The field can not be empty</span>
					<input type="text" name="name" id="name" placeholder="Name" disabled="disabled" value="${name} ${surname}" />
					
					<span id="error_email" style="display:none;color:red;clear:both;">The field can not be empty and must be like string@string.string</span>					
					<input type="text" name="email" id="email" placeholder="Email" disabled="disabled" value="${email}" /> 
					</security:authorize>
					
					<security:authorize access="!isAuthenticated()">
					
					<span id="error_name" style="display:none;color:red;clear:both;">The field can not be empty</span>
					<input type="text" name="name" id="name" placeholder="Name" />
					
					<span id="error_email" style="display:none;color:red;clear:both;">The field can not be empty and must be like string@string.string</span>					
					<input type="text" name="email" id="email" placeholder="Email" /> 
					</security:authorize>
					
					<span id="error_subject" style="display:none;color:red;clear:both;">The field can not be empty</span>
					<input type="text" name="subject" id="subject" placeholder="Subject" />
					
					<span id="error_message" style="display:none;color:red;clear:both;">The field can not be empty</span>
					<textarea name="message" id="message" placeholder="Message"></textarea>


					<input type="submit" class="standard_button medium" value="Send" />
					<!-- end .buttonHolder -->
				</form>
				<img id="load" src="${pageContext.request.contextPath}/resources/merlin/images/load1.gif" width="128px" height="128px" style="display:none;"/>
			</div>
			<!-- end .contact_form_holder -->
		</div>
		<!-- end twelve -->
		<div class="four columns add-top add-bottom">
			<aside class="sidebar">
				<article class="widget">
					<div class="widget_title">
						<h2><span class="bold"> Contact Details</span></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
						<!-- end .title_sep_container -->
					</div>
				<!-- end .title -->
					<div class="contact_details_holder">
						<div class="contact_details_content">
							<p>32 Rue De Saint Denis. 42525, Tozzanella (TE)</p>
							<p class="bold">
								phone: <span>+(306) 222-3339</span>
							</p>
							<p class="bold">
								email: <span>email@yourcompany.com</span>
							</p>
						</div>
						<!-- end .contact_details_content -->
					</div>
					<!-- end .contact_details_holder -->
				</article>
				<article class="widget">
					<div class="widget_title">
						<h2><span class="bold"> Business Hours</span></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
						<!-- end .title_sep_container -->
					</div>
					<!-- end .title -->
					<ul class="business_hours">
						<li><span class="bold">Monday:</span> <span class="time">8:00am
								- 6:00pm</span></li>
						<li><span class="bold">Tuesday:</span> <span class="time">8:00am
								- 6:00pm</span></li>
						<li><span class="bold">Wednesday:</span> <span class="time">8:00am
								- 6:00pm</span></li>
						<li><span class="bold">Thursday:</span> <span class="time">8:00am
								- 6:00pm</span></li>
						<li><span class="bold">Friday:</span> <span class="time">8:00am
								- 6:00pm</span></li>
						<li><span class="bold">Saturday:</span> <span class="time">CLOSED</span>
						</li>
						<li><span class="bold">Sunday:</span> <span class="time">CLOSED</span>
						</li>
					</ul>
					<!-- end ul.business_hours -->
				</article>
			</aside>
		</div>
		<!-- end four -->
	</div>
	<!-- end container -->
</section>
<!-- end #main_content -->
