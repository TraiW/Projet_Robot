$(document).ready(function(){
  $("#ButtonDepart").click(function(){
	  	$.post("rest/cmd/Depart",
    		  {},
    		  function(data,status){
    			  document.location.href=data;
    		  });
  });    
})
