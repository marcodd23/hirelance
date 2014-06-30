<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script>
$("#ui-script").remove();
$("#ui-css").remove();
$("#ui-theme-css").remove();
$("#home").addClass("current");
</script>
<section id="main_content">

	<div class="fs_slider_bg">
		<div class="container">
			<div class="sixteen columns">
				<div class="slider-wrapper">
					<div class="responisve-container">
						<div class="fs_slider">
							<div class="fs_loader"></div>
							<div class="slide">
								<img
									src="${pageContext.request.contextPath}/resources/merlin/images/content/slide1.png"
									data-position="25,50" data-in="right" data-delay="1000"
									data-out="right" alt="" />
								<p class="title_skin" data-position="50,450" data-in="top"
									data-out="top" data-ease-in="easeInOutBounce">HIRELANCE</p>
								<p class="title_white" data-position="110,450" data-in="bottom"
									data-delay="1100"><spring:message code="slider.1.1"/></p>
								<p class="title_white" data-position="160,450" data-in="bottom"
									data-delay="1200"><spring:message code="slider.1.2"/></p>
								<p class="title_white" data-position="210,450" data-in="bottom"
									data-delay="1300"><spring:message code="slider.1.3"/></p>


								<a href="${pageContext.request.contextPath}/users/create" class="gray_gradient medium" data-in="bottom"
									data-delay="1600" data-position="265,450"> <spring:message code="common.join"/> </a>
							</div>
							<!-- end .slide -->



							<div class="slide">

								<img
									src="${pageContext.request.contextPath}/resources/merlin/images/content/job.jpg"
									data-position="50,400" data-in="right" data-delay="100"
									data-out="right" alt="" />

								<p class="title_skin" data-position="40,0" data-in="top"
									data-out="top"><spring:message code="slider.2.1"/></p>

								<p class="teaser title_white" data-position="100,50"
									data-step="1" data-in="bottom" data-delay="500"><spring:message code="slider.2.2"/></p>
									
								<p class="teaser title_white" data-position="150,50"
									data-step="1" data-in="bottom" data-delay="1000"><spring:message code="slider.2.3"/></p>

								<p class="teaser title_white" data-position="200,50"
									data-step="1" data-in="bottom" data-delay="1400"><spring:message code="slider.2.4"/></p>

								<a href="${pageContext.request.contextPath}/projects/views_all" class="gray_gradient medium" data-in="right"
									data-step="1" data-delay="1600" data-position="255,50"> <spring:message code="common.search_job" /> </a>

							</div>
							<!-- end .slide -->




							<div class="slide">

								<img
									src="${pageContext.request.contextPath}/resources/merlin/images/content/hire.jpg"
									data-position="25,0" data-in="right" data-delay="1000"
									data-out="right" alt="" />


								<p class="title_large" data-position="70,530" data-in="top"
									data-out="top" data-ease-in="easeInOutBounce"><spring:message code="slider.3.1"/></p>


								<p class="title_white" data-position="120,530" data-in="bottom"
									data-delay="1100"><spring:message code="slider.3.2"/>
								</p>

								<p data-position="155,530" data-in="bottom" data-delay="1200"><spring:message code="slider.3.3"/></p>



								<a href="${pageContext.request.contextPath}/profiles/freelancer/views_all" class="gray_gradient medium" data-in="bottom"
									data-delay="1600" data-position="225,530"> <spring:message code="common.search_freelancer"/> </a>

							</div>
							<!-- end .slide -->



						</div>
						<!-- end .fs_slider -->

					</div>
					<!-- end .responisve-container -->

				</div>
				<!-- end .slider-wrapper -->

			</div>
			<!-- end sixteen -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .fs_slider_bg -->
