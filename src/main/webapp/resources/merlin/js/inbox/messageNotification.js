/*$(document).ready(function(){
	//Se l'elemento esiste ......
	if($("#inbox-notification").length > 0){
		findNumberOfNewMessages();
	}	
});	*/

findNumberOfNewMessages();

function findNumberOfNewMessages(){

	$.ajax({
		url:contextPath+'/inbox/findTotalNewMessages',
		type: "GET",
        data: {userID: userLogged},
        dataType:'json',
		success:function(data){
			var html = "";
			var prevNewsNumber = 0;
			
	    if($("#notification-number").length > 0){
	    	prevNewsNumber = $("#notification-number").html();
	    	//alert(prevNewsNumber);
			}	
	    
		   if($("#notification-number").length > 0){ 
			if(data!=0){
				//alert("Ciaooo");
				html+='<span class="nav-counter" style="display: block;" id="notification-number">' + data + '</span>';	
				$('#notification-number').replaceWith(html);
			   }else{
				      $('#notification-number').remove();
			        }
		         }else{
		        if(data!=0){
		    		html+='<span class="nav-counter" style="display: block;" id="notification-number">' + data + '</span>';	
		    		$('#inbox-notification').html(html);
		    	  }
		         }
		   
			if(($("#messagesBody-inbox").length > 0) && (data>prevNewsNumber)){
				//alert("dfsdbsfdb");
				inboxBody();
		       }
           },
		  
		  complete: function() {
		      // Schedule the next request when the current one's complete
		      setTimeout(findNumberOfNewMessages, 5000);
		    }
	});	
}


function refreshNumberOfNewMessages(){

	$.ajax({
		url:contextPath+'/inbox/findTotalNewMessages',
		type: "GET",
        data: {userID: userLogged},
        dataType:'json',
		success:function(data){
			var html = "";
             
			if(data!=0){
				html+='<span class="nav-counter" style="display: block;" id="notification-number">' + data + '</span>';
				$('#notification-number').replaceWith(html);
			}else{
				$('#notification-number').remove();
			   }
		  }
	});
}
