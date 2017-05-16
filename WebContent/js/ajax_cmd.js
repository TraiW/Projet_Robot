
$(document).ready(function(){
  $("#ButtonUp").click(function(){
	  	$.post("rest/cmd/UP",
    		  {},
    		  function(data,status){
      		    alert("Post Done received data: " + data + "\nStatus: " + status);
    		  });    
  });

  $("#ButtonDown").click(function(){
	  	$.post("rest/cmd/DOWN",
  		  {},
  		function(data,status){
    		    alert("Post Done received data: " + data + "\nStatus: " + status);
  		  });    
  });
  
  $("#ButtonRight").click(function(){
	  	$.post("rest/cmd/RIGHT",
  		  {},
  		function(data,status){
    		    alert("Post Done received data: " + data + "\nStatus: " + status);
  		  });    
  });
  
  $("#ButtonLeft").click(function(){
	  	$.post("rest/cmd/LEFT",
  		  {},
  		function(data,status){
    		    alert("Post Done received data: " + data + "\nStatus: " + status);
  		  });    
  });

});