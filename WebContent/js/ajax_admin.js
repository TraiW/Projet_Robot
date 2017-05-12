$(document).ready(function(){
  $("#ButtonStart").click(function(){
	  	$.post("rest/cmd/START",
    		  {},
    		  function(data,status){
      		    alert("Post Done received data: " + data + "\nStatus: " + status);
    		  });    
  });

  $("#ButtonStop").click(function(){
	  	$.post("rest/cmd/STOP",
  		  {},
  		function(data,status){
    		    alert("Post Done received data: " + data + "\nStatus: " + status);
  		  });    
  });
});