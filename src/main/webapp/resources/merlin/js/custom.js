
/* ------------------------------------------------------------------------
FUNCTION FOR CALLING BXSLIDER (WideSlider)
* ------------------------------------------------------------------------- */
$(function(){
            
	$(".wide_slider").bxSlider({
			auto: true,
		  easing: 'easeInOutExpo',
		  speed: 1000,
		  pause:4500
		  
	
	});
			
});



/* ------------------------------------------------------------------------
FUNCTION FOR CALLING BXSLIDER (Main slider)
* ------------------------------------------------------------------------- */
$(function(){
            
	$(".bxSlider_main").bxSlider({
			auto: true,
		  easing: 'easeInOutExpo',
		  speed: 1000,
		  pause:4500
		  
	
	});
			
});


	
/* ------------------------------------------------------------------------
	SUPERFISH MENU 
* ------------------------------------------------------------------------- */

	$('ul.sf-menu').superfish({
				delay: 10,
				speed: 'fast',
				animation:{height:'show'}
				
	});
		

	
/* ------------------------------------------------------------------------
	SCROLL TOP
* ------------------------------------------------------------------------- */	
	
	$(document).ready(function() {

		$('a[href=#scrollTop]').click(function(){

			$('html, body').animate({scrollTop:0}, 800,"easeInOutExpo");

			return false;

		});

	});
	

/* ------------------------------------------------------------------------
	SCROLL TO THE COMMENT SECTION
* ------------------------------------------------------------------------- */	
	
	function scrollToComments(){
	
		$("html, body").animate({scrollTop: $('#comment_section').offset().top }, 1000, 'easeInOutExpo' );
		
	
	};
	
	



/* ------------------------------------------------------------------------
	TESTIMONIALS SLIDER 
* ------------------------------------------------------------------------- */	

	$(function(){
				
		$(".testimonials_slider").bxSlider({
			  auto: true,
			  easing: 'easeInOutExpo',
			  speed: 1000,
			  pause:3800,
			  pager:false,
			  controls : false
			});	
				
	});




	


/* ------------------------------------------------------------------------
	ANIMATION FOR PROGRESSBAR
* ------------------------------------------------------------------------- */	
	
	 setTimeout(function(){

		$('.animate_progress').each(function() {
			
			var me = $(this);
			var child = $(me).children('div');
			var percent=$(me).progressbar("value");
			var current_percent = 0;
			

			var progress = setInterval(function() {
				if (current_percent>=percent) {
					clearInterval(progress);
				} else {
					current_percent +=1;
					
					child.css('width', (current_percent)+'%');
				}
				
				

			}, 10);

		});

	},100);
	

	


/* ------------------------------------------------------------------------
	ACCORDION, TABS and PROGRESSBAR
* ------------------------------------------------------------------------- */	
	
	$(function() {
		  
		$( "#accordion" ).accordion();
		$( "#tabs" ).tabs();
		
		//progress bars	
		$("#seo").progressbar({ value: 90 });
		$("#jquery").progressbar({ value: 70 });
		$("#wordpress").progressbar({ value: 85 });
		$("#htmlcss").progressbar({ value: 80 });
		$("#photoshop").progressbar({ value: 75 });
		
		//progress bars second example 
		$("#seo2").progressbar({ value: 90 });
		$("#jquery2").progressbar({ value: 70 });
		$("#wordpress2").progressbar({ value: 85 });
		$("#htmlcss2").progressbar({ value: 80 });
		$("#photoshop2").progressbar({ value: 75 });
		
		//progress bars for 404 error
		$("#spelling").progressbar({ value: 46 });
		$("#page_existing").progressbar({ value: 24 });
		$("#page_moved").progressbar({ value: 22 });
		$("#other").progressbar({ value: 8 });
			
			
	});
	
	
