function checkForm(){
	if($("#name").val()=='') {
		$("#name").focus();
		$("#error_name").css("display","block");
		return false;
	}
	else{
		$("#error_name").css("display","none");
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!filter.test($("#email").val())){
			$("#email").focus();
			$("#error_email").css("display","block");
			alert("ma porco");
			return false;
		}
		else{
			$("#error_name").css("display","none");
			$("#error_email").css("display","none");
			if($("#subject").val()==''){
				$("#subject").focus();
				$("#error_subject").css("display","block");
				return false;
			}
			else{
				$("#error_name").css("display","none");
				$("#error_email").css("display","none");
				$("#error_subject").css("display","none");
				if($("#message").val()==''){
					$("#message").focus();
					$("#error_message").css("display","block");
					return false;
				}
				else{
					mySubmit();
				}
			}
		}
	}
	return true;
}
function mySubmit(){
	var name = $("#name").val();
	var email = $("#email").val();
	var subject = $("#subject").val();
	var message = $("#message").val();

	$("#load").show();
	$.ajax({
		url:contextPath+"/sendEmail",
		type:"POST",
		data:$("#contact_form").serialize(),
		success:function(data){
			//alert(data);
			if(data.length!=0){
				$("#load").hide();
				$("#result").removeClass("success");
				$("#result").addClass("error");
				$("#result p").html("Error: "+data);
				$("#result img").attr("src",contextPath+"/resources/merlin/images/icons/error.png");
				$("#result").show();
				$("#name").val(name);
				$("#email").val(email);
				$("#subject").val(subject);
				$("#message").val(message);
			}
			else{
				$("#load").hide();
				$("#result").addClass("success");
				$("#result").removeClass("error");
				$("#result p").html("The message has been sent");
				$("#result img").attr("src",contextPath+"/resources/merlin/images/icons/success.png");
				$("#result").show();
				var details="<h5>Name: "+name+"</h5><h5>Email: "+email+"</h5><h4>"+subject+"</h4><h6>"+message+"</h6>";
				$("#details").html(details);
			}
		}
	});
}