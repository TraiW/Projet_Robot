package RobotManagement.Model;

public class Measures {
	/*Création d'un conteneur de compteur
	 * Executes les proprio suivantes :
	 *  Nombre de commandes exécutés sur le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles rencontrés par le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles visibles par le robot
	 *  Distance parcouru par le robot depuis le lancement du simulateur
	 *  */
	
	int nbrCommandes = 0;
	int nbrObstaclesRencontres = 0;
	int nbrObstaclesVisibles = 0;
	int distanceParcourue = 0;
	
	public void setCommandes(){
		nbrCommandes = nbrCommandes + 1;
	}
	
	public int getNbrCommandes(){
		return nbrCommandes; 
	}
	
	public void setObstaclesRencontres(){
		nbrObstaclesRencontres = nbrObstaclesRencontres + 1;
	}
	 
	public int getObstaclesRencontres(){
		return nbrObstaclesRencontres;
	}
	
	public void setObstaclesVisibles(){
		/*récupère le pattern de vision du robot et regarde ce qu'il y a dans la case, si obstacle, faire +1*/
	}
	
	public int getObstaclesVisibles(){
		return nbrObstaclesVisibles;
	}
	
	public void setDistanceParcourue(){
		distanceParcourue = distanceParcourue + 1;
	}
	
	public int getDistanceParcourue(){
		return distanceParcourue;
	}
}
