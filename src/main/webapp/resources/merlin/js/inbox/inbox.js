$(document).ready(function() {
	$('#page').val('1');
	$("#conversationType").val('inbox');
	// workResume();
	proposalInboxBody();
});

// ordinamento nel caso si ordini di default solo per data
function addOrder() {
	var dirSort = document.getElementById('dirSort');
	if (dirSort.value == 'asc') {
		$('#dirSort').val('desc');
	} else {
		$('#dirSort').val('asc');
	}

	var dirSort = document.getElementById('dirSort');
	$('#date').css("font-weight", "bold");
	if (dirSort.value == 'asc') {
		$('#date i').removeClass("icon-up-dir");
		$('#date i').addClass("icon-down-dir");

	} else {
		$('#date i').removeClass("icon-down-dir");
		$('#date i').addClass("icon-up-dir");

	}

	messageFormBody();
}

function setPage(page) {
	$('#page').val(page);

	if ($("#conversationType").val() == 'inbox') {
		proposalInboxBody();
	} else if ($("#conversationType").val() == 'workroom') {
		workroomInboxBody();
	} else {
		systemInboxBody();
	}
}

function pagesCounter(itemsNumber) {
	var pages;
	var itemsForPage = $('#itemsForPage').val();
	var mod = itemsNumber % itemsForPage;
	if (mod == 0) {
		pages = itemsNumber / itemsForPage;
	} else {
		pages = (itemsNumber - mod) / itemsForPage + 1;
	}
	return pages;
}

function pager(itemsNumber) {
	var pages = pagesCounter(itemsNumber);
	var page = $('#page').val();
	var prev = "";
	var next = "";
	var html = "<ul>";
	var pagerHtml = "";
	if (pages == 0) {
		html += "";
	} else {
		if (pages == 1) {
			pagerHtml += '<li id="page1"><a href="javascript:setPage(1)">1</a></li>';
			html += pagerHtml;
		} else {
			if (pages > 5) {
				if ((parseInt(page) - 2) > 1) {
					if (parseInt(page) + 2 <= parseInt(pages)) {
						for ( var i = parseInt(page) - 2; i <= parseInt(page) + 2; i++) {
							pagerHtml += '<li id="page' + i
									+ '"><a href="javascript:setPage(' + i
									+ ')">' + i + '</a></li>';
						}
					} else {
						for ( var i = pages - 4; i <= pages; i++) {
							pagerHtml += '<li id="page' + i
									+ '"><a href="javascript:setPage(' + i
									+ ')">' + i + '</a></li>';
						}
					}
				} else {
					for ( var i = 1; i <= 5; i++) {
						pagerHtml += '<li id="page' + i
								+ '"><a href="javascript:setPage(' + i + ')">'
								+ i + '</a></li>';
					}
				}
			} else {
				for ( var i = 1; i <= pages; i++) {
					pagerHtml += '<li id="page' + i
							+ '"><a href="javascript:setPage(' + i + ')">' + i
							+ '</a></li>';
				}
			}
			var nextPage = 1 + parseInt(page);
			next = '<li><a class="next" href="javascript:setPage(' + nextPage
					+ ')">Next >></a></li>';
			if (page == 1) {
				html += pagerHtml + next;
			} else {
				var prevPage = parseInt(page) - 1;
				prev = '<li><a class="previous" href="javascript:setPage('
						+ prevPage + ')"><< Previous</a></li>';
				if (page == pages) {
					html += prev + pagerHtml;
				} else {
					html += prev + pagerHtml + next;
				}
			}
		}
	}
	return html + '</ul>';
}

function proposalInboxBody() {

	$('#bodyTitle').html("Proposals Conversations");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();

	htmlMessagesBody += '<div id="noresults">'
			+
			// '<span class="bold" id="counter">1 proposal in 1 page</span>'+
			'<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">As Client<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">| As Freelance<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span style="float:right;">'
			+ '<span>Sort By: </span>'
			+ '<!-- <a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>  -->'
			+ '<a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>'
			+ '</span>' + '</div>' + '<div id="messages">' + '</div>';

	/*
	 * htmlMessagesBody+='<table class="msgTable" id="msgTable">'+ '<thead
	 * id="msgTableHead">'+ '<tr class="msgRowHead">'+ '<th id="firstcol" width="4px"></th>'+ '<th id="firsttextcol" width="70%">Project</th>'+
	 * //'<th id="secondtextcol" width="30%">New Messages</th>'+ '<th id="thirdtextcol" width="30%">New
	 * Messages</th>'+ '<th id="lastcol" width="5px"></th>'+ '</tr>'+ '</thead>'+ '<tbody
	 * id="messages">'+ '</tbody>'+ '</table>';
	 */

	$('#messagesBody-inbox').html(htmlMessagesBody);

	proposalsConversationsInboxList(dataForm);

}

