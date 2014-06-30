$(document).ready(function() {
	
	$(".chosen-select").chosen({
		placeholder_text_multiple : "Select Skills",
	});

	
	if ($("#projectMainCategory").val() != '0') {
		var main_id = $("#projectMainCategory").val();
	    $.ajax({
	        type: "GET",
	        url: contextPath+'/projects/sub/populateDependentSelects',
	        data: {mainID: main_id, projectID: projectID},
	        dataType:'json',
	        success: function(data){
	            var htmlSubCat = '';
	            var htmlSkills = '';
	            
	            var len1 = data.subCategories.length;
	            for(var i=0; i<len1; i++){
	                if(data.subCategories[i].catID == subCategorySelectedID){
	                	htmlSubCat += '<option selected="selected" value="' + data.subCategories[i].catID + '">' + data.subCategories[i].name + '</option>'; 
	                }else{
		            	htmlSubCat += '<option value="' + data.subCategories[i].catID + '">' + data.subCategories[i].name + '</option>'; 	
	                }
	             }
	            var len2 = data.skills.length;
	            var preSelectedSkillsLength = data.preSelectedSkills.length;
	            for(var i=0; i<len2; i++){
	            	var selected = 0;
	            	for(var j=0; j<preSelectedSkillsLength; j++){
	            		
	            		if(data.preSelectedSkills[j].skillID == data.skills[i].skillID){
	            			selected += 1;
	            		}
	            	}
	            	if(selected > 0){
	            		htmlSkills += '<option selected value="' + data.skills[i].skillID + '">' + data.skills[i].name + '</option>';
	            	}else{
	            		htmlSkills += '<option value="' + data.skills[i].skillID + '">' + data.skills[i].name + '</option>';
	            	}
	            	
	             }
	            
	            $('#projectSubCategory option').each(function(){$(this).remove();});
	            $('#projectSubCategory').append('<option value="' + 0 + '">-- Select Subcategory --</option>');
	            $('#projectSubCategory').append(htmlSubCat);
	            
		        $('#skills option').each(function(){$(this).remove();});
	            $('#skills').append(htmlSkills); 
	            
	            $('.chosen-select').trigger('chosen:updated');
	            $('.chosen-select').chosen({
	            	placeholder_text_multiple:"Select Skills",
	               });
	      
	       } 
	     });
	}

	
	
	
	
	
	
	$("#projectMainCategory").change(function()
			  {
				var main_id = $(this).val();
				if(main_id != '0'){  
			    $.ajax({
			        type: "GET",
			        url: contextPath+'/projects/sub/populateDependentSelects',
			        data: {mainID: main_id},
			        dataType:'json',
			        /* cache: false, */
			        success: function(data){
			            var htmlSubCat = '';
			            var htmlSkills = '';
			            var len1 = data.subCategories.length;
			            for(var i=0; i<len1; i++){
			                 /* html += '<option value="' + data[i].catID + '">' + data[i].name + '</option>'; */
			            	htmlSubCat += '<option value="' + data.subCategories[i].catID + '">' + data.subCategories[i].name + '</option>'; 
			             }
			            var len2 = data.skills.length;
			            for(var i=0; i<len2; i++){
			            	htmlSkills += '<option value="' + data.skills[i].skillID + '">' + data.skills[i].name + '</option>';
			             }
			            
			            $('#projectSubCategory option').each(function(){$(this).remove();});
			            $('#projectSubCategory').append('<option value="' + 0 + '">-- Select Subcategory --</option>');
			            $('#projectSubCategory').append(htmlSubCat);
			            
	  		            $('#skills option').each(function(){$(this).remove();});
			            $('#skills').append(htmlSkills); 
			            
			            $('.chosen-select').trigger('chosen:updated');
			            $('.chosen-select').chosen({
			            	placeholder_text_multiple:"Select Skills",
			               });
			         
			            /* $('[name="_skills"]').remove();  */
			       } 
			     });
				}else{
					   $('#projectSubCategory').html('<option value="' + 0 + '">-- Select Subcategory --</option>');
	 				   $('#skills option').each(function(){$(this).remove();});
					   $('.chosen-select').trigger('chosen:updated'); 
			         }
				 return false;
			   });
	
		
});

