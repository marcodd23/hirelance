<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="main_content">
<div class="top_title_holder add-bottom">
				
				<div class="top_title_border"></div>
				<div class="bottom_title_border"></div>
				
				<div class="container">
					
					<div class="nine columns">
					<c:choose>
						<c:when test="${not empty errorMessage}">
							<h3>Error ${errorNumber}</h3>
							<p>${errorMessage}</p>
						</c:when>
						<c:otherwise>
							<h3>Error 403</h3>
							<p>Access Denied</p>
						</c:otherwise>
					</c:choose>					
					</div><!-- end nine -->
					
					<div class="seven columns">
					
											
					</div><!-- end seven -->
				
				</div><!-- end container -->
			
			</div>
<div class="container">

	<div class="sixteen columns add-bottom">
<div class="one-third column alpha add-top add-bottom"></div>
		<div class="one-third column add-top add-bottom">

			<div class="holder_404">

				<div class="num_holder">
					<c:choose>
						<c:when test="${not empty errorMessage}">
							<p class="number">${errorNumber}</p>
							<p class="explanation">${errorMessage}</p>
						</c:when>
						<c:otherwise>
							<p class="number">403</p>
							<p class="explanation">Access Denied</p>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- end .num_holder -->


			</div>
			<!-- end .holder_404 -->

		</div>
		<!-- end .one-third -->
<div class="one-third column omega add-top add-bottom"></div>
</div>
	<!-- end sixteen -->

</div>
<!-- end container -->
</section>


		<!-- 
		<div class="one-third column add-top add-bottom">

			<div class="widget_title">

				<h2>
					<span class="bold">Recommended Links:</span>
				</h2>

				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
				end .title_sep_container

			</div>
			end .title

			<ul class="cart_list small_list">

				<li><a href="index.html">Home</a></li>
				<li><a href="sitemap.html">Sitemap</a></li>
				<li><a href="contact.html">Contact</a></li>
				<li><a href="blog-large-post.html">Blog</a></li>

			</ul>
			end ul.cart_list


		</div>
		end .one-third




		<div class="one-third column omega add-top add-bottom">

			<div class="widget_title">

				<h2>
					<span class="bold">Reasons 404:</span>
				</h2>

				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
				end .title_sep_container

			</div>
			end .title


			<span>Terrible Spelling 46% </span>
			<div id="spelling" class="animate_progress small_progress red"></div>

			<span>Page Does't Exist 24%</span>
			<div id="page_existing"
				class="animate_progress small_progress yellow"></div>

			<span>Page Has Moved 22%</span>
			<div id="page_moved"
				class="animate_progress small_progress green_teal"></div>

			<span>Other Reasons 8%</span>
			<div id="other" class="animate_progress small_progress green">
			</div>

		</div>
		end .one-third

 -->
	
