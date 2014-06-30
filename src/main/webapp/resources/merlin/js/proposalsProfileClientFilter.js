$(document).ready(function(){
	$('#page').val('1');
	filter();
});	

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
	//alert(pages);
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

function filter(){
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#jobexperience").html(loadImage);
    
	$.ajax({
    	url:contextPath+"/profiles/client/findAllProposalFiltered",
    	type:"POST",
    	dataType:"json",
    	data:$("#filterDataForm").serialize(),
    	success:function(data){
    		var html="";
    		var proposals=data.items;
    		for(var i=0;i < proposals.length;i++){
    			html+='<div class="sixteen columns jobhistory">'+
        		'<div class="experience">'+
    				'<div class="jobname">'+
      					'<span>'+proposals[i].refProject.title+'</span>'+
    				'</div>'+
    				'</div>'+
    				'<div class="three columns" style="margin-left: 0px;">'+
     				'<div style="margin: 0px 0px 5px 5px;">'+
       					'<span>'+my_rating+'</span>'+
     				'</div>'+
     				'<div class="rating" data-average="'+proposals[i].evaluations[1].grade+'"></div>'+
    				'</div>'+ 
    				'<div class="twelve columns">Freelancer: <strong>'+proposals[i].aspirantFreelance.freelanceName+'</strong></div>'+
    				'<div class="thirteen columns jobdescription" style="float: right;">'+proposals[i].refProject.description+'</div>'+
    				'<div class="thirteen columns feedback_initial" style="float: right;">'+
        			'<span class="bold"> Freelance -> client </span>'+
        			'<div class="feedback">'+proposals[i].evaluations[1].remark+'</div>'+
        		'</div>'+
    				'<div class="thirteen columns feedback_response" style="float: right;">'+
    				'<span class="bold"> Client -> freelance</span>'+
     				'<div class="feedback">'+proposals[i].evaluations[0].remark+'</div>'+
    				'</div>'+
    			'</div>';
    		}   
    	   	$("#jobexperience").html(html); 
    	   	var pagerHtml=pager(data.totalItems);
			$('#pagination').html(pagerHtml);
			var pageCurrent = $('#page').val();
			$('#page'+pageCurrent+' a').addClass('current');
			$('#noresults').html(result+": "+data.totalItems+" "+elements+" in "+pagesCounter(data.totalItems)+" "+page_message);
    	   	$(".rating").jRating({
  			  isDisabled : true,
  			  rateMax:5,
  			  length:5,
  			  decimalLength:1
    	   	});
    	}
    });
}