function workroomInboxBody() {
	$("#conversationType").val('workroom');

	$('#bodyTitle').empty();
	$('#messagesBody-inbox').empty();

	$('#bodyTitle').html("Workrooms Conversations");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();

	htmlMessagesBody += '<div id="noresults">'
			+
			// '<span class="bold" id="counter">1 proposal in 1 page</span>'+
			'<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">As Client<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">| As Freelance<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span style="float:right;">'
			+ '<span>Sort By: </span>'
			+ '<!-- <a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>  -->'
			+ '<a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>'
			+ '</span>' + '</div>' + '<div id="messages">' + '</div>';

	$('#messagesBody-inbox').html(htmlMessagesBody);

	workroomsConversationsInboxList(dataForm);
}

function systemInboxBody() {
	$("#conversationType").val('system');

	$('#bodyTitle').empty();
	$('#messagesBody-inbox').empty();

	$('#bodyTitle').html("System Messages");
	var html = "";
	var htmlMessagesBody = "";
	var dataForm = $("#filterDataForm").serialize();

	htmlMessagesBody += '<div id="noresults">'
			+
			// '<span class="bold" id="counter">1 proposal in 1 page</span>'+
			'<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">As Client<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span class="bold" id="counter">'
			+ '<a id="date" href="javascript:addOrder();">| As Freelance<i class="icon-down-dir"></i></a>'
			+ '</span>'
			+ '<span style="float:right;">'
			+ '<span>Sort By: </span>'
			+ '<!-- <a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>  -->'
			+ '<a id="date" href="javascript:addOrder();">Submitted Date<i class="icon-down-dir"></i></a>'
			+ '</span>' + '</div>' + '<div id="messages">' + '</div>';

	$('#messagesBody-inbox').html(htmlMessagesBody);

	systemMessagesInboxList(dataForm);
}

function proposalsConversationsInboxList(dataForm) {

	// alert(contextPath);
	var loadImage = '<img src="'
			+ contextPath
			+ '/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);

	    $.ajax({
				url : contextPath + '/inbox/allConversationsInbox',
				type : 'POST',
				dataType : 'json',
				data : dataForm,
				success : function(data) {
					$("#LoadingImage").hide();
					var html = "";
					var proposals = data.items;

					if (proposals.length == 0) {
						html += '<tr>'
								+ '<td colspan="5">'
								+ '<div class="notification error">'
								+ '<img src="'
								+ contextPath
								+ '/resources/merlin/images/icons/error.png" alt="">'
								+ '<p>There are not conversations </p></div></div>'
								+ '</div>' + '</td>' + '</tr>';
					} else {

						for ( var i = 0; i < proposals.length; i++) {

							var projectClient = proposals[i].refProject.clientOwner.clientID;
							var proposalFreelance = proposals[i].aspirantFreelance.freelanceID;
							var title = "";
							var subTitle = "";
							var newMessages = "";

							if (loggedClientID == projectClient) {
								title += '<div class="proposal-card-name">'
										+ '<span class="bold">Your job: <a href="">'
										+ proposals[i].refProject.title
										+ '</a></span>' + '</div>';

								subTitle += '<div id="subcategory" style="float: left;" title="Sub Category">Freelance Name: '
										+ '<a href="">'
										+ proposals[i].aspirantFreelance.freelanceName
										+ '</a></div>'
										+ '<div class="proposal-card-separator">|</div>'
										+ '<div style="float: left;" id="portfolio">';

								if (proposals[i].newMessageClientCounter != 0) {
									newMessages += '<div class="proposal-card-separator">|</div>'
											+ '<div id="newProposalMessages" style="float: left; color:red;" title="New Proposal Messages"><span class="bold">('
											+ proposals[i].newMessageClientCounter
											+ ') new Messages</span></div>';
								}

							} else if (loggedFreelanceID == proposalFreelance) {
								title += '<div class="proposal-card-name">'
										+ '<span class="bold">Proposal for the Job: <a href="">'
										+ proposals[i].refProject.title
										+ '</a></span>' + '</div>';

								subTitle += '<div id="subcategory" style="float: left;" title="Sub Category">Question about your proposal</div>'
										+ '<div class="proposal-card-separator">|</div>'
										+ '<div style="float: left;" id="portfolio">';

								if (proposals[i].newMessageFreelanceCounter != 0) {
									newMessages += '<div class="proposal-card-separator">|</div>'
											+ '<div id="newProposalMessages" style="float: left; color:red;" title="New Proposal Messages"><span class="bold">('
											+ proposals[i].newMessageFreelanceCounter
											+ ') new Messages</span></div>';
								}
							}


							html += '<div class="proposal-card" style="margin-top: 0px; padding-bottom: 0px; padding-top: 0px;">'
									+ '<div class="proposal-card-top" style="padding-bottom: 0px; padding-top: 0px;">'
									+ '<div class="proposal-card-info">'
									+ title
									+ '<div class="proposal-card-info-detail">'
									+ subTitle
									+ '<div  class="icon-mail">'
									+ '<a href="'
									+ contextPath
									+ '/inbox/conversation?proposalID='
									+ proposals[i].proposalID
									+ '" style="color: green;"> View Conversation </a></div>'
									+ '</div>'
									+ newMessages
									+ '</div>'
									+ '</div>' + '</div>' + '</div>';

						}

					}

					$('#messages').html(html);
					var pagerHtml = pager(data.totalItems);
					$('#pagination').html(pagerHtml);
					var pageCurrent = $('#page').val();
					$('#page' + pageCurrent + ' a').addClass('current');

				}
			});

}

