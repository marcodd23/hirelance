<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="portfolio" value="${user.freelanceProfile.portfolio}"/>
<section id="main_content">
	<div class="top_title_holder add-bottom">
		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3>Portfolio</h3>
				<p>Show the freelancer's portfolio</p>
			</div>
			<!-- end nine -->
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li><a href="${pageContext.request.contextPath}/profiles/freelancer/views?userID=${user.userID}">Profile</a></li>
					<li>&#187;</li>
					<li>Portfolio</li>
				</ul>
			</div>
			<!-- end seven -->
		</div>
		<!-- end container -->
	</div>
	<!-- end .top_title_holder -->
	
	<div class="container">
		<div class="sixteen columns add-top">
			<div class="gallery_style_item four_columns last">
				<div class="overlayLink">
					<a href="gallery-project.html"> <img src="images/content/portofolio/gallery-style/4-small.jpg" alt="">  </a>
				</div><!-- end .overlayLink -->
			</div>			
		</div><!-- end sixteen -->				
		<div class="sixteen columns">
			<nav class="pagination"> 
				 <ul>
					<li><a class="previous" href="#">« Previous</a></li>
					<li><a href="#">1</a></li>
					<li><a class="current" href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a class="next" href="#">Next »</a></li>	  
				</ul>
			</nav><!-- end .pagination -->
		</div><!-- end sixteen -->					
	</div>
</section>