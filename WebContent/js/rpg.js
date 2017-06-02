var map = new Map("background");
var brouillard = new Map("brouillard");
var parcouru = new Map("parcouru");
var clone = new Map("clone");




var brou =1;
var perso =1;
var parc = 1;
map.recupMap();
//map.modifMap();

var joueur = new Personnage("exemple.png", 0, 0, DIRECTION.BAS);

clone.addPersonnage(joueur);

window.onload = function() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');

	canvas.width  = map.getLargeur() * 32;
	canvas.height = map.getHauteur() * 32;

	setInterval(function() {
				
		
		parcouru.parcouruMap();
		brouillard.brouillardMap();
		
				$('#signup7').change(function() {
					   if($(this).is(":checked")) {
							brou=1;
//							alert("Coché"+brou);		
						   return;
					   }
					   brou=0;
//					alert("Decoché"+brou);	
					});
				$('#signup6').change(function() {
					   if($(this).is(":checked")) {
						   parc=1;
//							alert("Coché"+perso);		
						   return;
					   }
					   parc=0;
//					alert("Decoché"+perso);	
					});

				map.dessinerMap(ctx);
				if(parc==1){parcouru.dessinerMap(ctx);}
				clone.dessinerMap(ctx);
				if(brou==1){brouillard.dessinerMap(ctx);}


					
			}, 40);


	



		

	
//	

	
	
//	// Gestion du clavier
//	window.onkeydown = function(event) {
//
//		
//		var e = event || window.event;
//		var key = e.which || e.keyCode;
//
//		
//		
//		switch(key) {
//		case 38 : case 122 : case 119 : case 90 : case 87 : // Flèche haut, z, w, Z, W
//			joueur.deplacer(DIRECTION.HAUT, map);
//			break;
//		case 40 : case 115 : case 83 : // Flèche bas, s, S
//			joueur.deplacer(DIRECTION.BAS, map);
//			break;
//		case 37 : case 113 : case 97 : case 81 : case 65 : // Flèche gauche, q, a, Q, A
//			joueur.deplacer(DIRECTION.GAUCHE, map);
//			break;
//		case 39 : case 100 : case 68 : // Flèche droite, d, D
//			joueur.deplacer(DIRECTION.DROITE, map);
//			break;
//		default :
//			//alert(key);
//			// Si la touche ne nous sert pas, nous n'avons aucune raison de bloquer son comportement normal.
//			return true;
//		}
//		
//		
//		
//		
//	}
}
