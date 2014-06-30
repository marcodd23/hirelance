
function removeProjectFromWatchList(projectID){
	$.ajax({
		url:contextPath+"/profiles/freelancer/removeProjectToWatchList",
		type:"GET",
		data:{projectID:projectID},
		success:function(){
			$("#watchlist_dialog").dialog("open");
			filter();
		}
	});
	
}

$(document).ready(function(){
	$('#page').val('1');
	$('#result').html('<div class="twelve columns add-bottom bottom-bordered"><p>Seleziona una categoria a sinistra per filtrare i progetti</p></div>');
	//$('#result').html('<div class="twelve columns add-bottom"><table class="table_v1 add-bottom"><tbody><tr><td>#</td><td>First Name</td><td>Last Name</td><td>Username</td></tr><tr><td>1</td><td>John</td><td>Doe</td><td>john12</td></tr><tr><td>2</td><td>Jane</td><td>Doe</td><td>jahn12</td></tr><tr><td>3</td><td>Julia</td><td>Doe</td><td>julia22</td></tr></tbody></table></div>');
	$('#name,#date,#budget').click(function( event ) {
		  event.preventDefault();
		});
	//filter();
});		

function addSearch(){
	var visibleSearch=document.getElementById("visibleSearch");
	var search=document.getElementById("search");
	search.value=visibleSearch.value;
	$('#page').val('1');
	filter();
}
function addStatus(val, profile){
	$('#status').val(val);
	$('#profile').val(profile);
	$('#categoryList li a').css("font-weight", "normal");
	if($("#status").val()!='')el=document.getElementById(val).style.fontWeight="bold";
	filter();
}