function workroomsConversationsInboxList(dataForm) {

	// $("#conversationType").val('workroom');

	// alert(contextPath);
	var loadImage = '<img src="'
			+ contextPath
			+ '/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);

	   $.ajax({
				url : contextPath + '/inbox/allConversationsInbox',
				type : 'POST',
				dataType : 'json',
				data : dataForm,
				success : function(data) {
					$("#LoadingImage").hide();
					var html = "";
					var proposals = data.items;

					if (proposals.length == 0) {
						html += '<tr>'
								+ '<td colspan="5">'
								+ '<div class="notification error">'
								+ '<img src="'
								+ contextPath
								+ '/resources/merlin/images/icons/error.png" alt="">'
								+ '<p>There are not conversations </p></div></div>'
								+ '</div>' + '</td>' + '</tr>';
					} else {

						for ( var i = 0; i < proposals.length; i++) {

							var projectClient = proposals[i].refProject.clientOwner.clientID;
							var proposalFreelance = proposals[i].aspirantFreelance.freelanceID;
							var title = "";
							var subTitle = "";
							var newMessages = "";

							if (loggedClientID == projectClient) {
								title += '<div class="proposal-card-name">'
										+ '<span class="bold">WorkRoom of the Job: <a href="">'
										+ proposals[i].refProject.title
										+ '</a></span>' + '</div>';


								if (proposals[i].newMessageClientCounter != 0) {
									newMessages += '<div class="proposal-card-separator">|</div>'
											+ '<div id="newProposalMessages" style="float: left; color:red;" title="New Proposal Messages"><span class="bold">('
											+ proposals[i].newMessageClientCounter
											+ ') new Messages</span></div>';
								}

							} else if (loggedFreelanceID == proposalFreelance) {
								title += '<div class="proposal-card-name">'
										+ '<span class="bold">WorkRoom of the Job: <a href="">'
										+ proposals[i].refProject.title
										+ '</a></span>' + '</div>';

								if (proposals[i].newMessageFreelanceCounter != 0) {
									newMessages += '<div class="proposal-card-separator">|</div>'
											+ '<div id="newProposalMessages" style="float: left; color:red;" title="New Proposal Messages"><span class="bold">('
											+ proposals[i].newMessageFreelanceCounter
											+ ') new Messages</span></div>';
								}
							}

							html += '<div class="proposal-card" style="margin-top: 0px; padding-bottom: 0px; padding-top: 0px;">'
									+ '<div class="proposal-card-top" style="padding-bottom: 0px; padding-top: 0px;">'
									+ '<div class="proposal-card-info">'
									+ title
									+ '<div class="proposal-card-info-detail">'
									+ subTitle
									+ '<div  class="icon-mail">'
									+ '<a href="'
									+ contextPath
									+ '/workroom/views?projectID='
									+ proposals[i].refProject.projectID
									+ '" style="color: green;"> Go to this Workroom </a></div>'
									+ '</div>'
									+ newMessages
									+ '</div>'
									+ '</div>' + '</div>' + '</div>';

						}

					}

					$('#messages').html(html);
					var pagerHtml = pager(data.totalItems);
					$('#pagination').html(pagerHtml);
					var pageCurrent = $('#page').val();
					$('#page' + pageCurrent + ' a').addClass('current');

				}
			});

}



