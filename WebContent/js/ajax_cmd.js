
$(document).ready(function(){
  $("#ButtonUp").click(function(){
	  	$.post("rest/cmd/UP",
    		  {},
    		  function(data,status){
    			  test=data;
//      		    alert("Post Done 1received data: " + data + "\nStatus: " + status);
    			  if(data=="true"){
        			  joueur.deplacer(DIRECTION.HAUT, map);
    			 }
    			  else{ alert("Déplacement impossible");}
    			  

    		  });   


  });

  $("#ButtonDown").click(function(){
	  	$.post("rest/cmd/DOWN",
  		  {},
  		function(data,status){
//    		    alert("Post Done downb data: " + data + "\nStatus: " + status);

			  if(data=="true")
 			 {
     			  joueur.deplacer(DIRECTION.BAS, map);

 			 }
			  else
  			{
      		    alert("Déplacement impossible");

  			}
			  
  		 });    

  });
  
  $("#ButtonRight").click(function(){
	  	$.post("rest/cmd/RIGHT",
  		  {},
  		function(data,status){
			  if(data=="true")
 			 {
     			  joueur.deplacer(DIRECTION.DROITE, map);

 			 }  
			  else
	  			{
	      		    alert("Déplacement impossible");

	  			}
		});    
  });
  
  $("#ButtonLeft").click(function(){
	  	$.post("rest/cmd/LEFT",
  		  {},
  		function(data,status){
//  			 alert("Post Done down data: " + data + "\nStatus: " + status);
			  if(data=="true")
	 			 {
	     			  joueur.deplacer(DIRECTION.GAUCHE, map);
	 			 }
			  else
	  			{
	      		    alert("Déplacement impossible");

	  			}
			  
		});    
  });

});