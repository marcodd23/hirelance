function openForm(type,projectID){
	var projectsNumber=$("#projectsNumber").val();
	$('#type').val(type);
	$('#projectIDevaluated').val(projectID);
	$('#dialog-form' ).dialog( 'open' );
	$(".basic").jRating({
		rateMax:5,
		sendRequest:false,
		step:true,
		length : 5, // nb of stars
		decimalLength:0, // number of decimal in the rate
		canRateAgain : true,
		nbRates:projectsNumber,
		onClick : function(element,rate) {
	         $("#rate").val(rate);
	        }
	});
}

function addFeedback(){
	//alert("provo ad aggiungere");
	var dataForm = $("#feedbackForm").serialize();
	$.ajax({
		url:contextPath+"/projects/addFeedback",
		type:"POST",
		data:dataForm,
	}).done(function(){
		$( '#dialog' ).dialog( 'open' );
		
	});
	//alert("esco dalla funzione");
}

function addFeedbackInWorkRoom(){
	//alert("provo ad aggiungere");
	var dataForm = $("#feedbackForm").serialize();
	$.ajax({
		url:contextPath+"/projects/addFeedback",
		type:"POST",
		data:dataForm,
	}).done(function(){
		location.href=contextPath + "/projects/my";
		
	});
	//alert("esco dalla funzione");
}


$( "#dialog" ).dialog({ 
	autoOpen: false,
	modal: true,
	//position: { my: "center top", at: "top top", of: window },
	buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
        }
      }
});
  $(function() {
    var rate = $( "#rate" ),
      comment = $( "#comment" ),
      allFields = $( [] ).add( rate ).add( comment ),
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
    $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        Conferma: function() {
          var bValid = true;
          allFields.removeClass( "ui-state-error" );
 
          bValid = bValid && checkLength( rate, "rate", 1, 5 );
          bValid = bValid && checkLength( comment, "comment", 1, 60 );

          if ( bValid ) {
            //alert("ciao");
            addFeedback();
            $( this ).dialog( "close" );
            //$("#status").val("COMPLETED");
			//filter();
            var type=$("#type").val();
            if(type=='client'){
            	addStatus('COMPLETED','client');
            }
            else{
            	addStatus('COMPLETED','freelance');
            }
          }
        },
        Cancella: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        allFields.val( "" ).removeClass( "ui-state-error" );
      }
    });
  });