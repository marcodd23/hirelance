$(document).ready(function(){
	$('#page').val('1');
	filter();
});	


//ordinamento nel caso si ordini di default solo per data
function addOrder(){
	var dirSort = document.getElementById('dirSort');
	if(dirSort.value=='asc'){
		$('#dirSort').val('desc');
	}else{
		$('#dirSort').val('asc');
	}
	
	var dirSort = document.getElementById('dirSort');
	$('#date').css("font-weight","bold");
	if(dirSort.value=='asc'){
		$('#date i').removeClass("icon-up-dir");
		$('#date i').addClass("icon-down-dir");
		
	}
	else{
		$('#date i').removeClass("icon-down-dir");
		$('#date i').addClass("icon-up-dir");
		
	}
	
	filter();
}



/*function addOrder(item){	
	$('#page').val('1');
	var itemSort = document.getElementById('itemSort');
	if(itemSort.value!=''){
		if(itemSort.value==item){
			var dirSort = document.getElementById('dirSort');
			if(dirSort.value=='' || dirSort.value=='desc'){
				$("#dirSort").val('asc');
			}
			else{
				$("#dirSort").val('desc');
			}
		}
		else{
			$("#itemSort").val(item);
			$("#dirSort").val('asc');
		}
	}
	else{
		$("#itemSort").val(item);
		$("#dirSort").val('asc');
	}
	
   // Parte per modificare i CSS dopo il click	
	var dirSort = document.getElementById('dirSort');
	var itemSort = document.getElementById('itemSort');
	if(itemSort.value=='date'){
		$('#date').css("font-weight","bold");
		if(dirSort.value=='asc'){
			$('#date i').removeClass("icon-up-dir");
			$('#date i').addClass("icon-down-dir");
			
		}
		else{
			$('#date i').removeClass("icon-down-dir");
			$('#date i').addClass("icon-up-dir");
			
		}
	}
	else{
		if(itemSort.value=='rating'){
			$('#rating').css("font-weight","bold");
			if(dirSort.value=='asc'){
				$('#name i').removeClass("icon-up-dir");
				$('#name i').addClass("icon-down-dir");
				
			}
			else{
				$('#name i').removeClass("icon-down-dir");
				$('#name i').addClass("icon-up-dir");
				
			}
		}
		else{
			$('#jobs').css("font-weight","bold");
			if(dirSort.value=='asc'){
				$('#name i').removeClass("icon-up-dir");
				$('#name i').addClass("icon-down-dir");
				
			}
			else{
				$('#name i').removeClass("icon-down-dir");
				$('#name i').addClass("icon-up-dir");
				
			}
		}
	}
	filter();
}
*/

function setPage(page){
	$('#page').val(page);
	filter();
}


function pagesCounter(itemsNumber){
	var pages;
	var itemsForPage=$('#itemsForPage').val();
	var mod = itemsNumber%itemsForPage;
	if(mod==0){
		pages=itemsNumber/itemsForPage;
	}
	else{
		pages=(itemsNumber-mod)/itemsForPage+1;
	}
	return pages;
}


function pager(itemsNumber){
	var pages = pagesCounter(itemsNumber);
	var page=$('#page').val();	
	var prev="";
	var next="";
	var html="<ul>";
	var pagerHtml="";
	if(pages==0){
		html+="";
	}
	else{
		if(pages==1){
			pagerHtml+='<li id="page1"><a href="javascript:setPage(1)">1</a></li>';
			html+=pagerHtml;
		}
		else{
			if(pages>5){
				if((parseInt(page)-2)>1){
					if(parseInt(page)+2<=parseInt(pages)){
						for(var i = parseInt(page)-2;i<=parseInt(page)+2;i++){
							pagerHtml+='<li id="page'+i+'"><a href="javascript:setPage('+i+')">'+i+'</a></li>';
						}
					}
					else{
						for(var i = pages-4;i<=pages;i++){
							pagerHtml+='<li id="page'+i+'"><a href="javascript:setPage('+i+')">'+i+'</a></li>';
						}
					}
				}
				else{
					for(var i = 1;i<=5;i++){
						pagerHtml+='<li id="page'+i+'"><a href="javascript:setPage('+i+')">'+i+'</a></li>';
					}
				}
			}
			else{
				for(var i = 1;i<=pages;i++){
					pagerHtml+='<li id="page'+i+'"><a href="javascript:setPage('+i+')">'+i+'</a></li>';
				}
			}
			var nextPage=1+parseInt(page);
			next='<li><a class="next" href="javascript:setPage('+ nextPage +')">Next >></a></li>';
			if(page==1){
				html+=pagerHtml+next;
			}
			else{
				var prevPage=parseInt(page)-1;
				prev='<li><a class="previous" href="javascript:setPage('+prevPage+')"><< Previous</a></li>';
				if(page==pages){
					html+=prev+pagerHtml;
				}
				else{
					html+=prev+pagerHtml+next;
				}
			}
		}
	}
	 return html+'</ul>';
}


/*function formatterDate(date){
	var d = new Date(date);
	var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
	var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
	var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
	var formattedTime = hours + ":" + minutes;
	formattedDate = formattedDate + " " + formattedTime;
	return formattedDate;
}*/