/* ------------------------------------------------------------------------
	FUNCTION FOR RESPONSIVE DROPDOWN LIST
* ------------------------------------------------------------------------- */

	$(function(){
   
   
	   //inject dropdown list to nav #mainNavigation
	   $("<select/>").appendTo("#mainNavigation");
	   
	   
	   //Create default option Go to...
	   $("<option />", {
	   "selected": "selected",
	   "value"   : "",
	   "text"    : "Go to...",
	   }).appendTo("#mainNavigation select");
	   
	   
	   //Populate dropdowns with the first menu items
	   $("#mainNavigation a").each(function(){
		
			var elem = $(this);
			$("<option />",{
			"value"   :   elem.attr("href"),
			"text"    :   elem.text()
			}).appendTo("#mainNavigation select");
	   
		});
		
		
		//make responsive dropdown to navigate
		$("#mainNavigation select").change(function(){
		window.location = $(this).find("option:selected").val();
		
		});
	   
   
	});



/* ------------------------------------------------------------------------
	TIPSY PLUGIN (toolTip) 
* ------------------------------------------------------------------------- */
	
	$('.showTipsy').tipsy({gravity: 's'});		
	
	


  
 	
	
	

/* ------------------------------------------------------------------------
	RECENT PROJECTS HOVER PANELS
* ------------------------------------------------------------------------- */	
	$(document).ready(function(){
			
			
			$('.hover').hover(function(){
				$(this).addClass('flip');
			},function(){
				$(this).removeClass('flip');
			});
			
			// set up click/tap panels
			$('.click').toggle(function(){
				$(this).addClass('flip');
			},function(){
				$(this).removeClass('flip');
			});
			
			// set up block configuration
			$('.block .action').click(function(){
				$('.block').addClass('flip');
			});
			$('.block .edit-submit').click(function(e){
				$('.block').removeClass('flip');
				
				
				$('#list-title').text($('#form_title').val());
				$('#list-items').empty();
				for (var num = 1; num <= $('#form_items').val(); num++) {
					$('#list-items').append('<li>Name '+num+'</li>');
				}
				e.preventDefault();
			});
			
			// set up contact form link
			$('.contact .action').click(function(e){
				$('.contact').addClass('flip');
				e.preventDefault();
			});
			$('.contact .edit-submit').click(function(e){
				$('.contact').removeClass('flip');
				// just for effect we'll update the content
				e.preventDefault();
			});
			
		});
	
	

/* ------------------------------------------------------------------------
FUNCTION FOR CALLING TWITTER PLUGIN (FOOTER)
* ------------------------------------------------------------------------- */
	/*jQuery(function($){
          $(".tweet").tweet({
                modpath: 'twitter/index.php',
				username: "envatowebdesign",
				count: 1,
				loading_text: "Loading tweets...",
          });
      });  
*/
	
/* ------------------------------------------------------------------------
FUNCTION FOR CALLING TWITTER PLUGIN FOR TWITTER WIDGETS
* ------------------------------------------------------------------------- */  
	/*
jQuery(function($){
          $(".tweetWidget").tweet({
                modpath: 'twitter/index.php',
				username: "envatowebdesign",
				count: 2,
				loading_text: "Loading tweets...",
          });
      });  	
	*/

/* ------------------------------------------------------------------------
	GALLERY POST
* ------------------------------------------------------------------------- */	

	$(function(){
            
	$(".gallery_slider").bxSlider({
		  auto:false,
		  easing: 'easeInOutExpo',
		  speed: 1000
		  
	});
			
});




/* ------------------------------------------------------------------------
	SLIDESHOW PROJECT
* ------------------------------------------------------------------------- */	

	$(function(){
            
	$(".slideshow_project").bxSlider({
		  auto:true,
		  easing: 'easeInOutExpo',
		  speed: 1000,
		  pause:4500
		  
	});
			
});





/* ------------------------------------------------------------------------
	PRETTY PHOTO
* ------------------------------------------------------------------------- */ 
  $("a[rel^='prettyPhoto']").prettyPhoto();
  
  

  
/* ------------------------------------------------------------------------
	PORTOFOLIO
* ------------------------------------------------------------------------- */  

