$(document).ready(function(){
	$('#page').val('1');
	deliveryTimeFormatter();
	proposalMessageFormBody();
	//$('#page').val('1');
	//workResume();
	
	//inboxConversationBody();
});	

   function deliveryTimeFormatter(){
	         var html = "";
             var dtFormatted;
                    switch(proposalDeliveryTime){
                       case null:
                    	   dtFormatted = "not specified";
                       break;
                       case 1:
                    	   dtFormatted = "1 day";
                       break; 
                       case 3:
                    	   dtFormatted = "3 days";
                       break;
                       case 7:
                    	   dtFormatted = "1 week";
                       break;
                       case 14:
                    	   dtFormatted = "2 weeks";
                       break;
                       case 21:
                    	   dtFormatted = "3 weeks";
                       break;
                       case 30:
                    	   dtFormatted = "1 month";
                       break;
                       case 60:
                    	   dtFormatted = "2 months";
                       break;
                       case 90:
                    	   dtFormatted = "3 months";
                       break;
                       case 180:
                    	   dtFormatted = "6 months";
                       break;
                       case -1:
                    	   dtFormatted = "more than 6 months";
                       break;
                    } 

                    html+='<div class="proposal-price">$'+proposalPayBid+' - Estimated Delivery: '+dtFormatted+'</div>';
                    
                    $('#price-deliveryTime').html(html);
   }          


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
	proposalMessageFormBody();
	//messageFormBody();
}




function setPage(page){
	$('#page').val(page);
	proposalMessageFormBody();
	//messageFormBody();
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






function proposalMessageFormBody(){

	$('#bodyTitle').html("Proposal COnversation");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();
    html+='<div class="msgContent" style="margin-bottom: 25px;">'+
             '<div class="message-text-box" style="background-color: aliceblue;">'+
                '<div class="msgPanelContent container">'+
                  '<form id="messageForm" action="' + messageAction + '" method="post">'+
                   '<input type="hidden" name="userID" value="' + userLogged + '"/>'+ 
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
    
    $('#messagesBody-conversation').html(htmlMessagesBody);
 
    //Chiamo la routine per oaginare tutti i messaggi:
    proposalMessagesPaginated(dataForm);
    
//Routine per inviare il messaggio usando il plugin ajaxform:
    $('#messageForm').ajaxForm(function(data) { 
    	if(data==true){
    		alert('Messaggio Inviato');
    	}else{
    		alert('Errore Invio');
    	}
    	proposalMessageFormBody();
    }); 
 
}



//Routine per paginare tutti i messaggi:
function proposalMessagesPaginated(dataForm){
	//alert(dataForm);
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);
	
	$.ajax({
		url:contextPath+'/inbox/message/allInboxMessages',
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


/*function inboxConversationBody(){
	alert(proposal.proposalDate);
	$('#bodyTitle').html("Proposals Conversations");
	var htmlMessagesBody = "";
	var proposalHtml = "";
	var dataForm = $("#filterDataForm").serialize();
	alert(dataform);

    
    //$('#proposalBody').html(proposalHtml);
     
   // proposalsInboxList(dataForm);
 
 
}
*/

/*function proposalsInboxList(dataForm){
	
	//alert(contextPath);
	var loadImage='<img src="'+contextPath+'/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);
	
	$.ajax({
		url:contextPath+'/inbox/message/allProposalsConversationInbox',
		type:'POST',
        dataType:'json',
        data: dataForm,
		success:function(data){
			$("#LoadingImage").hide();
			var html = "";
			var proposals=data.items;
			//alert(messages.length);
			
			//alert(proposals.length);
			if(proposals.length==0){
	      html+='<tr>'+
				  '<td colspan="5">'+
				      '<div class="notification error">'+
				        '<img src="'+contextPath+'/resources/merlin/images/icons/error.png" alt="">'+
				        '<p>There are not conversations </p></div></div>'+
				      '</div>'+
				  '</td>'+
				'</tr>';
			}else{

                for(var i = 0; i < proposals.length; i++){
							
                	var projectClient = proposals[i].refProject.clientOwner.clientID;
                	var proposalFreelance = proposals[i].aspirantFreelance.freelanceID;
				    var title = "";
				    var subTitle = "";
				    var newMessages = "";
				    
				    if(loggedClientID == projectClient){
				    	title+='<div class="proposal-card-name">'+
                        '<span class="bold">Your job: <a href="">' + proposals[i].refProject.title + '</a></span>'+
                        '</div>';
				    	
				    	subTitle+='<div id="subcategory" style="float: left;" title="Sub Category">Freelance Name: ' +
                        '<a href="">' + proposals[i].aspirantFreelance.freelanceName + '</a></div>'+
                        '<div class="proposal-card-separator">|</div>'+   
                        '<div style="float: left;" id="portfolio">';
				    	
				    }else if(loggedFreelanceID == proposalFreelance){
				    	title+='<div class="proposal-card-name">'+
	                       '<span class="bold">Proposal for the Job: <a href="">' + proposals[i].refProject.title + '</a></span>'+
	                       '</div>';
				    	
				    	subTitle+='<div id="subcategory" style="float: left;" title="Sub Category">Question about your proposal</div>'+
                        '<div class="proposal-card-separator">|</div>'+   
                        '<div style="float: left;" id="portfolio">';
				    }
				    
				    if(proposals[i].newMessageCounter != 0){
				    	newMessages+='<div class="proposal-card-separator">|</div>'+    
                        '<div id="newProposalMessages" style="float: left; color:red;" title="New Proposal Messages"><span class="bold">(' + proposals[i].newMessageCounter + ') new Messages</span></div>';
				    }
                	
                	
                	html+='<div class="proposal-card" style="margin-top: 0px; padding-bottom: 0px; padding-top: 0px;">'+
                    '<div class="proposal-card-top" style="padding-bottom: 0px; padding-top: 0px;">'+  
                     '<div class="proposal-card-info">'+
                       title +
                       '<div class="proposal-card-info-detail">'+
                             subTitle + 
                          '<div  class="icon-mail">'+
                               '<a href="' + contextPath + '/inbox/conversation?proposalID=' + proposals[i].proposalID + '" style="color: green;"> View Conversation </a></div>'+
                          '</div>'+
                               newMessages+
                          '</div>'+
                     '</div>'+
                    '</div>'+
                  '</div>';
					
				}
                
			}
			
			
			
			$('#messages').html(html);
			
			var pagerHtml=pager(data.totalItems);
			$('#pagination').html(pagerHtml);
			var pageCurrent = $('#page').val();
			$('#page'+pageCurrent+' a').addClass('current');

		  }
	});
	
}
*/


/*function proposalMessagesBody(proposa_lID){
	
	$('#proposalID').val(proposal_ID);
	$('#bodyTitle').html("Proposal Messages");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();

	$.ajax({
		url:contextPath+'/proposals/findProposalByID',
		type:'POST',
        dataType:'json',
        data: {proposalID: proposal_ID},
		success:function(data){
			
		   }
		});
	
	htmlMessagesBody+='';

    
    $('#messagesBody').html(htmlMessagesBody);
     
    proposalsInboxList(dataForm);
	
}*/

