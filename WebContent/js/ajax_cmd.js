
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
  		$("#ButtonStart").click(function(){
			  $.post("rest/cmd/START",
			 {},
			function(data,status){
			alert("Robot déverouillé avec succès");
			document.location.href=data;
		
			});
		});

		$("#ButtonStop").click(function(){
			$.post("rest/cmd/STOP",
			{},
				function(data,status){
				alert("Robot déverouillé avec succès");
				document.location.href=data;
			});
		});
		
		$("#ButtonClear").click(function(){
			$.post("rest/cmd/CLEAR",
				{},
			function(data,status){
			alert("Post Done data: " + data + "\nStatus: " + status);
				if(data == false){
					alert("RAZ impossible");
				}
			});
		});
		
		$("#ButtonRefresh").click(function(){
			$.post("rest/cmd/measure",
				{},
			function(data,status){
			//alert("Post Done data: " + data + "\nStatus: " + status);
				if(data == false){
					alert("Refresh impossible");
				}
			});
		});
});


		