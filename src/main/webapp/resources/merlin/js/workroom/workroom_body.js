$(document).ready(function(){
	$('#page').val('1');
	workResume();
});	

/*$(document).ready(function(){
	workResume();
});*/


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
	
	messageFormBody();
}

function setPage(page){
	$('#page').val(page);
	messageFormBody();
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


function workResume(){
	$('#bodyTitle').html("Job Summary");
	var dataForm = $("#filterDataForm").serialize();
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#result").html(loadImage);
	$.ajax({
		url:contextPath+'/workroom/views/jobresume',
		type:'POST',
		dataType:'json',
		data:dataForm,
		success:function(data){
			//$("#LoadingImage").hide();
			var html = "";
			var proposal=data.items;
			//alert(proposal.length);
          
			var jobStartDate = formatterDate(proposal[0].jobStartDate);
			var proposalDate = formatterDate(proposal[0].proposalDate);
			var deliveryTime;
            switch(proposal[0].deliveryTime){
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
            if(proposal[0].aspirantFreelance.image == null){
				imagePath=contextPath+'/resources/images/avatar_default.png';
			}
			else{
				imagePath=contextPath+"/files/image/freelancer/"+proposal[0].aspirantFreelance.image.fileID;
			}
			
            var complete ="";
            
            if(isOwner=='true'){
            	complete+='<a href="javascript:openForm(\'client\',\''+proposal[0].refProject.projectID+'\');">COMPLETE</a>';
            }
            
			html+= '<div class="project-card-workroom">'+
			          '<div id="project-Details">'+
		                '<div class="five columns">'+
	                  '<ul class="projectDetail" id="jobDetailLeft">'+
	                    '<li class="icon-calendar" style="padding: 0px;" title="Job Started on: ' + jobStartDate + '>'+
	                       '<span style="display: inline-block;">Job Started on: ' + jobStartDate + '</span>'+
	                    '</li>'+
	                    '<li class="icon-flow-tree" style="padding: 0px;" title="Category of job: ' + proposal[0].refProject.projectSubCategory.parentCategory + '&gt' + proposal[0].refProject.projectSubCategory + '">'+
	                       '<span style="display: inline-block;">Category: ' + proposal[0].refProject.projectSubCategory.parentCategory.name + '&gt' + proposal[0].refProject.projectSubCategory.name + '</span>'+
	                    '</li>'+
	                    '<li class="icon-globe" style="padding: 0px;" title="Location: ' + proposal[0].refProject.country + '">'+
	                       '<span style="display: inline-block;">Location: ' + proposal[0].refProject.country + '</span>'+
	                    '</li>'+
	                    '<li class="atomicJobDetail bid-icon-spr spr-budget bid-icon-spr-bg" title="Price: $' + proposal[0].payBid + '">'+
	                       '<span style="display: inline-block;">Price: $' + proposal[0].payBid + '</span>'+
	                    '</li>'+
	                  '</ul>'+
	                '</div>'+
	                '<div class="five columns" style="float: right;">'+
	                    '<!--  PARTE DESTRA -->'+
	   		        '<div class="bidContainer" id="bidContainer2">'+
			        '<div class="title" style="margin-bottom: 0px;">'+
					     '<h4> <span class="bold" >Status:</span></h4>'+	
			        '</div>'+
				      '<h6 class="grey">' + proposal[0].status+ '</h6>'+ complete +'<br><br></div></div></div>'+ 
			          '<div class="project-card-middle">'+
		                 '<div style="margin-left: 3px;  float: inherit; margin-top: 7px;">'+
		                   '<div class="project-card-title" style=" float: inherit;">'+
		                     '<span class="bold">Description</span>'+
		                   '</div>'+
		                   '<div style="margin-top: 8px; margin-right: 0px; margin-bottom: 18px;"></div>'+
		                   '<div class="project-card-text">' + proposal[0].refProject.description + '</div>'+
		                     '<div class="project-card-skills">'+
		                      'Skills:'+ 
		                      '<a href="/r/contractors/q-Android/">Android</a>,'+
		                      '<a href="/r/contractors/q-HTML5/">HTML5</a>,'+ 
		                      '<a href="/r/contractors/q-iOS/">iOS</a>,'+ 
		                      '<a href="/r/contractors/q-iPhone/">iPhone</a>'+
		                    '</div>'+
		                 '</div>'+
		               '</div>'+ 
		             '</div>'+
		             '<!-- \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\  -->'+
		             '<div class="title" style="margin-bottom: 0px;">'+
						'<h4> <span class="bold" >Selected Proposal:</span></h4>'+	
				     '</div>'+
				     '<!-- ///////////////////////////////////////// PROPOSAL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ -->'+
				     '<div class="proposal-card" style="margin-top: 0px;">'+
		               '<div class="proposal-card-top">'+
		                '<div class="proposal-card-image" style="width: 10%;">'+
		                '<img alt="" src="'+imagePath+'" style = "width:auto; height:65px;" />'+
		                '</div>'+
		                '<div class="proposal-card-info" style="width: 70%;">'+
		                  '<div class="proposal-card-name">'+
		                   '<span class="bold">'+proposal[0].aspirantFreelance.freelanceName+'</span>'+
		                  '</div>'+
		                  '<div class="proposal-card-info-detail">'+
		                     '<div class="proposal-card-country italy-flag" title="italy">Italy</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div id="subcategory" style="float: left;" title="Sub Category">'+proposal[0].aspirantFreelance.category.name+'</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div id="jobhistory" style="float: left;" title="job History">3 jobs</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div class="eol-rating-stars-small" style="float: left;" title="Average Job Rating">'+  
		                       '<div class="jStar" style="height: 20px; background-image: url('+contextPath+'/resources/jquery/plugin/star-rating/icons/small.png); background-position: initial initial; background-repeat: repeat no-repeat;">'+
		                       '</div>'+                
		                     '</div>'+
		                     '<div class="proposal-card-separator">|</div>'+
		                     '<div style="float: left;" id="portfolio">'+
		                       '<div class="proposal-card-icon proposal-icon-portfolio" style="float: left;"></div>'+
		                       '<div class="proposal-portfolio-link" style="float: left;">Portfolio</div>'+
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
		                   '<div class="proposal-card-text ">'+proposal[0].description+'</div>'+
		                    '<div class="proposal-card-skills">'+
		                      'Skills:';
		                      
                         
		                      
     					for ( var j = 0; j < proposal[0].aspirantFreelance.curriculum.cvSkills.length; j++) {
  						if(j<=(proposal[0].aspirantFreelance.curriculum.cvSkills.length)-2){
  						  html+="<a> "+proposal[0].aspirantFreelance.curriculum.cvSkills[j].name+"</a>,";	
  						}else{
  						  html+="<a> "+proposal[0].aspirantFreelance.curriculum.cvSkills[j].name+"</a>";	
  						}
  					}
                         
                         
		           html+= '</div>'+
		                '</div>'+
		               '</div>'+
		               '<div class="proposal-card-bottom" >'+
		                 '<div class="proposal-price">$'+proposal[0].payBid+' - Estimated Delivery: '+deliveryTime+'</div>'+
		                          
		               '</div>'+              
		             '</div>';
		

		    $('#messagesBody').html("");
			$('#result').html(html);
			
		  }
	});
}

function messageFormBody(){
	
	$('#bodyTitle').html("Messages");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();
    html+='<div class="msgContent" style="margin-bottom: 25px;">'+
             '<div class="message-text-box" style="background-color: aliceblue;">'+
                '<div class="msgPanelContent container">'+
                  '<form id="messageForm" action="' + messageAction + '" method="post">'+
                   '<input type="hidden" name="userID" value="' + userLoggedID + '"/>'+ 
                   //'<input type="hidden" name="projectID" value="' + projectID + '"/>'+ 
                   '<input type="hidden" name="proposalID" value="' + proposalID + '"/>'+ 
                   '<textarea name="messageText" style="width: 677px; max-width: width: 680px; min-width: width: 680px;border: #9B9B9B 1px solid; z-index: -10;" rows="" cols="">'+
                   '</textarea>'+
                   '<div style="margin: 10px;">'+
                   '</div>'+
                      '<button type="submit" class="standard_button small">Send Message</button>'+
                   '</div>'+
                  '</form>'+
                '</div>'+
             '</div>';
    
    $('#result').html(html);
    
    htmlMessagesBody+='<table class="msgTable" id="msgTable">'+
                        '<thead id="msgTableHead">'+
                           '<tr class="msgRowHead">'+
		                      '<th id="firstcol" width="4px"></th>'+
		                      '<th id="firsttextcol" width="15%">Sender</th>'+
		                      '<th id="secondtextcol" width="75%">Message</th>'+
		                      '<th id="thirdtextcol" width="15px">Date/Time</th>'+
		                      '<th id="lastcol" width="5px"></th>'+
                          '</tr>'+
                     '</thead>'+
                     '<tbody id="messages">'+
                     '</tbody>'+
                   '</table>';
    
    $('#messagesBody').html(htmlMessagesBody);
   
    messagesPaginated(dataForm);
    

    $('#messageForm').ajaxForm(function(data) { 
    	if(data==true){
    		alert('Messaggio Inviato');
    	}else{
    		alert('Errore Invio');
    	}
        messageFormBody();
    }); 
 
}


function messagesPaginated(dataForm){
	//alert(dataForm);
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);
	
	$.ajax({
		url:contextPath+'/workroom/messages/findAllWorkroomMessages',
		type:'POST',
		dataType:'json',
		data:dataForm,
		success:function(data){
			$("#LoadingImage").hide();
			var html = "";
			var messages=data.items;
			//alert(messages.length);
			
			if(messages.length==0){
		html+='<tr>'+
				'<td colspan="5">'+
				      '<div class="notification error">'+
				        '<img src="'+contextPath+'/resources/merlin/images/icons/error.png" alt="">'+
				        '<p>There are not messages </p></div></div>'+
				      '</div>'+
				  '</td>'+
				'</tr>';
			}else{

				for(var i = 0; i < messages.length; i++){
					
					var messageDate = formatterDate(messages[i].messageDate);
					
					if(messages[i].senderType == 'FREELANCE'){
						var senderName = messages[i].sender.freelanceProfile.freelanceName;
					}else{
						var senderName = messages[i].sender.clientProfile.clientName;
					}		
				
			   html+='<tr id="msgRowA1" class="msgRowDark" style="border-bottom: 1px dotted gray;">'+
	                   '<td id="msgRowUserA1" class="msgTableUser" colspan="2">'+
	                     '<div class="msgUserWidth" >'+
	                         senderName +
	                     '</div>'+
	                   '</td>'+
	                   '<td id="msgRowContentA1">'+
	                     '<div class="msgContentWidth">'+
	                       '<div>'+
	                         messages[i].text + 
	                       '</div>'+
	                     '</div>'+
	                   '</td>'+
	                   '<td id="msgRowTimestampA1" class="msgTableTimestamp" colspan="2">'+
	                       '<div class="msgdateWidth">'+
	                           messageDate+
	                       '</div>'+
	                   '</td>'+
	                '</tr>';
	                
	            
					
				}
			}
			
			$('#messages').html(html);
			
			var pagerHtml=pager(data.totalItems);
			$('#pagination').html(pagerHtml);
			var pageCurrent = $('#page').val();
			$('#page'+pageCurrent+' a').addClass('current');

		  },
		  
			complete: function() {
				  refreshNumberOfNewMessages();
			    }
	});
}