function filter(){
	var dataForm = $("#filterDataForm").serialize();
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#result").html(loadImage);
	$.ajax({
		url:contextPath+'/projects/proposals/findAllProposalsFiltered',
		type:'POST',
		dataType:'json',
		data:dataForm,
		success:function(data){
			//$("#LoadingImage").hide();
			var html = "";
			var proposals=data.items;
			//alert(proposals.length);
			if(proposals.length==0){ 
				html+='<div class="twelve columns">' +
				'<div class="notification error">'+
				'<img src="'+contextPath+'/resources/merlin/images/icons/error.png" alt="">'+
				'<p>There is not proposals </p></div></div>';
			}else{

				
				for(var i = 0; i < proposals.length; i++){
					
					var proposalDate = formatterDate(proposals[i].proposalDate);
					var deliveryTime;
                    switch(proposals[i].deliveryTime){
                       case null:
                 	       deliveryTime = "not specified";
                       break;
                       case 1:
                    	   deliveryTime = "1 day";
                       break; 
                       case 3:
                    	   deliveryTime = "3 days";
                       break;
                       case 7:
                    	   deliveryTime = "1 week";
                       break;
                       case 14:
                    	   deliveryTime = "2 weeks";
                       break;
                       case 21:
                    	   deliveryTime = "3 weeks";
                       break;
                       case 30:
                    	   deliveryTime = "1 month";
                       break;
                       case 60:
                    	   deliveryTime = "2 months";
                       break;
                       case 90:
                    	   deliveryTime = "3 months";
                       break;
                       case 180:
                    	   deliveryTime = "6 months";
                       break;
                       case -1:
                    	   deliveryTime = "more than 6 months";
                       break;
                    }
					
                    if(proposals[i].aspirantFreelance.image == null){
						imagePath=contextPath+'/resources/images/avatar_default.png';
					}
					else{
						imagePath=contextPath+"/files/image/freelancer/"+proposals[i].aspirantFreelance.image.fileID;
					}
					html+= '<div class="proposal-card">'+
		               '<div class="proposal-card-top">'+
		                '<div class="proposal-card-image">'+
		                '<img alt="" src="'+imagePath+'" style = "width:auto; height:65px;" />'+
		                '</div>'+
		                '<div class="proposal-card-info">'+
		                  '<div class="proposal-card-name">'+
		                   '<span class="bold">'+proposals[i].aspirantFreelance.freelanceName+'</span>'+
		                  '</div>'+
		                  '<div class="proposal-card-info-detail">'+
		                     '<div id="subcategory" style="float: left;" title="Sub Category">'+proposals[i].aspirantFreelance.category.name+'</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div id="jobhistory" style="float: left;" title="job History">'+proposals[i].aspirantFreelance.totalProjects+' jobs</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div class="eol-rating-stars-small" style="float: left;" title="Average Job Rating">'+  
		                       '<div class="rating" data-average="'+proposals[i].aspirantFreelance.rating+'"></div>'+                
		                     '</div>'+
		                  '</div>'+
		                '</div>'+
		               '</div>'+    
		               '<div class="proposal-card-detail">'+
		                 '<div class="proposal-card-left" style="margin-left: 3px;  float: inherit;">'+
		                   '<div class="proposal-card-title" style=" float: inherit;">'+
		                     '<span class="bold">Proposal</span>'+
		                   '</div>'+
		                   '<div class="proposal-card-date">'+proposalDate+'</div>'+
		                   '<div class="proposal-card-text ">'+proposals[i].description+'</div>'+
		                    '<div class="proposal-card-skills">'+
		                      'Skills:';
		                      
                           
		                      
       					for ( var j = 0; j < proposals[i].aspirantFreelance.curriculum.cvSkills.length; j++) {
    						if(j<=(proposals[i].aspirantFreelance.curriculum.cvSkills.length)-2){
    						  html+="<a> "+proposals[i].aspirantFreelance.curriculum.cvSkills[j].name+"</a>,";	
    						}else{
    						  html+="<a> "+proposals[i].aspirantFreelance.curriculum.cvSkills[j].name+"</a>";	
    						}
    					}
                           
                           
		           html+='</div>'+
		                '</div>'+
		               '</div>'+
		               '<div class="proposal-card-bottom" >'+
		                 '<div class="proposal-price">$'+proposals[i].payBid+' - Estimated Delivery: '+deliveryTime+'</div>';
		     
		        if(isClientOwner && !(proposals[i].refProject.status == "HIRING_CLOSED" || proposals[i].refProject.status == "EXPIRED")){       
		           html+='<div class="proect_details_btn_holder" style="float:right;">'+
						   '<a href="/hirelance/workroom/create?proposalID=' + proposals[i].proposalID + '" class="green_gradient small" > Hire this </a>'+
					     '</div>'+
		                 '<div class="proposal-message">'+
		                    '<a href="/hirelance/inbox/conversation?proposalID=' + proposals[i].proposalID + '" title="Send Message To This Freelance" >Contact Freelance</a>'+
		                 '</div>';
		           }
		           
		           html+='</div>'+              
		             '</div>';
				}
			}

			 
			//alert(proposals.length);
			$('#result').html(html);
			var pagerHtml=pager(data.totalItems);
			$('#pagination').html(pagerHtml);
			var pageCurrent = $('#page').val();
			$('#page'+pageCurrent+' a').addClass('current');
			if(data.totalItems==1){
				$('#counter').html(data.totalItems+" proposal in "+pagesCounter(data.totalItems)+" page");
			}else if(data.totalItems>1 && (pagesCounter(data.totalItems)==1)){
				$('#counter').html(data.totalItems+" proposals in "+pagesCounter(data.totalItems)+" page");
			}else if(data.totalItems>1 && (pagesCounter(data.totalItems)>1)){
				$('#counter').html(data.totalItems+" proposals in "+pagesCounter(data.totalItems)+" pages");
			}
			$(".rating").jRating({
				type:'small',
				  isDisabled : true,
				  rateMax:5,
				  length:5,
				  decimalLength:1
			});
			
		  }
	});
}