function systemMessagesInboxList(dataForm) {

	// alert(contextPath);
	var loadImage = '<img src="'
			+ contextPath
			+ '/resources/merlin/images/load1.gif" width="128px" height="128px"/>';
	$("#messages").html(loadImage);

	    $.ajax({
				url : contextPath + '/inbox/message/allInboxMessages',
				type : 'POST',
				dataType : 'json',
				data : dataForm,
				success : function(data) {
					$("#LoadingImage").hide();
					var html = "";
					var messages = data.items;

					if (messages.length == 0) {
						html += '<tr>'
								+ '<td colspan="5">'
								+ '<div class="notification error">'
								+ '<img src="'
								+ contextPath
								+ '/resources/merlin/images/icons/error.png" alt="">'
								+ '<p>There are not new system messages </p></div></div>'
								+ '</div>' + '</td>' + '</tr>';
					} else {

						for ( var i = 0; i < messages.length; i++) {

							var title = "";
							var subTitle = "";
							
							if(messages[i].systemMessageSubject == "NEW_JOB_PROPOSAL"){
								title += '<div class="proposal-card-name">'
								     + '<span class="bold" style="color: blue">'
								       + 'New Proposal! '
								     + '</span>' 
								   + '</div>';

						       subTitle += '<div id="subcategory" style="float: left;" title="Sub Category">'
						 		   + '<span style="color: green;"> ' 
								    + messages[i].messageProposal.aspirantFreelance.freelanceName 
								     + '</span> ' 
								      + ' has just submit a new proposal for your job: '
								     + '</div>'
								   + '<div class="proposal-card-separator"></div>'
							     + '<div style="float: left;" id="portfolio">';
							}else{
								title += '<div class="proposal-card-name">'
								     + '<span class="bold" style="color: blue">'
								       + 'You are just hired ! '
								     + '</span>' 
								   + '</div>';

						subTitle += '<div id="subcategory" style="float: left;" title="Sub Category">'
								   + '<span style="color: green;"> ' 
								    + messages[i].messageProposal.refProject.clientOwner.clientName 
								   + '</span> ' 
								    + ' has selected your proposal and just hire you for his job: '
								   + '</div>'
								 + '<div class="proposal-card-separator"></div>'
							   + '<div style="float: left;" id="portfolio">';
							}


							html += '<div class="proposal-card" style="margin-top: 0px; padding-bottom: 0px; padding-top: 0px;">'
								    + '<div class="proposal-card-top" style="padding-bottom: 0px; padding-top: 0px;">'
								      + '<div class="proposal-card-info">'
								        + title
								         + '<div class="proposal-card-info-detail">'
								           + subTitle 
								              + '<a href="javascript:decrementNumberOfNewSystemMessages(' + messages[i].messageID + ', ' + messages[i].messageProposal.refProject.projectID + ');">' 
								                + messages[i].messageProposal.refProject.title + '</a></div>'   
								           + '</div>'
								         + '</div>' 
								       + '</div>' 
								     + '</div>';

						}

					}

					$('#messages').html(html);
					var pagerHtml = pager(data.totalItems);
					$('#pagination').html(pagerHtml);
					var pageCurrent = $('#page').val();
					$('#page' + pageCurrent + ' a').addClass('current');

				},

				complete : function() {
					refreshNumberOfNewMessages();
				}
			});

}

function decrementNumberOfNewSystemMessages(messageID, projectID){

	$.ajax({
		url: contextPath + '/inbox/decrementSystemNewMessage',
		type: "GET",
        data: {messageID: messageID},
        dataType:'json',
		success:function(data){
			if(data == true){
				window.location.replace(contextPath + '/projects/views?projectID=' + projectID);
			}else{
				window.location.replace(contextPath + '/workroom/views?projectID=' + projectID);
			}	
		  }
	});
}



