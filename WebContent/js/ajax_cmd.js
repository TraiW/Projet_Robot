
$(document).ready(function(){
	var test = [];
  $("#ButtonUp").click(function(){
	  	$.post("rest/cmd/UP",
    		  {},
    		  function(data,status){
      		    alert("Post Done 1received data: " + data + "\nStatus: " + status);
    		  });    
  });

  $("#ButtonDown").click(function(){
	  	$.post("rest/cmd/DOWN",
  		  {},
  		function(data,status){
  			  test.push(data);
    		    alert("Post Done received dddddddata: " + data + "\nStatus: " + status);

  		  });    
	    console.log("test : ", test);

  });
  
  $("#ButtonRight").click(function(){
	  	$.post("rest/cmd/RIGHT",
  		  {},
  		function(data,status){
    		    alert("Post Done 2received data: " + data + "\nStatus: " + status);
  		  });    
  });
  
  $("#ButtonLeft").click(function(){
	  	$.post("rest/cmd/LEFT",
  		  {},
  		function(data,status){
    		    alert("Post Done 3received data: " + data + "\nStatus: " + status);
  		  });    
  });

});