<script>
$("#ui-script").remove();
$("#ui-css").remove();
$("#ui-theme-css").remove();
$("#support").addClass("current");

</script>

<section id="main_content">
	<div class="top_title_holder add-bottom">
		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>
		<div class="container">
			<div class="nine columns">
				<h3>Support</h3>
				<p>Find the answers to your questions</p>
			</div>
			<div class="seven columns">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>Support</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end .top_title_holder -->
	<div class="container">
		<div class="four columns add-top add-bottom">
			<aside class="sidebar">
				<article class="widget search">
					<form>
						<input onblur="if(this.value=='') this.value='Search...'"
							onfocus="if(this.value=='Search...') this.value=''" type="text"
							value="Search...">
					</form>
				</article>
				<!-- end .search -->
				<article class="widget latest_posts">
					<div class="widget_title">
						<h2> <span class="bold">Latest Posts</span></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
					</div>
					<ul>
						<li><a href="#">Impsum dolor sit amet</a></li>
						<li><a href="#"> Voluptatem accusantium </a></li>
						<li><a href="#"> Quia voluptas sit aspe </a></li>
						<li><a href="#"> Nemo enim ipsam </a></li>
					</ul>
				</article>
				<!-- end .latest_posts -->
				<article class="widget">
					<div class="widget_title">
						<h2> <span class="bold">Tag Cloud</span></h2>
						<div class="title_sep_container">
							<div class="title_sep"></div>
						</div>
						<!-- end .title_sep_container -->
					</div>
					<ul class="tagList">
						<li><a href="#">Web</a></li>
						<li><a href="#">Programming</a></li>
						<li><a href="#">Design</a></li>
						<li><a href="#">Theme</a></li>
						<li><a href="#">Business</a></li>
						<li><a href="#">Sport</a></li>
						<li><a href="#">Theme Forest</a></li>
					</ul>
				</article>
			</aside>
		</div>
		<!-- end four -->
		<div class="twelve columns add-top add-bottom">
			<div class="title">
				<h2> <span class="bold">Faq</span></h2>
				<div class="title_sep_container">
					<div class="title_sep"></div>
				</div>
			</div>
			<div id="accordion">
				<h3>First</h3>
				<div>Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.
					Lorem ipsum dolor.</div>
				<h3>Second</h3>
				<div>Phasellus mattis tincidunt nibh.</div>
				<h3>Third</h3>
				<div>Nam dui erat, auctor a, dignissim quis.</div>
			</div>
		</div>
		<!-- end twelve -->
	</div>
	<!-- end container -->
</section>