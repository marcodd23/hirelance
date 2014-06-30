
/* ------------------------------------------------------------------------
	REGEX FOR VALIDATION
* ------------------------------------------------------------------------- */

		// Regex for valid name
		var nameRegex=/^\s?[a-zA-Z šŠđĐžŽćĆčČ]+\s[a-zA-Z šŠđĐžŽćĆčČ'-]+$/;
		// Regex for valid email address
		var emailRegex = /^[a-zA-Z0-9-_\.]+@[a-zA-Z0-9-.]+\.[a-zA-Z]{2,4}$/;
		// Regex for empty input
		var emptyRegex=/\S/;
		//Regex for valid website
		var websiteRegex=/^(http[s]?:\/\/(www\.)?|ftp:\/\/(www\.)?|www\.){1}([0-9A-Za-z-\.@:%_\+~#=]+)+((‌​\.[a-zA-Z]{2,3})+)(\/(.)*)?(\?(.)*)?/g;

		
		
/* ------------------------------------------------------------------------
	VALIDATION FOR COMMENT FORM
* ------------------------------------------------------------------------- */
$("#comment_form").submit(function(){
	
		
		var nameField = $("#name").val().toString();
		var emailField = $("#email").val().toString();
		var websiteField = $("#website").val().toString();
		var messageField = $("#message").val().toString();
		var errorMessages=[];
		
				
		
		
		if(nameField.search(emptyRegex) || nameField=="Your Name")
		{
			errorMessages.push('Please enter your name !');
			
		}
		
		
		if(nameField.search(nameRegex)==-1)
		{
			errorMessages.push('Your name is invalid !');
			
		}
		
		
		if(emailField.search(emptyRegex) || emailField=="Your Email")
		{
			errorMessages.push('Please enter your email !');
			
		}
		
		else
		
		if(emailField.search(emailRegex)==-1)
		{
			errorMessages.push('Your email is invalid !');
		
		}
		
		
		if(websiteField!="Website")
		{
			
			if(websiteField.search(websiteRegex)==-1)
			{
				errorMessages.push('Your website is invalid !');
			}
						
		}
		
		
		if(messageField.search(emptyRegex) || messageField=="Type your comment...")
		{
			
			errorMessages.push('Please enter your message !');
			
		}
		
				
		if(errorMessages.length>0)
		{
			//Clear previous errors
			$("#error_handler").html("");
			
			$("#error_handler").append('<div class="notification error"> <img src="images/icons/error.png" alt="Error"/>'); 
			
			for(var i=0;i<errorMessages.length;i++)
			{
				$("#error_handler .notification").append('<p>'+errorMessages[i]+'</p>');
				$("html, body").animate({ scrollTop: $("#error_handler").offset().top -400 },600);
			}
			
			return false;
			
		}
		

	
});
	
	
	
	
/* ------------------------------------------------------------------------
	VALIDATION FOR CONTACT FORM
* ------------------------------------------------------------------------- */
	
		 $(function(){
        $('#send_message').click(function(e){
            
            //Stop form submission & check the validation
            e.preventDefault();
            
            // Variable declaration
            var nameField = $('#name').val();
            var emailField = $('#email').val();
            var subject = $('#subject').val();
            var messageField = $('#message').val();
            var errorMessages=[];
		
			
		
		
		if(nameField.search(emptyRegex) || nameField=="Your Name")
		{
			errorMessages.push('Please enter your name !');
			
		}
		
		else	
		
		if(nameField.search(nameRegex)==-1)
		{
			errorMessages.push('Your name is invalid !');
			
		}
						
		
		if(emailField.search(emptyRegex) || emailField=="Your Email")
		{
			errorMessages.push('Please enter your email !');
			
		}
		
		else	
		
		if(emailField.search(emailRegex)==-1)
		{
			errorMessages.push('Your email is invalid !');
			
		}
		
		
		if(messageField.search(emptyRegex) || messageField=="Type your message...")
		{
			
			errorMessages.push('Please enter your message !');
			
		}
			
			
		//Check to see if error has occured	
		if(errorMessages.length>0)
		{
			//Clear previous errors
			$("#contact_messages_holder").html("");
				
			$("#contact_messages_holder").append('<div class="notification error"> <img src="images/icons/error.png" alt="Error"/>'); 	
			
			for(var i=0;i<errorMessages.length;i++)
			{
				$("#contact_messages_holder .notification").append('<p>'+errorMessages[i]+'</p>');
				$("html, body").animate({ scrollTop: $("#contact_messages_holder").offset().top -400 },600);
					
			}
				
				return false;
			
		}
			
			
		/* Post Ajax function of jQuery to get all the data from the submission of the form as soon as the form sends the values to email.php*/
        $.post("php/email.php", $("#contact_form").serialize(),function(result){
					
					
			//Load gif animation
			$("#contact_loading").html('<img src="images/load.gif" alt=""/>');	
					
			//Clear previous errors
			$("#contact_messages_holder").html("");
					
			
            //Check the result set from email.php file.
			
            if(result == 'sent'){
			
				//If the email is sent successfully, clear the form
				   clearContact();          
				   
				//Display the success message
				$("#contact_messages_holder").append('<div class="notification success"> <img src="images/icons/success.png" alt="Success"/> <p>Thank you, your message has been sent successfully.</p> </div>'); 	
					
				//Disable gif loading
				$("#contact_loading").html("");	
				
			}
					
			else{
                    //Display the error message    
					$("#contact_messages_holder").append('<div class="notification error"> <img src="images/icons/error.png" alt="Error"/> <p>Sorry your message could not be delivered !</p> </div>'); 	
					
					//Disable gif loading
					$("#contact_loading").html("");		
                       
                }
				
         });
		  	
       });
    });    
    
	
	
	function clearContact(){
	
		   $('#name').val("Your Name");
            $('#email').val("Your Email");
            $('#subject').val("Subject");
            $('#message').val("Type your message...");
	
	}
	


	
	
	
/* ------------------------------------------------------------------------
	VALIDATION FOR SUBSCRIPTION
* ------------------------------------------------------------------------- */
$("#EmailSubscription").submit(function(){
	
		
		var emailField = $("#emailSubscribe").val().toString();
		var errorMessages=[];
		
		
		
		if(emailField.search(emptyRegex) || emailField=="Enter your e-mail...")
		{
			errorMessages.push('Please enter your email !');
			
		}
		
		else
		
		if(emailField.search(emailRegex)==-1)
		{
		errorMessages.push('Your email is invalid !');
		
		}
		
		if(errorMessages.length>0)
		{
			//Clear subscribeError
			$("#subscribeMessageHolder").css("display","inline");
			$("#subscribeMessageHolder").html("");
			
			for(var i=0;i<errorMessages.length;i++)
			{
				$("#subscribeMessageHolder").append(' <p class="error">'+errorMessages[i]+'</p> ');
				$("#emailSubscribe").css('border','1px solid #d53d27');
				$("html, body").animate({ scrollTop: $("#subscribeMessageHolder").offset().top-200},1000);
			}
			
			
			
			return false;
			
		}
		
		
});
