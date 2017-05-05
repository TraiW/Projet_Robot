package RobotManagement.Model;

public class Measures {
	/*Création d'un conteneur de compteur
	 * Executes les proprio suivantes :
	 *  Nombre de commandes exécutés sur le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles rencontrés par le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles visibles par le robot
	 *  Distance parcouru par le robot depuis le lancement du simulateur
	 *  */
	
	private int nbrCommandes = 0;
	private int nbrObstaclesRencontres = 0;
	private int nbrObstaclesVisibles = 0;
	private int distanceParcourue = 0;
	
	public void addCommandes(){nbrCommandes += 1;}
	public int getNbrCommandes(){return nbrCommandes;}
	public void addObstaclesRencontres(){nbrObstaclesRencontres += 1;}	 
	public int getObstaclesRencontres(){return nbrObstaclesRencontres;}	
	public void addObstaclesVisibles(){nbrObstaclesVisibles += 1;}
	public int getObstaclesVisibles(){return nbrObstaclesVisibles;}	
	public void addDistanceParcourue(){distanceParcourue += 1;}
	public int getDistanceParcourue(){return distanceParcourue;}
	
	public void razMesures(){
		nbrCommandes = 0;
		nbrObstaclesRencontres = 0;
		nbrObstaclesVisibles = 0;
		distanceParcourue = 0;
	}
}
