var test
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

	// Liste des personnages présents sur le terrain.
	this.personnages = new Array();
}
Map.prototype.recupMap=function(){	

var wait=0;
var xinter=0;
var yinter=0;
test=this.terrain;
console.log("test0 : "+test);

	$.get("rest/cmd/obstacle",function(data) {
		console.log("DEBUT DATA CARTE =>"); 

		console.log("nbr :"+data.terrain[2].nbreObstacle);

		for(var i=0;i<data.terrain[2].nbreObstacle;i++)
		{
			xinter=data.terrain[0].x[i];
			yinter = data.terrain[1].y[i];
			console.log("--------------------------------");
			console.log("xinter : "+xinter);
			console.log("yinter : "+yinter);
			console.log("--------------------------------");

			test[yinter][xinter]=1;
		}

		console.log("test1 : "+test);

	});

	
}
Map.prototype.modifMap=function(){	
		
	//while(wait!=1){}
		for(var j=0;j<20;j++)
		{
			for(var i=0;i<20;i++)
			{
				console.log("this.terrain[j][i] => "+this.terrain[j][i]);
			}
		}
		



	}
Map.prototype.ecrire = function(){
	console.log("this.terrain = >"+this.terrain);
//	this.terrain[0][0]=1;
	console.log("this.terrain[0][0] => "+this.terrain[0][0])
	console.log("this.terrain = >"+this.terrain);

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
