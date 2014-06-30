<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<footer>

	<div class="top_full_shadow"></div>
	<div class="container">
	<security:authorize access="!isAuthenticated()">
		<div class="sixteen columns add-bottom">
			<div class="one-third column alpha add-bottom">
				<h3 class="heading">
					<span class="bold">about</span> <span>us</span>
				</h3>
				<p class="remove-bottom">We are the biggest gay in the world!</p>
			</div>
			<!-- end one-third -->
			<div class="one-third column add-bottom">
				<h3 class="heading">
					<span class="bold">from</span> <span>the blog</span>
				</h3>
				<ul class="from_blog">
					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/content/footer/1.png"
						alt="" />
						<div class="title">
							<a href="#" class="dark_link">Lorem ipsum dolor sit amet
								impsum</a>
						</div>
						<div>
							<span>10 Jan 2013</span> <a href="#" class="dark_link">Admin</a>
						</div></li>


					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/content/footer/2.png"
						alt="" />
						<div class="title">
							<a href="#" class="dark_link">Lorem ipsum dolor sit amet
								impsum</a>
						</div>
						<div>
							<span>10 Jan 2013</span> <a href="#" class="dark_link">Jane
								Doe</a>
						</div></li>
				</ul>
				<!-- end ul .from_blog -->

			</div>
			<!-- end one-third -->

			<div class="one-third column omega add-bottom">

				<h3 class="heading">
					<span class="bold">contact</span> <span>us from</span>
				</h3>

				<ul class="contact_us">

					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/location.png"
						alt="Location" /> <span>200 Mainstreet Blvd. <br />
							(East) 10th Floor, Tower Doe, <br /> Miami 42525
					</span></li>
					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/Iphone-portrait.png"
						alt="Phone" /> <span>+(306) 222-3339</span></li>
					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/email.png"
						alt="Email" /> <a href="mailto:email@yourcompany.com"
						class="skin_link"> <span>email@yourcompany.com</span>
					</a></li>
					<li><img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/social/skype-dark.png"
						alt="Skype" /> <a href="#" class="skin_link"><span>http://skype.com/yourskypename</span>
					</a></li>

				</ul>
				<!-- end ul .contact_us -->

			</div>
			<!-- end one-third -->
		</div>
		</security:authorize>
		<!-- end sixteen -->
		<div class="sixteen columns add-bottom" id="footer_bottom">

			<span>&copy; Copyright 2013 <a class="dark_link" href="#">Merlin</a>
				All rights reserved.
			</span>

			<ul class="bottom_links">
				<li><a class="dark_link" href="faq.html">FAQ's</a></li>
				<li><a class="dark_link" href="sitemap.html">Sitemap</a></li>
				<li><a class="dark_link" href="contact.html">Contact</a></li>
				<li class="top"><a class="skin_link" href="#scrollTop">
						&uarr; Top</a></li>
			</ul>
			<!-- end ul .bottom_links -->

		</div>
		<!-- end sixteen -->
	</div>
	<!-- end container -->
	<div class="bottom_full_shadow"></div>

</footer>