window.onload = function() {
	var c = document.getElementById( "mon_canvas" );
	var ctx = c.getContext("2d");

	// Fond
	ctx.fillStyle = "olivedrab";
	ctx.fillRect(50,50,250,250);

	// Bouche
	ctx.fillStyle = "pink";
	ctx.fillRect(100,200,150,50);

	// Yeux
	ctx.fillStyle = "powderblue";
	ctx.fillRect(100,100,50,50);
	ctx.fillRect(200,100,50,50);
}