function addOrder(item){
	
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
	var dirSort = document.getElementById('dirSort');
	var itemSort = document.getElementById('itemSort');
	if(itemSort.value=='name'){
		$('#name').css("font-weight","bold");
		$('#date').css("font-weight","normal");
		$('#budget').css("font-weight","normal");
		$('#date i').removeClass("icon-up-dir");
		$('#date i').addClass("icon-down-dir");
		$('#budget i').removeClass("icon-up-dir");
		$('#budget i').addClass("icon-down-dir");
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
		if(itemSort.value=='date'){
			$('#name').css("font-weight","normal");
			$('#date').css("font-weight","bold");
			$('#budget').css("font-weight","normal");
			$('#name i').removeClass("icon-up-dir");
			$('#name i').addClass("icon-down-dir");
			$('#budget i').removeClass("icon-up-dir");
			$('#budget i').addClass("icon-down-dir");
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
			$('#name').css("font-weight","normal");
			$('#date').css("font-weight","normal");
			$('#budget').css("font-weight","bold");
			$('#name i').removeClass("icon-up-dir");
			$('#name i').addClass("icon-down-dir");
			$('#date i').removeClass("icon-up-dir");
			$('#date i').addClass("icon-down-dir");
			if(dirSort.value=='asc'){
				$('#budget i').removeClass("icon-up-dir");
				$('#budget i').addClass("icon-down-dir");
				
			}
			else{
				$('#budget i').removeClass("icon-down-dir");
				$('#budget i').addClass("icon-up-dir");
				
			}
		}
	}
	filter();
}
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
	var dataForm = $("#filterDataForm").serialize();
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#result").html(loadImage);
	var profile=$('#profile').val();
	var url;
	if(profile == 'client'){
		url = '/projects/findAllClientProjectsFiltered';
	}
	else{
		url = '/projects/findAllFreelanceProjectsFiltered';
	}
	$.ajax({
		url:contextPath+url,
		type:'POST',
		dataType:'json',
		data:dataForm,
		success:function(data){
			//$("#LoadingImage").hide();
			var html = "";
			var projects=data.items;
			if(projects.length==0||projects[0]==null){
				html+='<div class="twelve columns">' +
				'<div class="notification error">'+
				'<img src="'+contextPath+'/resources/merlin/images/icons/error.png" alt="">'+
				'<p>'+notfound+'</p></div></div>';
				data.totalItems=0;
			}
			else{
				html+='<div class="twelve columns"><table id="project_table" class="project_details"><thead><tr>'+
				'<td><strong>'+name_table+'</strong></td>'+
				'<td><strong>'+category+'</strong></td>'+
				'<td><strong>Budget</strong></td>'+
				'<td><strong>Action<strogn></td></tr></thead><tbody>';
				for(var i = 0; i < projects.length; i++){
					html+='<tr><td>'+projects[i].title+'</td>'+
					'<td>'+projects[i].projectSubCategory.parentCategory.name+' > '+projects[i].projectSubCategory.name+'</td>'+
					'<td>'+projects[i].budgetMIN+'-'+projects[i].budgetMAX+'</td>'+
					'<td><a href="'+contextPath+'/projects/views?projectID='+projects[i].projectID+'" title="'+info+'"><i class="icon-info"></i></a>';
					var status=$('#status').val();
					if(profile=='client'){
						if(status=='HIRING_OPEN'){
							html+='<span title="'+proposals+'">('+projects[i].totalProposal+')</span>';
							html+='<a href="'+contextPath+'/projects/new_proposal?projectID='+projects[i].projectID+'" title="'+remove+'"><i class="icon-cancel"></i></a>';							
						}
						if(status=='HIRING_CLOSED'){
							html+='<a href="javascript:openForm(\'client\',\''+projects[i].projectID+'\');" title="'+do_complete+'"><i class="icon-thumbs-up"></i></a>';							
							html+='<a href="'+contextPath+'/workroom/views?projectID='+projects[i].projectID+'" title="'+view_workroom+'"><i class="icon-cog"></i></a>';
						}
						if(status=='EXPIRED'){
							html+='<a href="'+contextPath+'/projects/update?projectID='+projects[i].projectID+'" title="'+reply+'"><i class="icon-reply"></i></a>';
						}
					}
					else{
						if(status=='watch'){
							html+='<a href="'+contextPath+'/projects/new_proposal?projectID='+projects[i].projectID+'" title="'+proposal_add+'"><i class="icon-ticket"></i></a>';
							html+='<a href="javascript:removeProjectFromWatchList(\''+projects[i].projectID+'\');" title="'+watchlist_remove+'"><i class="icon-cancel"></i></a>';
						}
						if(status=='NOT_HIRED'){
							html+='<a href="'+contextPath+'/profiles/freelancer/removeProjectToWatchList?projectID='+projects[i].projectID+'" title="'+proposal_remove+'"><i class="icon-cancel"></i></a>';
						}
						if(status=='WORKING'){
							html+='<a href="'+contextPath+'/workroom/views?projectID='+projects[i].projectID+'" title="'+view_workroom+'"><i class="icon-cog"></i></a>';
						}
						if(status=='COMPLETED'){
							if(!projects[i].valuated)
								html+='<a href="javascript:openForm(\'freelancer\',\''+projects[i].projectID+'\');" title="'+rate_this+'"><i class="icon-star-empty"></i></a>';								
						}
					}
					'</td></tr>';
					/*html+='<div class="twelve columns add-bottom bottom-bordered" style="padding-bottom:10px;">'+
					'<div class="freelancers-item-grid profile-img" style="margin-left:0px;">'+
					'<div>'+ 
					'<h5> <a href="'+contextPath+'/projects/views?projectID='+projects[i].projectID+'">'+projects[i].title+'</a> </h5>'+	
					'<span>'+projects[i].projectSubCategory.parentCategory.name+' > '+projects[i].projectSubCategory.name+'&nbsp;&nbsp;&nbsp;</span>'+
					'<div><span>Budget '+projects[i].budgetMIN+' - '+projects[i].budgetMAX+'</span></div>'+
					'</div><!-- end .blog_list_item_description -->'+
					'</div>'+
					'<div class="freelancers-item-grid profile-img">'+
					'<ul class="tagList">';
					for ( var j = 0; j < projects[i].skills.length; j++) {
						html+="<li>"+projects[i].skills[j].name+"</li>";
					}
					html+='</ul>'+
					'</div>'+
					'<div class="freelancers-item-grid profile-img"><p><a href="#"><i class="icon-cancel"></i></a>&nbsp;'+
					'<a href="#"><i class="icon-ticket"></i></a></p></div>'+
					'</div>';*/					
				}
				html+='</tbody></table></div>';
			}
			$(".sidebar").css('min-height', function(){
				return $('#itemsForPage').val()*120;
			});
			$('#result').html(html);
			var pagerHtml=pager(data.totalItems);
			$('#pagination').html(pagerHtml);
			var pageCurrent = $('#page').val();
			$('#page'+pageCurrent+' a').addClass('current');
			$('#counter').html(result+": "+data.totalItems+" "+elements+" in "+pagesCounter(data.totalItems)+" "+page_message);
			$("#projectsNumber").val(data.totalItems);
			$('#name,#date,#budget').unbind();  
		}
	});
}