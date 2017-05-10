var map = new Map("premiere");

window.onload = function() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');
	
//	canvas.width  = map.getLargeur() * 32;
//	canvas.height = map.getHauteur() * 32;
//	
//	map.dessinerMap(ctx);
	
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
