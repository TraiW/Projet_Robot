var test=0;
var test1=0;
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
			alert("Nettoyage de la carte en Cours");
			for(var i=0;i<20;i++)
			{
				for(var j=0;j<20;j++)
				{
					var_parcouru[i][j]=0
					var_brouillard[i][j]=3
				}

			}
			var_brouillard[0][0]=0;
			var_brouillard[1][0]=0;
			var_brouillard[1][1]=0;
			joueur.x=0;
			joueur.y=0;
			joueur.direction=DIRECTION.BAS;




				if(data == false){
					alert("RAZ impossible");
				}
			});
		});
		
		$('#signup8').change(function() {
			console.log("test : "+test);
			   if($(this).is(":checked")) {
					test=1;
					test1=1;
				   return;
			   }
			   test=0;
			});
});


		