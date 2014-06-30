$(document).ready(function(){
		$('#list2').hide();
		$('#loadImage').hide();
});	
function flip(){
	//alert('ciao');
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
}
function less(totItems){
	//alert("ne vedo di meno");
	$("#list2").hide("blind",1000);
	$("#more").html(moreText+" ("+totItems+") &gt;&gt;");
	$("#more").attr("onclick","displayMore("+totItems+")");
}
function displayMore(totItems){
	$("#list2").show("blind",1000);
	$("#more").html("&lt;&lt; "+lessText);
	$("#more").attr("onclick","less("+totItems+")");				
}
function more(){
	$.ajax({
		url:contextPath+"/portfolio/showMoreElements",
		type:"GET",
		data:"freelanceID="+freelanceID,
		dataType:"json",
		success:function(data){
			//var oldHtml=$(".rc_list").html();
			$("#loadImage").show();
			//$(".rc_list").html(loadImage);
			var newHtml='';
			imagePath='';
			for(var i=0;i<data.length;i++){
				if(data[i].portfolioFile==null){
					imagePath=contextPath+'/resources/images/empty.png';
				}
				else{
					imagePath=contextPath+'/files/image/freelancer/'+data[i].portfolioFile.fileID;
				}
				newHtml+= '<li class="first plus"><div class="hover panel personal"><div class="front">'
				+'<img src="'+imagePath+'" alt="">'
				+'<span>'+data[i].title+'</span></div><div class="back"><div class="pad">'
				+'<h2>'+data[i].title+'</h2><p>'+data[i].description+'</p>';
				if(isOwner){
					newHtml+='<a title="edit" href="#" style="background-color:white;"><i class="icon-pencil"></i></a> '
						+'<a title="'+trash+'" href="#dialog" style="background-color:white;">'
						+'<span onclick="url=\''+contextPath+'/portfolio/delete?portfolioItemID='+data[i].portfolioItemID+'\'; $( \'#dialog\' ).dialog( \'open\' );">'
						+'<i class="icon-trash"></i></span></a> ';
				}
				newHtml=newHtml+'<a href="'+imagePath+'" rel="prettyPhoto" class="portfolioItem_action"><i title="'+zoom+'" class="icon-resize-full"></i></a></div></div></div></li>';
			}
			var totItems=data.length+3;
			$("#more").html("&lt;&lt; "+lessText);
			$("#more").attr("onclick","less("+totItems+")");
			$("#loadImage").hide();
			$("#list2").html(newHtml);
			$("#list2").show("blind",1000);
			flip();
			$("a[rel^='prettyPhoto']").prettyPhoto();
			//$(".rc_list").html(oldHtml+newHtml);
			//alert(data[0].title);
		}
	});				
}