$(function(){

	// Clone portfolio items to get a second collection for Quicksand plugin
	
	var $portfolioClone = $(".portfolio").clone();
	
	// Attempt to call Quicksand on every click event handler
	$(".filter a").click(function(e){
		
		$(".filter li").removeClass("current");	
		
		// Get the class attribute value of the clicked link
		var $filterClass = $(this).parent().attr("class");

		if ( $filterClass == "all" ) {
			var $filteredPortfolio = $portfolioClone.find("li");
		} else {
			var $filteredPortfolio = $portfolioClone.find("li[data-type~=" + $filterClass + "]");
		}
		
		// Call quicksand
		$(".portfolio").quicksand( $filteredPortfolio, { 
			duration: 800, 
			easing: 'linear' 
		}, function(){
			
						
			$(".portfolio a[rel^='prettyPhoto']").prettyPhoto();
				
			
		});


		$(this).parent().addClass("current");

		// Prevent the browser jump to the link anchor
		e.preventDefault();
	})
});

 	  
 
/* ------------------------------------------------------------------------
	SCROLL TO THE ANSWER AND ANIMATE SELECTED ANSWER
* ------------------------------------------------------------------------- */	
	
	function scrollToAnswer(id){
		
		$("html, body").animate({scrollTop: $('#'+id).offset().top -50 }, 1000, 'easeInOutExpo',function(){
		
			$('#'+id).animate({backgroundColor:"#fafa92"},500,function(){
			
				$('#'+id).animate({backgroundColor:"#fff"});
			
			});
			
		});
		
	};
	
	

 
/* ------------------------------------------------------------------------
	FUNCTION FOR CALLING FRACTION SLIDER
* ------------------------------------------------------------------------- */	
$(window).load(function(){
	$('.fs_slider').fractionSlider({
		'fullWidth': 			false,
		'controls': 			true, 
		'pager': 				false
	});

});	
  



 
/* ------------------------------------------------------------------------
CLOSE FUNCTION (Notifications)
* ------------------------------------------------------------------------- */
jQuery(document).ready(function(){

	jQuery('.close').closeThis({
		animation: 'fadeAndSlide', 	// set animation
		animationSpeed: 400 		// set animation speed
	});
	
});

(function($)
{
	$.fn.closeThis = function(options)
	{
		var defaults = {
			animation: 'slide',
			animationSpeed: 300
		};
		
		var options = $.extend({}, defaults, options);
		
		return this.each(function()
		{
			var message = $(this);
			
			message.css({cursor: 'pointer'});
			
			message.click(function()
			{
				hideMessage(message);
			});
			
			function hideMessage(object)
			{
				switch(options.animation)
				{
					case 'fade':
						fadeAnimation(object);
						break;
					case 'slide':
						slideAnimation(object);
						break;
					case 'size':
						sizeAnimation(object);
						break;
					case 'fadeThenSlide':
						fadeAndSlideAnimation(object);
						break;
					default:
						fadeAndSlideAnimation(object);
				}
			}
			
			function fadeAnimation(object)
			{
				object.fadeOut(options.animationSpeed);
			}
			
			function slideAnimation(object)
			{
				object.slideUp(options.animationSpeed);
			}
			
			function sizeAnimation(object)
			{
				object.hide(options.animationSpeed);
			}
			
			function fadeAndSlideAnimation(object)
			{
				object.fadeTo(options.animationSpeed, 0, function() { slideAnimation(message) } );
			}
			
		});
	}
})(jQuery);






  
/* ------------------------------------------------------------------------
	FUNCTION FOR CALLING FLICKR PLUGIN
* ------------------------------------------------------------------------- */	 
  $(function(){
 $('.flickrImages').flickrush({
    id: '44499772@N06',  // you just need to change the ID of your flickr username
    limit: 6,            // the number of photos to display
    random: true         // randomly select photos to be displayed
 });
});  





/* ------------------------------------------------------------------------
	TOGGLE SEARCH
* ------------------------------------------------------------------------- */	
$("#toggleSearch").click(function(){
		
			$("#searchv2").toggle(function(){
			
				$("#searchv2").animate({
				
				},1000,'linear');
			
			
			});
			
			
			$("#searchv2 input[type='text']").focus();
			
			
		});
  