<div class="full_promobox add-bottom">

		<div class="top_full_shadow"></div>
		<div class="bottom_full_shadow"></div>

		<div class="container">

			<div class="thirteen columns">
			<security:authorize access="!isAuthenticated()">
				<h4>
					<spring:message code="common.message"/>. <a class="a_personal"
						href="${pageContext.request.contextPath}/users/create"><spring:message code="common.join"/></a> <spring:message code="common.or"/> <a class="a_personal" href="${pageContext.request.contextPath}/login_start"><spring:message code="common.signin"/></a> <spring:message code="common.if"/>.
				</h4>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<h4><spring:message code="common.welcome"/> <span class="bold"><security:authentication property="name"/></span>. <spring:message code="common.logged"/>. <a class="a_personal" href="${pageContext.request.contextPath}/account/views"><spring:message code="common.go"/></a> <spring:message code="common.or"/> <a class="a_personal" href="${pageContext.request.contextPath}/j_spring_security_logout"><spring:message code="common.logout"/></a>.</h4>
			</security:authorize>
			</div>
			<!-- end thirteen -->
		</div>
		<!-- end container -->

	</div>
	<!-- end .full_promobox -->
	<div class="container">

		<div class="sixteen columns add-top">

			<div class="one-third column featured_v1 add-bottom alpha">

				<div class="imgHolder my_imgHolder">
					<img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/post.png"
						alt="" />
				</div>

				<div class="content">

					<h4 class="heading">
						<span><spring:message code="common.post"/></span>
					</h4>
					<p><spring:message code="common.post.description"/></p>
					<a href="${pageContext.request.contextPath}/projects/create"><spring:message code="common.go1"/> &#187;</a>

				</div>
				<!-- end content -->

			</div>
			<!-- end one-third -->


			<div class="one-third column featured_v1 add-bottom">

				<div class="imgHolder my_imgHolder">
					<img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/freelance.png"
						alt="" />
				</div>

				<div class="content">

					<h4 class="heading">
						<span><spring:message code="common.search_freelancer"/></span>
					</h4>
					<p><spring:message code="common.search_freelancer.description"/></p>
					<a href="${pageContext.request.contextPath}/profiles/freelancer/views_all"><spring:message code="common.go1"/> &#187;</a>

				</div>
				<!-- end content -->

			</div>
			<!-- end one-third -->


			<div class="one-third column featured_v1 add-bottom omega">

				<div class="imgHolder my_imgHolder">
					<img
						src="${pageContext.request.contextPath}/resources/merlin/images/icons/job.png"
						alt="" />
				</div>

				<div class="content">

					<h4 class="heading">
						<span><spring:message code="common.search_job"/></span>
					</h4>
					<p><spring:message code="common.search_job.description"/></p>
					<a href="${pageContext.request.contextPath}/projects/views_all"><spring:message code="common.go1"/> &#187;</a>

				</div>
				<!-- end content -->

			</div>
			<!-- end one-third -->


		</div>
		<!-- end sixteen -->

		<div class="sixteen columns add-top">

			<div class="title">

				<h2> <span class="bold">recent</span> <span>projects</span></h2>

				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
				<!-- end .title_sep_container -->

			</div>
			<!-- end .title -->

			<ul class="rc_list">
				<li class="first">
					<div class="hover panel">
						<div class="front">
							<img
								src="${pageContext.request.contextPath}/resources/merlin/images/content/cooperation-handshake.png"
								alt="" /> <span>Image Format</span>
						</div>
						<!-- end .front -->
						<div class="back">
							<div class="pad">
								<h2>Image Format</h2>
								<span class="date">10 July, 2013</span>
								<p>Lorem impsum dolor sit ametcos tetuer das adipiscing
									elit. Vesti il liguan porta lorta lorem. Praesent vestibulum
									moles tie lacus.</p>
								<a
									href="${pageContext.request.contextPath}/resources/merlin/image-project.html"
									class="standard_button small">Read More</a>
							</div>
							<!-- end .pad -->

						</div>
						<!-- end .back -->

					</div> <!-- end .panel -->

				</li>


				<li>

					<div class="hover panel">

						<div class="front">

							<img
								src="${pageContext.request.contextPath}/resources/merlin/images/content/touch-screen.png"
								alt="" /> <span>Slideshow Format</span>

						</div>
						<!-- end .front -->

						<div class="back">

							<div class="pad">
								<h2>Slideshow Format</h2>
								<span class="date">10 July, 2013</span>
								<p>Lorem impsum dolor sit ametcos tetuer das adipiscing
									elit. Vesti il liguan porta lorta lorem. Praesent vestibulum
									moles tie lacus.</p>
								<a href="slideshow-project.html" class="standard_button small">Read
									More</a>
							</div>
							<!-- end .pad -->

						</div>
						<!-- end .back -->

					</div> <!-- end .panel -->

				</li>


				<li>

					<div class="hover panel">

						<div class="front">

							<img
								src="${pageContext.request.contextPath}/resources/merlin/images/content/boat.png"
								alt="" /> <span>Gallery Format</span>

						</div>
						<!-- end .front -->

						<div class="back">

							<div class="pad">
								<h2>Gallery Format</h2>
								<span class="date">10 July, 2013</span>
								<p>Lorem impsum dolor sit ametcos tetuer das adipiscing
									elit. Vesti il liguan porta lorta lorem. Praesent vestibulum
									moles tie lacus.</p>
								<a href="gallery-project.html" class="standard_button small">Read
									More</a>
							</div>
							<!-- end .pad -->

						</div>
						<!-- end .back -->

					</div> <!-- end .panel -->

				</li>


				<li class="last">

					<div class="hover panel">

						<div class="front">

							<img
								src="${pageContext.request.contextPath}/resources/merlin/images/content/beauty-nature.png"
								alt="" /> <span>Video Format</span>

						</div>
						<!-- end .front -->

						<div class="back">

							<div class="pad">
								<h2>Video Format</h2>
								<span class="date">10 July, 2013</span>
								<p>Lorem impsum dolor sit ametcos tetuer das adipiscing
									elit. Vesti il liguan porta lorta lorem. Praesent vestibulum
									moles tie lacus.</p>
								<a href="video-project.html" class="standard_button small">Read
									More</a>
							</div>
							<!-- end .pad -->

						</div>
						<!-- end .back -->

					</div> <!-- end .panel -->
				</li>


			</ul>
			<!-- end ul.rc_list -->


		</div>
		<!-- end sixteen -->




		<div class="eight columns add-top add-bottom">

			<div class="title">

				<h2><span class="bold"> latest</span> <span>news</span></h2>

				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
				<!-- end .title_sep_container -->

			</div>
			<!-- end .title -->


			<div class="latest_blog_list_item">

				<div class="blog_list_item_date">
					<span class="day">24</span> <span class="month">mar</span>
				</div>
				<!-- end .blog_list_item_date -->

				<div class="blog_list_item_description">

					<h5>
						<a href="#"> Quoet Agabit Tristique</a>
					</h5>

					<span class="comments">10 Comments</span>

					<div class="blog_list_item_excerpt">
						Praesent augue arcu, ornare ut tincidunt eu, mattis a libero.
						Pellentesque habitant morbi tristique senectus et netus et
						malesuada fames... <a href="#">Read More &#187;</a>
					</div>
					<!-- end .blog_list_item_excerpt -->

				</div>
				<!-- end .blog_list_item_description -->

			</div>
			<!-- end .latest_blog_list_item -->


			<div class="latest_blog_list_item remove-bottom">

				<div class="blog_list_item_date">
					<span class="day">24</span> <span class="month">mar</span>
				</div>
				<!-- end .blog_list_item_date -->

				<div class="blog_list_item_description">

					<h5>
						<a href="#"> Malesuada Mattis Libes</a>
					</h5>

					<span class="comments">15 Comments</span>

					<div class="blog_list_item_excerpt">
						Praesent augue arcu, ornare ut tincidunt eu, mattis a libero.
						Pellentesque habitant morbi tristique senectus et netus et
						malesuada fames... <a href="#">Read More &#187;</a>
					</div>
					<!-- end .blog_list_item_excerpt -->

				</div>
				<!-- end .blog_list_item_description -->

			</div>
			<!-- end .latest_blog_list_item -->


		</div>
		<!-- end eight -->



		<div class="eight columns add-bottom add-top">

			<div class="title">

				<h2> <span class="bold">Our</span> <span>skills</span></h2>

				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
				<!-- end .title_sep_container -->

			</div>
			<!-- end .title -->

			<div id="seo" class="animate_progress">
				<span>SEO 60% </span>
			</div>
			<div id="jquery" class="animate_progress">
				<span>jQuery 70%</span>
			</div>
			<div id="wordpress" class="animate_progress">
				<span>Wordpress 85%</span>
			</div>
			<div id="htmlcss" class="animate_progress">
				<span>HTML5 / CSS3 80%</span>
			</div>
			<div id="photoshop" class="animate_progress">
				<span>Photoshop 75%</span>
			</div>



		</div>
		<!-- end eight -->
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->
