
$(document).ready(function(){
	$('#page').val('1');
	filter();
});		

function addSearch(){
	var visibleSearch=document.getElementById("visibleSearch");
	var search=document.getElementById("search");
	search.value=visibleSearch.value;
	$('#page').val('1');
	filter();
}

function addCategory(val){
	if($("#category").val()==val){
		$("#category").val('');
	}
	else{
		$("#category").val(val);
	}
	//$('#skill').val('');
	$('#page').val('1');
	$('#categoryList li a').css("font-weight", "normal");
	if($("#category").val()!='')el=document.getElementById(val).style.fontWeight="bold";
	filter();
}

function addSkill(val){
	if($("#skill").val()==val){
		$("#skill").val('');
	}
	else{
		$("#skill").val(val);
	}
	$('#page').val('1');
	$('#skillList li').css("background-color", "#ffffff");
	$('#skillList li').css("background-repeat", "no-repeat");
	$('#skillList li').css("background-image", "url("+contextPath+"/resources/merlin/images/icons/tag-10x10.png)");
	$('#skillList li').css("background-position", "5px center");
	if($("#skill").val()!='')
		el=document.getElementById(val).style.background="#f6f6f6 url("+contextPath+"/resources/merlin/images/icons/tag-10x10.png) no-repeat 5px center";
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
	$.ajax({
		url:contextPath+'/projects/findAllProjectsFiltered',
		type:'POST',
		dataType:'json',
		data:dataForm,
		success:function(data){
			//$("#LoadingImage").hide();
			var html = "";
			var projects=data.items;
			if(projects.length==0){
				html+='<div class="twelve columns">' +
				'<div class="notification error">'+
				'<img src="'+contextPath+'/resources/merlin/images/icons/error.png" alt="">'+
				'<p>'+notfound+'</p></div></div>';
			}
			else{
				for(var i = 0; i < projects.length; i++){
					if(projects[i].clientOwner.image == null){
						imagePath=contextPath+'/resources/images/avatar_default.png';
					}
					else{
						imagePath=contextPath+"/files/image/client/"+projects[i].clientOwner.image.fileID;
					}
					html+='<div class="twelve columns add-bottom bottom-bordered">'+
					'<div class="freelancerList_imgHolder"><img src="'+imagePath+'" title="'+projects[i].clientOwner.clientName+'" alt=""></div>'+
					'<div class="freelancers-item-grid profile-img">'+
					'<div>'+ 
					'<h5> <a href="'+contextPath+'/projects/views?projectID='+projects[i].projectID+'">'+projects[i].title+'</a> </h5>'+	
					'<span>'+projects[i].projectSubCategory.parentCategory.name+' > '+projects[i].projectSubCategory.name+'&nbsp;&nbsp;&nbsp;</span>'+
					'<div><span>Budget '+projects[i].budgetMIN+' - '+projects[i].budgetMAX+'</span></div>'+
					'</div><!-- end .blog_list_item_description -->'+
					'</div>'+
					'<div class="freelancers-item-grid">'+
					'<ul class="tagList">';
					for ( var j = 0; j < projects[i].skills.length; j++) {
						html+="<li>"+projects[i].skills[j].name+"</li>";
					}
					html+='</ul>'+
					'</div>'+
					'</div>';
				}
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
		  }
	});
}