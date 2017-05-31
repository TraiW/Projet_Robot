var var_terrain;
var var_parcouru;
var var_brouillard;
function Map(nom) {
	// Création de l'objet XmlHttpRequest
	var xhr = getXMLHttpRequest();

	// Chargement du fichier
	xhr.open("GET", './maps/' + nom + '.json', false);
	xhr.send(null);
	if(xhr.readyState != 4 || (xhr.status != 200 && xhr.status != 0)) // Code == 0 en local
		throw new Error("Impossible de charger la carte nommée \"" + nom + "\" (code HTTP : " + xhr.status + ").");
	var mapJsonData = xhr.responseText;

	// Analyse des données
	var mapData = JSON.parse(mapJsonData);
	this.tileset = new Tileset(mapData.tileset);
	this.terrain = mapData.terrain;
	this.parcouru = mapData.terrain;
	this.brouillard = mapData.terrain;

	
	// Liste des personnages présents sur le terrain.
	this.personnages = new Array();
}
Map.prototype.recupMap=function(){	

var wait=0;
var xinter=0;
var yinter=0;
var_terrain=this.terrain;

	$.get("rest/cmd/obstacle",function(data) {
	//	console.log("DEBUT DATA CARTE =>"); 

	//	console.log("nbr :"+data.terrain[2].nbreObstacle);

		for(var i=0;i<data.terrain[2].nbreObstacle;i++)
		{
			xinter=data.terrain[0].x[i];
			yinter = data.terrain[1].y[i];
//			console.log("--------------------------------");
//			console.log("xinter : "+xinter);
//			console.log("yinter : "+yinter);
//			console.log("--------------------------------");

			var_terrain[xinter][yinter]=1;
		}

	//	console.log("test1 : "+test);

	});

	
}
Map.prototype.parcouruMap=function(){	
	var xinter=0;
	var yinter=0;
	var_parcouru=this.parcouru;
//	console.log("parcouru : "+this.parcouru);
	$.get("rest/cmd/parcouru",function(data) {
		//	console.log("DEBUT DATA CARTE =>"); 

		//console.log("nbr :"+data.parcouru[2].nbreParcouru);
		if(data.parcouru[2].nbreParcouru!=0){
			for(var i=0;i<data.parcouru[2].nbreParcouru;i++)
			{
				xinter=data.parcouru[0].x[i];
				yinter = data.parcouru[1].y[i];
//				console.log("--------------------------------");
//				console.log("xinter : "+xinter);
//				console.log("yinter : "+yinter);

				var_parcouru[xinter][yinter]=6;
			}
		}


		});

}

Map.prototype.brouillardMap=function(){	
	var xinter=0;
	var yinter=0;
	var_brouillard=this.brouillard;
//	console.log("parcouru : "+this.parcouru);

	$.get("rest/cmd/masquage",function(data) {

		if(data.masquage[2].nbreMasquage!=0){
			for(var i=0;i<data.masquage[2].nbreMasquage;i++)
			{
				xinter=data.masquage[0].x[i];
				yinter = data.masquage[1].y[i];


				var_brouillard[xinter][yinter]=0;
			}



		}


		});
	

}



// Pour récupérer la taille (en tiles) de la carte
Map.prototype.getHauteur = function() {
	return this.terrain.length;
}
Map.prototype.getLargeur = function() {
	return this.terrain[0].length;
}

// Pour ajouter un personnage
Map.prototype.addPersonnage = function(perso) {
	this.personnages.push(perso);
}
Map.prototype.removePersonnage = function(perso){
	this.personnages.pop()
}

Map.prototype.dessinerMap = function(context) {
	for(var i = 0, l = this.terrain.length ; i < l ; i++) {
		var ligne = this.terrain[i];
		var y = i * 32;
		for(var j = 0, k = ligne.length ; j < k ; j++) {
			this.tileset.dessinerTile(ligne[j], context, j * 32, y);
		}
	}

	// Dessin des personnages
	for(var i = 0, l = this.personnages.length ; i < l ; i++) {
		this.personnages[i].dessinerPersonnage(context);
	}
}
