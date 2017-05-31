var map = new Map("premiere");
var brouillard = new Map("seconde");
var brou =1;
var perso =1;
map.recupMap();
//map.modifMap();

var joueur = new Personnage("exemple.png", 0, 0, DIRECTION.BAS);

map.addPersonnage(joueur);

window.onload = function() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');

	canvas.width  = map.getLargeur() * 32;
	canvas.height = map.getHauteur() * 32;

	setInterval(function() {
				
		
				$('#signup7').change(function() {
					   if($(this).is(":checked")) {
							brou=1;
//							alert("Coché"+brou);		
						   return;
					   }
					   brou=0;
//					alert("Decoché"+brou);	
					});
				$('#signup8').change(function() {
					   if($(this).is(":checked")) {
						   perso=1;
//							alert("Coché"+perso);		
						   return;
					   }
					   perso=0;
//					alert("Decoché"+perso);	
					});
				
				if(brou==1){
				map.dessinerMap(ctx);
				//brouillard.dessinerMap(ctx);
				}
				else
				{
					map.dessinerMap(ctx);
				}
					
			}, 40);


	
//	setInterval(function() {
//	map.dessinerMap(ctx);
//	brouillard.dessinerMap(ctx);
//}, 40);


		

	
//	

	
	
	// Gestion du clavier
	window.onkeydown = function(event) {

		
		var e = event || window.event;
		var key = e.which || e.keyCode;

		
		
		switch(key) {
		case 38 : case 122 : case 119 : case 90 : case 87 : // Flèche haut, z, w, Z, W
			joueur.deplacer(DIRECTION.HAUT, map);
			break;
		case 40 : case 115 : case 83 : // Flèche bas, s, S
			joueur.deplacer(DIRECTION.BAS, map);
			break;
		case 37 : case 113 : case 97 : case 81 : case 65 : // Flèche gauche, q, a, Q, A
			joueur.deplacer(DIRECTION.GAUCHE, map);
			break;
		case 39 : case 100 : case 68 : // Flèche droite, d, D
			joueur.deplacer(DIRECTION.DROITE, map);
			break;
		default :
			//alert(key);
			// Si la touche ne nous sert pas, nous n'avons aucune raison de bloquer son comportement normal.
			return true;
		}
		
		
		
		
	}
}
