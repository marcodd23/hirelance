	
   function deliveryTimeFormatter(deliveryTime){
             var dtFormatted;
                    switch(deliveryTime){
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
                    
                    return dtFormatted;
   }           